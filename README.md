
# Exa Ecommerce Application  
In this Application Admin can register a Product , update product , delete product and manage them. A customer can register himself/herself and login and after login customer can see all present Product and if customer want to purchase anything then customer can place a order as well.
# Objevtive:
The objective to create this project was to implement all things which I have learned . I made a Backend Application to perform CRUD operation and made RestFul APIs Service for Exa Ecommerce Application. And Enhancing skills in SpringBoot, Java, MySQL , Hibernate and Also learning building end to end application. It is Solo project.

# Modules:
- Product Module 
- Customer Module
- Order Module
- Login Module

## Tech Stacks:

- Java
- Spring Boot
- Swagger-Ui
- Lombok
- MySQL
- SpringData Jpa
- Hibernate


## Features And Functinalities:

- Admin/Customer registration and Login.
- Admin can Perform The All Crud operation on Products or Manage them.
- Customer can See All product , Product by category , product by name, product by id.
- Customer can place a order, delete order ,update order , view all order , view perticular order by id.


##  Backend of The Application 

- Login For customer and Admin.  
- Stored the data In MySQL and that Can be access By only Authenticated User.
- Proper Exception Handling.
- Proper Input Validation. 

## Installation and Run 

You can clone this repo and start the serve on localhost.
Before running the API server, update the database config inside the application.properties file.


  ```
   server.port=8888 
   
   spring.datasource.url=jdbc:mysql://localhost:3306/exa
   
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   
   spring.datasource.username=root
   
   spring.datasource.password=Root
   
   spring.jpa.hibernate.ddl-auto=update 
   
   spring.jpa.show-sql=true
   
   spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
   ```
   
   


## API Root Endpoint 

Link: http://localhost:8888/swagger-ui/

## Roles & Responsibilities :-

- Responsible for All Features

## Team 

👤 [Shimbhu Kumawat](https://github.com/Shimbhu77)



