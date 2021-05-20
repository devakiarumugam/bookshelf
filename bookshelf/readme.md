# Getting Started

This Project is to manage books in a library.
 
Attributes of the book:
- ISBN
- Title
- Author
- Tags (multiple)
 
Functional Features:
- CRUD interface to access the books by ISBN
- Search API by any of the attributes of the books
- Rest API to Import books from CSV (or any other format you prefer)
 
### Building and Runing the application

Build the application by running the ./gradlew clean build gradle command at the project root folder on the terminal.

gradlew.bat build jacocoTestReport

If you want to run the application as jar file, then run build/libs/book.shelf-0.0.1-SNAPSHOT.jar command at the terminal.

gradlew.bat bootRun


### Swagger UI & H2 Console
Please view H2 database console and Swagger UI Below after starting the application:

http://localhost:8080/h2/login.do?jsessionid=cdc9958e608ffa96909f375ca46d7f0d
http://localhost:8080/swagger-ui.html#!/Book_Shelf_API/deleteUsingDELETE
