# About the App
This application caters need of the requirement. H2 is used for data store. Body has treated as CLOB so that we can get valid data to count the words. 

* Creating an Article by generating slug id
API Endpoint: http://localhost:8080/article
Method POST
Request:
{
  "title": "how to learn spring boot by building an app",
  "description": "success story",
  "body": "A long text... "
}
Response:
{
"id": 3,
"slug": "how to learn spring boot by building an app",
"title": "how-to-learn-spring-boot-by-building-an-app",
"description": "success story",
"body": "Writes a CSV (comma separated values). The file is overwritten if it exists. If only a file name is specified, it will be written to the current working directory. For each parameter, NULL means the default value should be used. For each parameter, NULL means the default value should be used. For each ",
"createdAt": "2020-08-08T06:34:52.903+00:00",
"updatedAt": "2020-08-08T06:34:52.903+00:00"
}
* Modify an Article by generating slug id
API Endpoint: http://localhost:8080/article/slug/how-to-learn-spring-boot-by-building-an-app
Method PUT

* Delete an article by slug id
API Endpoint: http://localhost:8080/article/slug/how-to-learn-spring-boot-by-building-an-app
Method DELETE

* Get the speed of reader
API Endpoint: http://localhost:8080/article/slug/how-to-learn-spring-boot-by-building-an-app
Method GET

Response:
{
"articleId": "how-to-learn-spring-boot-by-building-an-app",
"timeToRead": {
"mins": 100,
"seconds": 7
}
}
# How to run
Import as Maven project and build and run as Spring boot or Java Application

# Script for table
Please refer schema.sql in the resource folder.The spring framework would be running this when application starts. 

Note: The table would be dropped when you restart the application. 

# Drawbacks
Test cases are missing for automation. Planned to do BDD

