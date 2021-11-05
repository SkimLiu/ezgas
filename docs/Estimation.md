# Project Estimation  

Authors: Michele Filippini, Giovanni Brignone, Andrea Zappavigna, Dong Liu

Date: 24/04/2020

Version: 1

# Contents



- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Estimation approach

<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>

# Estimate by product decomposition



### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   |        8                   |             
|  A = Estimated average size per class, in LOC       |         150                   | 
| S = Estimated size of project, in LOC (= NC * A) | 1200|
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  |  120                                    |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) |3600 | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 0.75                   |               


# Estimate by activity decomposition



### 

| Activity name    		| Estimated effort (person hours) |
| :-----------: 		| :-----------------------------: | 
| Requirement engineering	| 20	|
| Requirement inspection	| 2	|
| Architecture and design	| 15	|
| Design inspection		| 1	|
| Implementation		| 45	|
| Code inspection 		| 10	|
| Test 				| 20	|
| Project management 		| 5	|
| Configuration management 	| 2	|


###

```plantuml
Project starts the 2020/04/8
[Requirement engineering & inspection] as [RE] lasts 1 days
[RE] is colored in lightblue/blue
[Requirement document] happens at [RE]'s end
[Architecture and design & inspection] as [AD] lasts 1 days
[AD] starts at [RE]'s end
[AD] is colored in fuchsia/firebrick
[Design document] happens at [AD]'s end
[Implementation] as [I] lasts 2 days
[I] starts at [AD]'s end
[Code inspection] as [CI] lasts 1 days
[CI] starts at [I]'s end
[Test] as [T] lasts 1 days
[T] starts at [I]'s end
[Executable] happens at [T]'s end
[Project management] as [PM] starts at [RE]'s start and ends at [T]'s end
[Configuration management] as [CM] starts at [RE]'s start and ends at [T]'s end
[PM] is colored in yellow/red
[CM] is colored in yellow/red
```
