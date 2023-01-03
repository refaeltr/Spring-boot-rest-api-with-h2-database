# Student Management System

## Introduction
This project has implemented crud operations in a simple way with H2 embedded
database, custom exceptions and tests for controller class and service class.
This project can run on a container.

## Environment
- intellij IDE
- Java 19
- Spring boot
- H2 Embedded Database
- Docker
```html
## Operating Instructions
For running the API server on a container use the commands:<br />
1.maven package.<br />
2.run: docker build -t <tag> <br />
3.run: docker run -p <container port:host port> <tag> <br />
```
## Testing
You can use [postamn](https://www.postman.com/) for testing the api.<br/>
There is a [postman collction file](https://github.com/refaeltr/Spring-boot-rest-api-with-h2-database/blob/master/demo/student%20api.postman_collection.json) 
in the project to help you started.<br/>

## Api endpoints:
 
### URL prefix: http://localhost:8080/Hogwarts/api/v1

- ## POST METHODS:

- ### Add student:<br/>
#### /addStudent
Add student with this in the body: <br/>
{ <br/>
    "name": "inigo montoya",<br/>
    "email": "inigo@montoya1.com",<br/>
    "dob": "1987-09-10"<br/>
}<br/>

- ### Add list of students:<br/>
 #### /addStudents
Add list of students with this in the body: <br/>
[{<br/>
    "name": "inigo montoya",<br/>
    "email": "inigo@montoya.com",<br/>
    "dob": "1987-09-10"<br/>
},<br/>
{<br/>
    "name": "Pete Maverick Mitchell",<br/>
    "email": "Maverick@Mitchell.com",<br/>
    "dob": "1986-05-16"<br/>
},<br/>
{<br/>
    "name": "Nick Goose Bradshaw",<br/>
    "email": "Goose@Bradshaw.com",<br/>
    "dob": "1986-05-16"<br/>
}]<br/>


- ## GET METHODS:

### Show all students:<br/>
 #### /students
List all students from the DataBase.

### Find student by ID:<br/>
 #### /student/{studentId}
Find student in the DateBase by studentId.

- ## PUT METHODS:

### Update student name by ID:<br/>
 #### /updateName/{studentId}?name=NAME_TO_UPDATE
Update student name with studentId to NAME_TO_UPDATE.

### Update student email by ID:<br/>
 #### /updateEmail/{studentId}?email=EMAIL_TO_UPDATE
Update student email with studentId to EMAIL_TO_UPDATE.

- ## DELETE METHODS:

### Delete student by ID
 #### /deleteStudent/{studentId}
Delete student with studentId.



