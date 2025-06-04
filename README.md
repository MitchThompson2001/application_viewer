# Data Viewer Application

## Overview

A web-based viewer application that allows users to query and view patient data without the ability to modify any records. This prototype serves as a foundation for a future project that will integrate into a production-level patient data viewer system.

## Setup Instructions

1. Navigate to src/main/java/com/example/application_viewer/ApplicationViewerApplication

2. Run the program

3. Open a new browser window and search localhost:8080

    * If you are not signed in already, you will be directed to sign in or register an account.

    * If you are signed in, you will be directed to the homepage.

## Security

This progam makes use of:

* spring-boot-starter-security

* thymeleaf-extras-springsecurity6

* spring-security-core

Users have restricted access to webpages unless they are authenticated. Once
they are logged in, they will have controlled access to other webpages.

## Future Improvements

* More robust system for creating an account

* Modify column size for each table view

* Utilize the user roles to manage access to features
