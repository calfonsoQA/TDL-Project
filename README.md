Coverage: 95%
# To Do List (TDL) Project

The objective of this project is to develop a web based application to interact with a managed SQL database. The application will enable the user to interact with a web page using HTML and JavaScript front end elements that connect to a Java back end API. The application enables the user to Create, Read, Update and Delete entries within the database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What you need to install the software and how to install them


To run this application, you will need:
* Java installed 
* Eclipse IDE
* Maven


### Installing

How to get the development environment running:

1. Clone or fork this repository
2. Import folder as a Maven project within Eclipse

In order to run the Java application within the command line enter the following into the command line and press enter: 
```
$ java -war calfonso-tdl-0.0.1-jar-with-dependencies.war
 
```

![](Supporting%20Documents/OrderReadScreenshot.png)

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

JUnit is used for testing this application. In order to run these tests, right-click on the project within the Eclipse IDE and select "Coverage As", then select "JUnit Test"

## Deployment

1. Open a command line within the project folder i.e. change directory within the command line to "/your-directory/TDL-Project/"
2. Enter the following:
```
$ mvn clean package
 
```
3. Then change directory into target using
```
$ cd target 
```
4. Run the application using:
```
$ java -jar tdl-project-0.0.1-SNAPSHOT.war
 
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Claes Alfonso** - *Implementation and development of working application*
* **Jordan Harrison** - *MyBeanUtils.java class* - [jordanharrison](https://github.com/JHarry444/ims-demo)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* **Savannah Vaithilingam** - *HTML, CSS and JavaScript Development Trainer*
* **Alan Davis** - *Spring Development and Testing Trainer*
* **Jordan Harrison** - *MyBeanUtils.java class provider*