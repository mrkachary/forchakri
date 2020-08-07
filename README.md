# About the App
This application caters need of the requirement. H2 is used for data store. Body has treated as CLOB so that we can get valid data to count the words. 

* Creating an Article by generating slug id
API Endpoint: http://localhost:8080/article
Method POST

* Modify an Article by generating slug id
API Endpoint: http://localhost:8080/article/slug/how-to-learn-spring-boot-by-building-an-app
Method PUT

* Delete an article by slug id
API Endpoint: http://localhost:8080/article/slug/how-to-learn-spring-boot-by-building-an-app
Method DELETE

* Get the speed of reader
API Endpoint: http://localhost:8080/article/slug/rukmini-aparna
Method GET

# How to run
Import as Maven project and build and run as Spring boot or Java Application

# Drawbacks
Test cases are missing for automation
