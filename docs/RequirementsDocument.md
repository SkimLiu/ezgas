# Requirements Document
 
Authors: Michele Filippini, Giovanni Brignone, Andrea Zappavigna, Dong Liu
 
Date: 18/04/2020

Version: 1

Change history

| Version | Changes | 
| ----------------- |:-----------|
| 1 | Initial definition of requirements  |

# Contents
- [Abstract](#abstract)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System Design](#system-design)
- [Deployment Diagram](#deployment-diagram)

# Abstract

EZGas is a crowdsourcing service that allows users to:
* collect prices of fuels in different gas stations
* locate gas stations in an area, along with the prices they practice.

This service is developed both as a web application and as an application for mobile devices so that it can be easily used at any time. There are two different types of user that can use the application : Registered and Unregistered user.

The difference between the two types of users lies in the functionalities of the application that they can use. 
Unregistered users can run searches through appropriate filters in order to find the stations of their interest.
Registered users, all those who have signed up for a free account, can contribute to adding information to the application, such as adding gas stations not yet present, and modifying the information, like the services available or the price of fuel for a certain gas station, in order to keep it updated.

Since all the information contained in the application is entered by registered users and in order to make the application scalable, the administration of the information entered and user management is carried out through an automatic system.

With each modification and insertion of new information, the application keeps the information about the users who created it. In this way, in case of incorrect information, the other users of the application have the possibility to decide whether or not to report the incorrect information. After a certain number of reports, the system locks the account of the users involved.

# Stakeholders
 
| Stakeholder name  | Description |
| ----------------- |:-----------:|
| User | Unregistered end user who can only do searches and view information regarding gas stations |
| Registered user | End user with an existing account. Differently from the unregistered user he is also able to add or update information on gas stations |
| Map system | Provides maps APIs which allow to retrieve information about the localization of users and gas stations |
| Developer | Person in charge of developing the application |
 
# Context Diagram and interfaces
 
## Context Diagram
```plantuml
left to right direction
actor User as U
actor "Registered user" as RU
actor "Maps system" as M
U <|- RU
skinparam packageStyle rectangle
rectangle system {
U -- (EZGas)
RU -- (EZGas)
(EZGas) -- M
}
```

## Interfaces
| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
| User | GUI | Screen, keyboard |
| Registered user | GUI | Screen, keyboard |
| Maps system| API | Internet connection |
 
# Stories and personas
 
Josh is a family man, he has a precarious job. Every morning he wakes up early to go to work. He would like to use the bicycle to save the price of fuel, but unfortunately the company he works for is very far from his home. The gas station in which he is used to going has recently raised prices, despite being aware of this he does not know others stations where to go. He would like to have a simple way to find which gas station offers the fuel at a lower price.
 
Mark is a carefree boy. He works in the family business. He loves driving sports cars and recently bought one. His girlfriend lives several hundred kilometers away from him. Mark takes the car every weekend to get there and spend time with her. He would be interested in having an easy way to find out the nearest petrol station, whenever the petrol is running out so as to stop only when necessary.
 
Luke is the manager of a company, he often travels with his company car. Since he does not pay directly for fuel, he is not so interested in the price of this. The trips he makes can be very long and he is therefore interested in finding service station that offer certain services such as the availability of a toilet and a bar area where he can sip a good coffee.

Anna is a young girl who cares about the environment and is very worried about the increase of the pollution. She works in a company outside the city where she lives so she cannot use public transport to go to work and not even other green solutions. She would like to buy an electric car but her salary won't allow her. For this reason, she decided to buy a natural gas city car that consumes and pollutes little. Unfortunately, there are very few refueling stations that offer this fuel and reluctantly she has to use petrol when facing longer journeys. She would like to find a way to find natural gas stations close to where she is also when she is far from her home.
 
# Functional and non functional requirements
 
## Functional Requirements
 
| ID    	| Description  |
| ------------- |:-------------:|
|  FR1     | Add new gas station |
|  FR2     | Update gas station | 
|  FR3     | Delete gas station |
|  FR4     | Add fuel type and price |
|  FR5     | Update fuel price |
|  FR6     | Delete fuel type and price |
|  FR7     | Sign in |
|  FR8     | Sign up |
|  FR9     | Search gas stations based on different parameters |
|  FR10    | Display on the map system the list of gas station yielded by the research |
|  FR11    | Management of reports |
|  FR12    | Report incorrect information |
|  FR13    | Determine the current user's location using GPS |
|  FR14    | When a new gas station is added, location and gas company are mandatory information |
 
## Non Functional Requirements
 
| ID    	| Type       	| Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     | Usability | Application should be used with no training by any user | All FR |
|  NFR2     | Performance | Functions should complete in < 1 sec  | FR1-7, FR13-14 |
|  NFR3     | Performance | Functions should complete in < 2.5 sec  | FR8-11 |
|  NFR3     | Portability | The web application should be supported by Internet Explorer (version 11.0 and later), Google Chrome (version 81.0 and later), Mozilla Firefox (version 75.0 and later) and Safari (version 13 and later) | All FR |
|  NFR4     | Portability | The application (functions and data) should have a mobile version for iOS (version 12.4.6 and later) and Android (version 10 and later) | All FR |
|  NFR5     | Localization | Decimal numbers use . (dot) as decimal separator | FR4-5 |
|  NFR6     | Localization | Fuel prices are priced using euros/liter | FR4-5, FR9 |
|  NFR7     | Localization | All the distances are expressed in meters | FR9-10 |
  
# Use case diagram and use cases

## Use case diagram
```plantuml
actor User as U
actor "Registered user" as RU
actor Map as M
skinparam packageStyle rectangle
rectangle EZGas{
	GM as (Gas station management)
	UGS as (Update gas station)
	U <|-- RU
	U - (Sign up)
	U - (Search gas station)
	RU - GM
	UGS ..> GM: <extend>
	(Add new fuel type and price) ..> UGS: <extend>
	(Update fuel price) ..> UGS: <extend>
	(Delete fuel type and price) ..> UGS: <extend>
	(Add new gas station) ..> GM: <extend>
	(Delete gas station) ..> GM: <extend>
	UGS <. (Report incorrect information): <extend>
	(Report incorrect information) ..> (Report management): <include>
	GM ..> (Sign in): <include>
	(Search gas station) -- M
	GM -- M
}
```
 
## Use Cases
 
### Use case 1, UC1 - FR1,FR15  Add new gas station
 
| Actors Involved | Registered use, Map system |
| ------------- |:-------------:|
|  Precondition | Registered user RU is logged in | 
|  Post condition | If the number of addition requests about the Gas station G is higher than a certain threshold the information about G are stored in the application |
|  Nominal Scenario | RU inserts the position, through the map system, the name and the gas company which the gas station belongs and sends a request to add the gas station in the application |
|  Variants | The station in the given inserted position already exists in the application, issue warning and the request is not sent |


### Use case 2, UC2 - FR2 Update gas station
 
| Actors Involved   | Registered user, Map system |
| ------------- |:-------------:|
|  Precondition  | Gas station G is stored in the application , Registered user RU is logged in | 
|  Post condition | G has been successfully updated and the changes are stored in the system together with the identifier of RU and the time of the change |
|  Nominal Scenario | RU selects the gas station, through the map system, and one of the possible update to do : update the name, update opening hours, update payment method or update services offered |
|  Variants  |  |
 
### Use case 3, UC3 - FR3 Delete gas station
 
| Actors Involved    	| Registered user, Map system |
| ------------- |:-------------:|
|  Precondition 	| Gas station G is stored in the application, Registered user RU is logged in | 
|  Post condition   | If the number of delete requests about G is higher than a certain threshold the information about G are removed from the application |
|  Nominal Scenario | RU selects the station G, through the map system, and sends a request to delete it, the request is stored in the system |
|  Variants     |  |
 
### Use case 4, UC4 - FR Add fuel type and price
 
| Actors Involved	| Registered user |
| ------------- |:-------------:|
|  Precondition | Gas station G is stored in the application, Registered user RU is logged in, it is possible to select the types of fuel among those not yet present in G | 
|  Post condition   | If the number of new fuel type FT addition is higher than a certain treshold the information about FT and its price are stored in the application and the latest price, about FT, inserted is showed |
|  Nominal Scenario | RU inserts the new fuel type, related price and sends a request to add those information in the application |
|  Variants     | |
 
### Use case 5, UC5 - FR5 Update fuel price
 
| Actors Involved | Registered user |
| ------------- |:-------------:|
|  Precondition | Gas station G is stored in the application, Registered user RU logged in and fuel price P of a certain type exists | 
|  Post condition   | the new price has been successfully updated and RU identifier and time of the update are stored in the system |
|  Nominal Scenario | RU selects P to update and change it |
|  Variants     |  |
 
### Use case 6, UC6 - FR6 Delete fuel type and price
 
| Actors Involved | Registered user |
| ------------- |:-------------:|
|  Precondition 	| Gas station G is stored in the application , Registered user RU is logged in and fuel price P of a certain type exists | 
|  Post condition   | If the number of deletion requests is higher than a certain threshold the fuel type and its price are removed |
|  Nominal Scenario | RU selects and sends a request to delete the fuel type and the related price from the application |
|  Variants     | |
 
### Use case 7, UC7 - FR9-10 Search gas station
 
| Actors Involved | User, Maps system |
| ------------- |:-------------:|
|  Precondition     |  | 
|  Post condition   | Display on the map system the list of gas station yielded by the research |
|  Nominal Scenario | U selects on the basis of which parameters to search for gas stations: fuel type, price, payment method, distance from current position, services and start the search |
|  Variants | if the research yields no results, issue warning |
 
### Use case 8, UC8 - FR7 Sign in
 
| Actors Involved  | Registered user |
| ------------- |:-------------:|
|  Precondition  | Registered user RU hasn't logged in yet | 
|  Post condition   | RU has been successfully logged in |
|  Nominal Scenario | RU inserts username, password  |
|  Variants     | Username or password are not correct, issue warning |
 
### Use case 9, UC9 - FR8 Sign up
 
| Actors Involved	| User |
| ------------- |:-------------:|
|  Precondition 	| | 
|  Post condition   | User U creates a new account and becomes a Registered user |
|  Nominal Scenario | U inserts the email, selects one username and password |
|  Variants     | The email entered is just used by another account or the chosen username is not available, the creation of the new account is interrupted and a warning is issued  |
 
### Use case 10, UC10 - FR12 Report incorrect information
| Actors Involved	| Registered user |
| ------------- |:-------------:|
|  Precondition     | Registered user RU logged in,  Gas station G is stored in the application | 
|  Post condition   | The report is stored into the system |
|  Nominal Scenario | RU discover a wrong information about G and sends a report regarding it |
|  Variants     | |
 
### Use case 11, UC11 - FR11 Management of the report
| Actors Involved	| Registered user |
| ------------- |:-------------:|
|  Precondition     | A new report is received | 
|  Post condition   | If the number of reports belonging to a certain Registered user RU is higher than a certain threshold, the system bans RU |
|  Nominal Scenario | From the data the data reported the system extract the RU who did the wrong update and increase the number of reports related to him |
|  Variants     | |
 
# Relevant scenarios
 
## Scenario 1

| Scenario ID: SC1    	| Corresponds to UC2  |
| ------------- |:-------------|
| Description | Registered user RU wants to update the information about a certain gas station G just inserted in the application |
|Precondition | Gas station G is stored in the application and all the non mandatory information are empty , Registered user RU is logged in |
|Postcondition | G has been successfully updated and the changes are stored in the system together with the username of RU and the time of the change |
| Step#    	| Step description  |
|  1 	| RU selects the gas station |
|  2    | RU enters in the Gas station update area |
|  3 	| RU selects one or more of the possible services offered by the service station among those predefined by the application: oil change, tire inflation, presence of a bar area, presence of the toilet,presence of loyalty discounts and presence of a utility shop | 

## Scenario 2

| Scenario ID: SC2    	| Corresponds to UC4  |
| ------------- |:-------------|
| Description | Registered user RU wants to add a new fuel type and price for a certain Gas station |
| Precondition | Gas station G is stored in the application, Registered user RU is logged in, it is possible to select the types of fuel among those not yet present in G |
| Postcondition | RU goes back to the Gas station update area |
| Step#    	| Step description  |
|  1 	| RU selects the gas station |
|  2    | RU enters in the Gas station update area |
|  3    | RU enters in the Add new fuel type and price area |
|  4 	| RU tries to insert a type of fuel already existing in the station but he cannot select it |

# Glossary

```plantuml
class EZGas
class User {
	name
	surname
	longitude
	latitude
}

class "Registered user" as RU {
	email
	username
	password
}

class "Gas station" as GS {
	name
	address
	opening hour
}

class Report {
	time
	type
	field
}

class Service {
	name
	type
}

class Fuel {
	type
	price
}

class Payment {
	method
}

class Submission {
	time
	type
}

class Insertion {
	field
	value
}
class Deletion

class Update {
	field
	new value
}

note left of User
	Latitude and longitude
	define the user location
end note
note bottom of Fuel
	Type can be:
	Benzina, Benzina Super,
	Gasolio, GPL, Metano
end note
note bottom of Payment
	Method can be:
	cash, credit or debit
end note
note left of Submission
	Type can be:
	station, fuel,
	payment, service
end note
note bottom of Service
	Type can be:
	oil change, tire inflation,
	restaurant area, toilet, discount
end note
note as SubmissionField
	Field specifies what
the submission refers to
end note
note as SubmissionType
	Submission type can only be
	Gas station or Fuel
end note

EZGas -- "*" User
EZGas -- "*" "GS"
EZGas -- "1" Map
User <|-- RU
GS o-- "*" Fuel: sells >
GS o-- "*" Service: offers >
GS o-- "*" Payment: accepts >
RU "1" - "*" Report: creates >
RU "1" - "*" Submission: submits >
Submission "*" - "1" GS: refers to >
Submission <|-- Insertion
Submission <|-- Deletion
Submission <|-- Update
Report "*" - "1" GS: refers to >
Report -[hidden]- Submission
Insertion .. SubmissionField
Update .. SubmissionField
SubmissionType .. Insertion
SubmissionType .. Deletion

```
# System Design

Since the application is only sw, the system design is not reported.

# Deployment Diagram

```plantuml
node "EzGas server" as a
node "Customer smartphone / PC" as n
node "Maps system" as m
a -- "*" n : internet
a -- m : internet

```


