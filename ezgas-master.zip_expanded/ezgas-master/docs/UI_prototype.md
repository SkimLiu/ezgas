# Graphical User Interface Prototype  

Authors: Michele Filippini, Giovanni Brignone, Andrea Zappavigna, Dong Liu

Date: 18/04/2020

Version: v4

Change history

| Version | Changes                                        	 |
| ------- | ---------------------------------------------------- |
| v4      | Changed the UI order in documents and added new ones |
| v3      | Added some description and [credits](#Credits)       |
| v2.1    | Changed the layout of some pages               	 |
| v2      | Added some new function pages                  	 |



## Contents


### GUI Prototype

According to functional and non functional requirements and the user's usage habits and processes, we have designed the following GUI. These images take into our consideration about how to design an automated and efficient system.

##### GUI 1, Home page

<img src="/docs/UI/Maps.png" alt="Log-In Page" style="zoom:33%;" />

The Home page is the first screen that the user, registered or not, sees. The service station search button is visible to both types of user, as well as the map showing the stations in the surrounding area. The button at the bottom right for the request to add a new station is instead visible only to registered users.

##### GUI 2,3 RegisteredUserMenu and UserMenu

<img src="/docs/UI/RegisteredUserMenu.png" alt="UserMenu" style="zoom:33%;" />

On each page of the application it is possible to access the side menu which allows the registered user to: if clicked on the home icon to return to the home page and to disconnect from the application if the log out option is selected.

<img src="/docs/UI/UserMenu.png" alt="RegisteredUserMenu" style="zoom:33%;" />

The menu for the unregistered user maintains the home icon compared to the previous one, with the same functionality, but instead of the log out option, the application user can select the sign up and log in action.

##### GUI 4 Sign-Up page, UC9 - FR8 Sign up

<img src="/docs/UI/Sign-Up Page.png" alt="Sign-Up Page" style="zoom:33%;" />

This is the sign-up page which shows five empty fields to be filled: Name, Surname, Email, Username and Password. This functionality allows the user to become a Registered user. If the email or the username are used by another registered account, the registration process will stop and show a warning of conflicting elements.

##### GUI 5 Log-in page, UC8-FR7 Sign in

<img src="/docs/UI/Log-In Page.png" alt="Log-In Page" style="zoom:33%;" />

After completing the registration, the registered User can log in with his e-mail address or username. If the credentials entered are correct, RU will access the application on the Home page, if these are incorrect, access is denied and the application will signal with a warning about which fields are incorrect.

##### GUI 6,7 Maps search, Map search 2, UC7 - FR9-10 Search gas station

The application uses the API of maps system in order to retrieve information about the localization of users and gas stations.

<img src="/docs/UI/Maps Search.png" alt="Maps Search" style="zoom:33%;" />

Through the Search gas station function, accessible to all registered and non-registered users, it is possible to search for the gas stations of interest through some filters such as: the distance, able to select the stations within a certain radius of kilometers, the services offered by a station, the payment methods accepted in this, the type of fuel, which can also be more than one, and the relative maximum price willing to pay. As shown in the image below, once a type of fuel has been selected, this will be highlighted in order to allow the user to easily recognize it. Once the filters have been selected, by clicking the button at the bottom right the search is carried out and the result shown in the map below. 

<img src="/docs/UI/Maps Search 2.png" alt="Maps Search 2" style="zoom:33%;" />

If the user wishes to filter by price, or by distance even if not shown in the image, the selection is made through a slider in order to avoid possible errors by the user in entering the values.

##### GUI 8 Gas Station Detail, UC3 - FR3 Delete gas station

<img src="/docs/UI/Gas Station Detail.png" alt="Gas Station Detail" style="zoom:33%;" />

Once a result is obtained by searching, the user can press on one of the shown stations in order to be sent to the gas station details page. The gas station details page provides all the information available for the gas station such as its name, opening hours, list of available fuels and their prices, services and accepted payment methods. The buttons related to the request to delete the station and change the parameters are visible to registered users only.

##### GUI 9 Gas Station New, UC1 - FR1,FR15 Add new gas station

<img src="/docs/UI/Gas Station New.png" alt="Gas Station New" style="zoom:33%;" />

By clicking on the home page button related to new station addition, the registered user will have the possibility to send a request for adding a new gas station, specifying its name, gas company and location. All fields are mandatory. If the station in the given position already exists in the application, a warning is shown and the request is not sent.

##### GUI 10,11 Gas Station Edit, Gas Station Edit 2, UC2 - FR2 Update gas station

<img src="/docs/UI/Gas Station Edit.png" alt="Gas Station Edit" style="zoom:33%;" />

Through this screen the user can edit wrong or out of date information about a gas station. If he wants to make changes regarding fuels, he can access a new page by clicking on "Fuel type and prices". If the user deems it appropriate, he can send a report by clicking "Report Wrong Information". In this case, a new window similar to the one shown opens and here he can decide which information to report. The location field has been entered for completeness but cannot be changed.

<img src="/docs/UI/Gas Station Edit 2.png" alt="Gas Station Edit 2" style="zoom:33%;" />

The selection of services and payment methods, can be done via a checkbox.

##### GUI 12,13 Fuel Edit, Fuel Edit 2, UC5 - FR5 Update fuel price, UC6 - FR6 Delete fuel type and price

<img src="/docs/UI/Fuel Edit.png" alt="Fuel Edit" style="zoom:33%;" />

If the registered user detects some out-of-date information regarding the types and / or prices of fuel, through this page he can:
* modify, by clicking on the type of fuel, the price
* send a request for removal of a certain type by clicking on the trash icon
* add, if not present, a new type

<img src="/docs/UI/Fuel Edit 2.png" alt="Fuel Edit 2" style="zoom:33%;" />

##### GUI 14 Fuel New, Fuel New Add, UC4 - FR Add fuel type and price

<img src="/docs/UI/Fuel New Add.png" alt="Fuel New Add" style="zoom:33%;" />

When selecting the type of fuel only the fuel types not present yet in that gas station are shown to the user.

### Credits

ICONS from the Noun Project

Gas station by Kantor Tegalsari

Home, Tools and Power by i cons

Plus by Rohith M S

Tag By Icons8

Search by Rizky Mapat Waluya

Bin by Alice Design from the Noun Project

PHOTO from Unsplash

Gas station by Justin Chrn
