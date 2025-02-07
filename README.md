# 🏥 Medical Records Management System (HR Department)

🎯 Objective
This project aims to develop a RESTful API for managing employee medical records in the HR department. The system is built using Java 17+ , PostgreSQL , and adheres to modern software design patterns such as Repository Pattern , Service Pattern , and  Java Bean Validation API.

🛠️ Mandatory Technical Requirements
Java 17+
Spring Boot (Framework for building the RESTful API)
PostgreSQL (local environment)
JPA + Hibernate (Database First approach using spring.jpa.hibernate.ddl-auto=none or hbm2ddl=validate)
JPA Annotations (equivalent to Fluent API in Entity Framework Core)
AutoMapper Equivalent : MapStruct or ModelMapper (not fully implemented due to dependency issues)
Repository Pattern
Service Pattern
Swagger for API documentation
Jakarta Bean Validation (equivalent to FluentValidation)


⚙️ Installation Instructions
1. Local PostgreSQL Installation
   Before running the project, ensure you have PostgreSQL installed on your local machine. Follow these steps:

   🖥️ For Windows:
    1. Download PostgreSQL from the official website .
    2. Run the installer and follow the prompts.
    3. Set a password for the postgres user during installation.
    4. Verify the installation by running:
       bash psql --version

2. Database Script Execution
   Run the following SQL script to create the required database and tables:


3. Running Migration with Database-First Approach
   I use JPA + Hibernate with the spring.jpa.hibernate.ddl-auto=none configuration. This ensures that the database schema is validated against the entity mappings but does not modify the database schema automatically.

To generate entities from the database schema:

- Use tools like Hibernate Tools or Spring Data JPA to reverse-engineer the database schema into Java entities.
- Ensure your application.properties file is configured correctly:

spring.datasource.url=jdbc:postgresql://localhost:5432/medical_records
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true


4. Project Configuration
   Clone the repository:
   git clone https://github.com/your-repo-url.git
   cd medical-records-management

Build the project using Maven:
mvn clean install

Run the application:
mvn spring-boot:run

Access the Swagger API documentation:
http://localhost:8086/swagger-ui/index.html


🧪 Testing the API
You can test the API endpoints using Swagger or tools like Postman . Here are some example endpoints:

GET /api/employees: Retrieve all employees.
POST /api/employees: Create a new employee.
GET /api/medical-records: Retrieve all medical records.
POST /api/medical-records: Add a new medical record.


🚨 Notes
Due to time constraints and dependency issues, I was unable to fully implement AutoMapper . However, I identified MapStruct and ModelMapper as suitable alternatives in Java. These libraries provide similar functionality for object mapping.


In the application.properties file, the configurations for the PostgreSQL database are defined as follows:



server.port=8085

spring.datasource.url=jdbc:postgresql://postgres-container:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

Endpoints and their HTTP verbs for correct functionality:
GET: Retrieve all medical records with mandatory pagination and optional filtering.
Example URL with only pagination:
http://localhost:8086/medical-records/filter?page=0&pageSize=10

Filtering options:

STATUS_ID, START_DATE, END_DATE, and MEDICAL_RECORD_TYPE_ID
Example URLs with filtering:

http://localhost:8086/medical-records/filter?page=0&pageSize=10&statusId=1
http://localhost:8086/medical-records/filter?page=0&pageSize=10&endDate=2028-01-01


GET by ID: Retrieve a specific medical record by ID.
This provides a more detailed and formatted description of the medical record.

Example URL:
http://localhost:8086/medical-records/7

Example Response:


{
"success": true,
"message": "Medical record found successfully.",
"data": {
"medicalRecordId": 7,
"fileId": 123,
"audiometry": "YES",
"positionChange": "NO",
"motherData": "Example data",
"diagnosis": "Test diagnosis",
"otherFamilyData": "More family info",
"fatherData": "Father's history",
"executeMicros": "YES",
"executeExtra": "NO",
"voiceEvaluation": "YES",
"deletionDate": null,
"creationDate": "2025-02-07",
"modificationDate": null,
"endDate": null,
"startDate": "2024-02-07",
"status": {
"statusId": 1,
"name": "Active",
"description": null
},
"medicalRecordType": {
"medicalRecordTypeId": 2,
"name": "Special",
"description": "Special medical record"
},
"disability": "NO",
"medicalBoard": "Board details",
"deletionReason": null,
"observations": "Some observations",
"disabilityPercentage": 20,
"deletedBy": null,
"createdBy": "doctor123",
"modifiedBy": "assistant456",
"areaChange": "YES"
},
"code": 200,
"totalRows": null,
"exception": null
}


Successful response for both GET methods:
HTTP Response Code: 200 OK

POST: Create a new medical record.
Mandatory fields must be validated and comply with validation rules.

Mandatory fields:

fileId, startDate, statusId, medicalRecordTypeId, and createdBy are required.
The observations field is mandatory when positionChange = 'YES'.
disabilityPercentage must be between 0 and 100 when disability = 'YES'.
Note: These validation rules also apply to the UPDATE method.

Example URL:
http://localhost:8086/medical-records

Expected Response:
{
"fileId": 12345,
"audiometry": "YES",
"positionChange": "NO",
"motherData": "Healthy with no prior conditions.",
"diagnosis": "Mild Hypertension",
"otherFamilyData": "No major illnesses in the family.",
"fatherData": "Diabetic with controlled glucose levels.",
"executeMicros": "YES",
"executeExtra": "NO",
"voiceEvaluation": "YES",
"startDate": "2025-01-01",
"statusId": 1,
"medicalRecordTypeId": 1,
"disability": "NO",
"medicalBoard": "Board approved for regular check-ups.",
"observations": "Patient is in stable condition with no major concerns.",
"disabilityPercentage": 0,
"createdBy": "Dr. Smith",
"modifiedBy": "Dr. Johnson",
"areaChange": "NO"
}

HTTP Response Code: 201 Created



UPDATE: Update an existing medical record.
Mandatory fields must be validated and comply with validation rules.
For this operation, I used the POST verb.

Example URL:
http://localhost:8086/medical-records/update/7

HTTP Response Code:
200 OK

Mandatory fields:

fileId, startDate, statusId, medicalRecordTypeId, and modifiedBy are required.
The observations field is mandatory when positionChange = 'YES'.
disabilityPercentage must be between 0 and 100 when disability = 'YES'.
Users are allowed to edit any data as long as status = 1.


DELETE: Delete a medical record (logical deletion).
Example URL:
http://localhost:8086/medical-records/delete/7

Note: The following fields are mandatory (example JSON):

{
"deletedBy": "adminUser",
"deletionReason": "Obsolete record due to data updates",
"endDate": "2025-12-31"
}

The endDate must be after the startDate.

HTTP Response Code for successful operation:
200 OK



Additional HTTP Response Codes:
If any complications arise while using an endpoint, these are the possible response codes:

400 Bad Request: Validation errors
404 Not Found: Resource not found
500 Internal Server Error: Unhandled errors


How to Run the Project?

If You Have Docker:
Use the command in the terminal:
docker-compose up --build


If You Don’t Have Docker:

1)Install JDK (to ensure Java runs properly).
2)Configure the application.properties file to connect to your database.
3)Navigate to the HrMedicalRecordsManagementSystemApplication file and run the application.



You can then test the endpoints using tools like POSTMAN with the corresponding URLs.








