# Unit Testing Documentation

Authors: Andrea Zappavigna, Dong Liu, Michele Filippini, Giovanni Brignone

Date: 14/05/2020

| Version | Contents |
| -------- | --------- |
| 1 | Unit tests on entities User, GasStation |
| 2 | Unit tests on entities UserDto, GasStationDto, LoginDto, IdPw |

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)

- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

All the tests described through the black box method refer to methods in which the parameters supplied at the input are checked. Many of these tests, for which a not valid result is expected, were not carried out in JUnit since the methods provided in the package entity for the GasStation and User classes do not carry out any control and therefore would lead to an insignificant result being that as long as they respect the defined type, all values are accepted and stored.


### **Class entity/User.java - constructor method**

 **Criteria for method *User*:**
  - userName, password and email must be strings
  - reputation must be a integer ranging between -5 and 5


 **Predicates for method *User*:**

 | Criteria | Predicate |
 | -------- | --------- |
 | Correctness | userName instanceof String == True |
 | | password instanceof String == True |
 | | email instanceof String == True |
 | | reputation instanceof Integer == True |


 **Boundaries**:

 | Criteria | Boundary values |
 | -------- | --------------- |
 | Correctness | "" (empty string) |

Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null. Any given set of values passes the correctness check if all values are individually correct (logic AND of predicates).

 **Combination of predicates**:

 | Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
 |-------|-------|-------|-------|
 |true|V|User("Mario", "Rossi", "mariorossi@ezgas.com", 0)| void it.polito.ezgas.EZGasApplicationTests.testUserConstructor() |
 |false|I|User("Mario", "", "mariorossi@ezgas.com", 3)||

### **Class entity/User.java - setReputation method**

Getters and setters are extremely simple pieces of code, they do not contain any logic and are stateless; thus only one of them will be considered foe each class as an example.

 **Predicates for method *setReputation*:**

 | Criteria | Predicate |
 | -------- | --------- |
 | Correctness | reputation instanceof Integer == True  |
 | Range | reputation >= -5 && reputation <= 5 |


 **Boundaries**:

 | Criteria | Boundary values |
 | -------- | --------------- |
 | Correctness | "" (empty string) |
 | Range | >= -5 && <= 5 |
 | | < -5 |
 | | > 5 |

Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

 **Combination of predicates**:

 | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
 |-------|-------|-------|-------|-------|
 |true|< -5|I|setReputation(-7)||
 |true|> 5|I|setReputation(8)||
 |true|>= -5 && <= 5|V|setReputation(0)| void it.polito.ezgas.EZGasApplicationTests.testUserGetterAndSetter() |
 |false|< -5|I|setReputation(true)||
 |false|> 5|I|setReputation("3")||
 |false|>= -5 && <= 5|I|setReputation(5.0)||

 ### **Class entity/GasStation.java - constructor method**

  **Criteria for method *GasStation*:**
   - gasStationName, gasStationAddress, carSharing and reportTimestamp must be strings
   - hasDiesel, hasSuper, hasSuperPlus, hasGas and hasMethane must be booleans
   - dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, lat, lon and reportDependability must be doubles
   - reportUser must be a integer


  **Predicates for method *GasStation*:**

  | Criteria | Predicate |
  | -------- | --------- |
  | Correctness | gasStationName instanceof String == True |
  | | hasDiesel instanceof Boolean == True |
  | | dieselPrice instanceof Double == True |
  | | reportUser instanceof Integer == True |
  | | etc.. |


  **Boundaries**:

  | Criteria | Boundary values |
  | -------- | --------------- |
  | Correctness | "" (empty string) |


 Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.  Any given set of values passes the correctness check if all values are individually correct (logic AND of predicates).

  **Combination of predicates**:

  | Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
  |-------|-------|-------|-------|
  |true|V|GasStation("Eni Abruzzi","Corso Duca degli Abruzzi 42", true, true, false, false, false, "Eni Enjoy", 45.08, 07.41, 1.58, 1.63, 0, 0, 0, 0, "00:00:00", 10)| void it.polito.ezgas.EZGasApplicationTests.testGasStationConstructor() |
  |false|I|GasStation("")| |

  ### **Class entity/GasStation.java - setLat method**

   **Criteria for method *setLat*:**
  - lat must be a double
  - lat value ranging between -90 and 90


   **Predicates for method *setLat*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | lat instanceof Double == True |
   | Range | lat >= -90.0 && lat <= 90.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= -90.0 && <= 90.0 |
   | | < -90.0 |
   | | > 90.0 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< -90.0|I|setLat(true)| |
   |false|> 90.0|I|setLat("80")| |
   |false|>= -90.0 && <= 90.0|I|setLat(null)| |
   |true|< -90.0|I|setLat(-92.42)| |
   |true|> 90.0|I|setLat(108.12)| ||
   |true|>= -90.0 && <= 90.0|V|setLat(45.33)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |

 ### **Class entity/GasStation.java - setLon method**

   **Criteria for method *setLon*:**
  - lon must be a double
  - lon value ranging between -180 and 180


   **Predicates for method *setLon*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | lon instanceof Double == True |
   | Range | lon >= -180.0 && lon <= 180.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= -180.0 && <= 180.0 |
   | | < -180.0 |
   | | > 180.0 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< -180.0|I|setLon(true)| |
   |false|> 180.0|I|setLon("80")| |
   |false|>= -180.0 && <= 180.0|I|setLon(null)| |
   |true|< -180|I|setLon(-182.42)| |
   |true|> 180|I|setLon(188.12)| ||
   |true|>= -180.0 && <= 180.0|V|setLon(45.33)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |

  ### **Class entity/GasStation.java - setReportDependability method**

   **Criteria for method *setReportDependability*:**
  - reportDependability must be a double
  - reportDependability value ranging between 0 and 100


   **Predicates for method *setReportDependability*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | reportDependability instanceof Double == True |
   | Range | reportDependability >= -180.0 && reportDependability <= 180.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= 0 && <= 100 |
   | | < 0 |
   | | > 100 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< 0|I|setReportDependability(true)| |
   |false|> 100|I|setReportDependability("80")| |
   |false|>= 0 && <= 100|I|setReportDependability(null)| |
   |true|< 0|I|setReportDependability(-1)| |
   |true|> 100|I|setReportDependability(102)| ||
   |true|>= 0 && <= 100|V|setReportDependability(100)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |


   ### **Class service/GasStationServiceImpl.java - distance method**

   **Criteria for method *setReportDependability*:**
  - reportDependability must be a double
  - reportDependability value ranging between 0 and 100


   **Predicates for method *setLon*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | reportDependability instanceof Double == True |
   | Range | reportDependability >= -180.0 && reportDependability <= 180.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= 0 && <= 100 |
   | | < 0 |
   | | > 100 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< 0|I|setReportDependability(true)| |
   |false|> 100|I|setReportDependability("80")| |
   |false|>= 0 && <= 100|I|setReportDependability(null)| |
   |true|< 0|I|setReportDependability(-1)| |
   |true|> 100|I|setReportDependability(102)| ||
   |true|>= 0 && <= 100|V|setReportDependability(100)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |


# White Box Unit Tests

### Test cases definition

To make the document more readable, individual units are grouped together.

#### Unit name - it.polito.ezgas.entity.User
void setAdmin(Boolean admin)

void setEmail(String email)

void setPassword(String password)

void setReputation(Integer reputation)

void setUserId(Integer userId)

void setUserName(String userName)

Boolean getAdmin()

String getEmail()

String getPassword()

Integer getReputation()

Integer getUserId()

String getUserName()

User(String userName, String password, String email, Integer reputation)

#### Unit name - it.polito.ezgas.entity.GasStation
void setCarSharing(String carSharing)

void setDieselPrice(double dieselPrice)

void setGasPrice(double gasPrice)

void setGasStationAddress(String gasStationAddress)

void setGasStationId(Integer gasStationId)

void setGasStationName(String gasStationName)

void setHasDiesel(boolean hasDiesel)

void setHasGas(boolean hasGas)

void setHasMethane(boolean hasMethane)

void setHasSuper(boolean hasSuper)

void setHasSuperPlus(boolean hasSuperPlus)

void setLat(double lat)

void setLon(double lon)

void setMethanePrice(double methanePrice)

void setReportDependability(double reportDependability)

void setReportTimestamp(String reportTimestamp)

void setReportUser(Integer reportUser)

void setSuperPlusPrice(double superPlusPrice)

void setSuperPrice(double superPrice)

String getCarSharing()

double getDieselPrice()

double getGasPrice()

String getGasStationAddress()

Integer getGasStationId()

String getGasStationName()

boolean getHasDiesel()

boolean getHasGas()

boolean getHasMethane()

boolean getHasSuper()

boolean getHasSuperPlus()

double getLat()

double getLon()

double getMethanePrice()

double getReportDependability()

GasStation(String gasStationName, String gasStationAddress, boolean hasDiesel, boolean hasSuper, boolean hasSuperPlus, boolean hasGas, boolean hasMethane, String carSharing, double lat, double lon, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer reportUser, String reportTimestamp, double reportDependability)

#### Unit name - it.polito.ezgas.dto.UserDto
void setUserId(Integer userId)

void setUserName(String userName)

void setPassword(String password)

void setEmail(String email)

void setReputation(Integer reputation)

void setAdmin(Boolean admin)

Integer getUserId()

String getUserName()

String getPassword()

String getEmail()

Integer getReputation()

Boolean getAdmin()

UserDto(Integer userId, String userName, String password, String email, Integer reputation)

UserDto(Integer userId, String userName, String password, String email, Integer reputation, Boolean admin)

#### Unit name - it.polito.ezgas.dto.GasStationDto
double getReportDependability()

void setReportDependability(double reportDependability)

Integer getGasStationId()

void setGasStationId(Integer gasStationId)

String getGasStationName()

void setGasStationName(String gasStationName)

String getGasStationAddress()

void setGasStationAddress(String gasStationAddress)

boolean getHasDiesel()

void setHasDiesel(boolean hasDiesel)

Boolean getHasSuper()

void setHasSuper(Boolean hasSuper)

Boolean getHasSuperPlus()

void setHasSuperPlus(Boolean hasSuperPlus)

Boolean getHasGas()

void setHasGas(boolean hasGas)

double getLat()

void setLat(Double lat)

double getLon()

void setLon(Double lon)

double getDieselPrice()

void setDieselPrice(double dieselPrice)

double getSuperPrice()

void setSuperPrice(double superPrice)

double getSuperPlusPrice()

void setSuperPlusPrice(double superPlusPrice)

double getGasPrice()

setGasPrice(double gasPrice)

List<PriceReportDto> getPriceReportDtos()

void setPriceReportDtos(List<PriceReportDto> priceReportDtos)

Integer getReportUser()

setReportUser(Integer reportUser)

String getReportTimestamp()

void setReportTimestamp(String reportTimestamp)

UserDto getUserDto()

setUserDto(UserDto userDto)

boolean getHasMethane()

void setHasMethane(boolean hasMethane)

double getMethanePrice()

void setMethanePrice(double methanePrice)

String getCarSharing()

void setCarSharing(String carSharing)

GasStationDto(Integer gasStationId, String gasStationName, String gasStationAddress, boolean hasDiesel, boolean hasSuper, boolean hasSuperPlus, boolean hasGas, boolean hasMethane, String carSharing, double lat, double lon, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer reportUser, String reportTimestamp, double reportDependability)

#### Unit name - it.polito.ezgas.dto.LoginDto
Integer getUserId()

void setUserId(Integer userId)

String getUserName()

void setUserName(String userName)

String getToken()

void setToken(String token)

String getEmail()

void setEmail(String email)

Integer getReputation()

void setReputation(Integer reputation)

Boolean getAdmin()

void setAdmin(Boolean admin)

LoginDto (Integer userId, String userName, String token, String emaiInteger reputation)

#### Unit name - it.polito.ezgas.dto.IdPw
String getUser()

void setUser(String user)

String getPw()

void setPw(String pw)

IdPw(String id, String pw)

#### Unit to tests mapping recap
| Unit name | JUnit test case |
|--|--|
| it.polito.ezgas.entity.User | EZGasApplicationTests.testUserGetterAndSetter |
| | EZGasApplicationTests.testUserConstructor |
| it.polito.ezgas.entity.GasStation | EZGasApplicationTests.testGasStationGetterAndSetter |
| | EZGasApplicationTests.testGasStationConstructor |
| it.polito.ezgas.dto.UserDto | UserDtoTest.testUserDtoGetterAndSetter |
| | UserDtoTest.testUserDtoConstructor |
| | UserDtoTest.testUserDtoConstructorWithAdmin |
| it.polito.ezgas.dto.GasStationDto | GasStationDtoTest.testGasStationDtoGetterAndSetter |
| | GasStationDtoTest.testGasStationConstructor |
| it.polito.ezgas.dto.LoginDto | LoginDtoTest.testLoginDtoGetterSetter |
| | LoginDtoTest.testLoginDtoConstructor |
| it.polito.ezgas.dto.IdPw | IdPwTest.testIdPwGetterSetter |
| | IdPwTest.testIdPwConstructor |

### Code coverage report

<img src="/docs/TestCoverage/GasStationAndUserCoverage.JPG" alt="GasStationAndUserCoverage"/>

<img src="/docs/TestCoverage/gasStationDtoCoverage.JPG" alt="GasStationDtoCoverage"/>

The coverage of GasStationDto is different from 100% since the tests of getPriceReportDto and setPriceReportDto have not been carried out since the PriceReportDto class is not used by the application.

<img src="/docs/TestCoverage/idPwcoverage.JPG" alt="idPwCoverage"/>

<img src="/docs/TestCoverage/loginDtoCoverage.JPG" alt="LoginDtoCoverage"/>

<img src="/docs/TestCoverage/userDtoCoverage.JPG" alt="UserDtoCoverage"/>

### Loop coverage analysis

The loop coverage analysis was not carried out as none of the units tested have loops inside.

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||
