# Student Management System

## Introduction
This project has implemented all the crud operations in a simple way with H2 embedded
database, custom exceptions and tests for controller class and service class.
This project can run on a container.

## Features of this Project
- Show all student
- Add student
- Add list of students
- Find student by ID
- Update student name by ID
- Update student email by ID
- Delete student by ID

## Environment
- intellij IDE
- Java 19
- Spring
- Spring initializr
- H2 Embedded Database
- Docker

## Operating Instructions
For running the API server on a container use the commands:<br />
1.maven clean.<br />
2.maven install.<br />
3."docker build -t springapi-Image_name .".<br />
4."docker run -p 8000:8080 springapi-Image_name".<br />

## Testing
You can use [postamn](https://www.postman.com/) for testing the api.<br/>
There is a [postman collction file](https://github.com/refaeltr/Spring-boot-rest-api-with-h2-database/blob/master/demo/student%20api.postman_collection.json) 
in the project to help you started.<br/>

### URL : http://localhost:8080/Hogwarts/api/v1
- ## POST METHODS:
- ### /addStudent
Add student with this in the body: <br/>
{ <br/>
    "name": "inigo montoya",<br/>
    "email": "inigo@montoya1.com",<br/>
    "dob": "1987-09-10"<br/>
}<br/>

- ### /addStudents
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
- ### /students
List all students from DB.

- ### /student/{studentId}
Find student in the DateBase by studentId.

- ## PUT METHODS:
- ### /updateName/{studentId}?name=NAME_TO_UPDATE
Update student name with studentId to NAME_TO_UPDATE.

- ### /updateEmail/{studentId}?email=EMAIL_TO_UPDATE
Update student email with studentId to EMAIL_TO_UPDATE.

- ## DELETE METHODS:
- ### /deleteStudent/{studentId}
Delete student with studentId.



