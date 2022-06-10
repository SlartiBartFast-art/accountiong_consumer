# Client part of the application "Accounting for goods"

[![Build Status](https://app.travis-ci.com/SlartiBartFast-art/accountiong_consumer.svg?branch=master)](https://app.travis-ci.com/SlartiBartFast-art/accountiong_consumer)
![GitHub top language](https://img.shields.io/github/languages/top/SlartiBartFast-art/accountiong_consumer?logo=java&logoColor=red)
![GitHub last commit](https://img.shields.io/github/last-commit/SlartiBartFast-art/accountiong_consumer?logo=github)

RESTful architecture used

Клиентская часть приложения "Учет товаров"

part 2 - under construction 

part 1  - automated_accounting

----
Used technology stack:

- Java Core
- JWT
- Spring Boot (Web)
- Maven
- Docker
- Travis C.I.
- Postman - (Postman is an API platform for building and using API)

GET - pagination query

    - http://localhost:8081/template/product/?pageSize=3&pageNo=1&sortBy=id
![Image of Arch](https://github.com/SlartiBartFast-art/accountiong_consumer/blob/master/image/Screenshot_01.jpg)


    -http://localhost:8081/template/product/?pageSize=5&pageNo=1&sortBy=id&sortDir=asc
![Image of Arch](https://github.com/SlartiBartFast-art/accountiong_consumer/blob/master/image/Screenshot_02.jpg)


GET - all Object List without pagination
  
    - http://localhost:8081/template/product/productsAll
![Image of Arch](https://github.com/SlartiBartFast-art/accountiong_consumer/blob/master/image/Screenshot_03.jpg)

GET - find objects using parameters

    - http://localhost:8081/template/product/products?coloring=red&operator=moreThan&cottonPart=90
![Image of Arch](https://github.com/SlartiBartFast-art/accountiong_consumer/blob/master/image/Screenshot_04.jpg)

GET - find Object by id

    - http://localhost:8081/template/product/{id}
![Image of Arch](https://github.com/SlartiBartFast-art/accountiong_consumer/blob/master/image/Screenshot_05.jpg)
    
POST - update Object

    - http://localhost:8081/template/product/
![Image of Arch](https://github.com/SlartiBartFast-art/accountiong_consumer/blob/master/image/Screenshot_06.jpg)

DELETE - Object by id

    - http://localhost:8081/template/product/{id}
![Image of Arch](https://github.com/SlartiBartFast-art/accountiong_consumer/blob/master/image/Screenshot_07.jpg)

       