# Bank-microservice
Simple bank application with ability to manage accounts, transfer money between accounts and manage related transactions.

## Environment:
- Java version: 11
- Maven
- Spring Boot version: 2.7.6.RELEASE

## General requirements:
- SQL database should be used for storing accounts and transactions.
- Application should be able to work in multithreading environment.
- All responses with errors should have the same format.
- New transaction should be created every time when amount of money is changed in account.
- All layers of the application should be covered by unit tests.
