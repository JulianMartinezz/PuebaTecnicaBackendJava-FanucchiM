# Technical Test: HR Medical Records Management System

## Objective
Develop a medical records management system for the HR department by implementing a RESTful API that allows management of employee medical records.

## Mandatory Technical Requirements
- JAVA 17+
- PostgreSQL **(local environment)**
- Entity Framework Core (Database First, Fluent API)
- AutoMapper
- Repository Pattern
- Service Pattern
- Swagger for API documentation
- FluentValidation

## Database Structure

### Database Name: **RRHH_DB**

### Table: T_MEDICAL_RECORD
```sql
CREATE TABLE T_MEDICAL_RECORD (
    MEDICAL_RECORD_ID SERIAL PRIMARY KEY,
    FILE_ID INTEGER, -- FILE_ID represents the person to whom the MEDICAL_RECORD belongs, but it is not found in the database.
    AUDIOMETRY VARCHAR(2),
    POSITION_CHANGE VARCHAR(2),
    MOTHER_DATA VARCHAR(2000),
    DIAGNOSIS VARCHAR(100),
    OTHER_FAMILY_DATA VARCHAR(2000),
    FATHER_DATA VARCHAR(2000),
    EXECUTE_MICROS VARCHAR(2),
    EXECUTE_EXTRA VARCHAR(2),
    VOICE_EVALUATION VARCHAR(2),
    DELETION_DATE DATE,
    CREATION_DATE DATE,
    MODIFICATION_DATE DATE,
    END_DATE DATE,
    START_DATE DATE,
    STATUS_ID INTEGER,
    MEDICAL_RECORD_TYPE_ID INTEGER,
    DISABILITY VARCHAR(2),
    MEDICAL_BOARD VARCHAR(200),
    DELETION_REASON VARCHAR(2000),
    OBSERVATIONS VARCHAR(2000),
    DISABILITY_PERCENTAGE NUMERIC(10),
    DELETED_BY VARCHAR(2000),
    CREATED_BY VARCHAR(2000),
    MODIFIED_BY VARCHAR(2000),
    AREA_CHANGE VARCHAR(2)
);

CREATE TABLE STATUS (
    STATUS_ID SERIAL PRIMARY KEY,
    NAME VARCHAR(100),
    DESCRIPTION VARCHAR(500)
);

CREATE TABLE MEDICAL_RECORD_TYPE (
    MEDICAL_RECORD_TYPE_ID SERIAL PRIMARY KEY,
    NAME VARCHAR(100),
    DESCRIPTION VARCHAR(500)
);

ALTER TABLE T_MEDICAL_RECORD
ADD CONSTRAINT FK_STATUS_ID_RECORD
FOREIGN KEY (STATUS_ID) REFERENCES STATUS(STATUS_ID);

ALTER TABLE T_MEDICAL_RECORD
ADD CONSTRAINT FK_MEDICAL_RECORD_TYPE
FOREIGN KEY (MEDICAL_RECORD_TYPE_ID) REFERENCES MEDICAL_RECORD_TYPE(MEDICAL_RECORD_TYPE_ID);

-- Initial test data
INSERT INTO STATUS (NAME, DESCRIPTION) VALUES 
('Active', 'Active medical record'),
('Inactive', 'Inactive medical record');

INSERT INTO MEDICAL_RECORD_TYPE (NAME, DESCRIPTION) VALUES 
('Regular', 'Regular medical record'),
('Special', 'Special medical record');
```

## Functional Requirements

### 1. Endpoints to Implement
- **GetFilterMedicalRecords**: List of medical records with pagination and filters. It should be possible to filter by STATUS_ID (optional), START_DATE (optional), END_DATE (optional), and MEDICAL_RECORD_TYPE_ID (optional).
    Page and PageSize are mandatory parameters.
- **GetMedicalRecordById**: Retrieve medical record by ID. Get a more detailed and formatted description of the medical record.
- **AddMedicalRecord**: Create new medical record. Mandatory fields must be validated and comply with validation rules.
- **UpdateMedicalRecord**: Update existing medical record. Mandatory fields must be validated and comply with validation rules.
- **DeleteMedicalRecord**: Delete medical record (logical deletion)

### 2. Required Validations

#### 2.1 Date Controls
- START_DATE cannot be later than END_DATE
- START_DATE cannot be a future date
- If END_DATE exists, it must be later than START_DATE
- CREATION_DATE is mandatory and must be auto-generated when inserting the record

#### 2.2 Required Fields
The following fields are mandatory:
- DIAGNOSIS
- START_DATE
- STATUS_ID
- MEDICAL_RECORD_TYPE_ID
- FILE_ID
- CREATED_BY

#### 2.3 Related Records Validation
- STATUS_ID must exist in the STATUS table
- MEDICAL_RECORD_TYPE_ID must exist in the MEDICAL_RECORD_TYPE table
- A record cannot be deleted without providing DELETION_REASON
- When creating or modifying a record, the status must be valid according to these rules:
  * Cannot assign 'Inactive' status when creating a new record
  * To change to 'Inactive' status, DELETION_REASON must be provided

#### 2.4 Maximum Length Validation
- DIAGNOSIS: maximum 100 characters
- MOTHER_DATA: maximum 2000 characters
- FATHER_DATA: maximum 2000 characters
- OTHER_FAMILY_DATA: maximum 2000 characters
- MEDICAL_BOARD: maximum 200 characters
- DELETION_REASON: maximum 2000 characters
- OBSERVATIONS: maximum 2000 characters
- Two-character fields (must be 'YES' or 'NO'):
  * AUDIOMETRY
  * POSITION_CHANGE
  * EXECUTE_MICROS
  * EXECUTE_EXTRA
  * VOICE_EVALUATION
  * DISABILITY
  * AREA_CHANGE

#### 2.5 Valid Status Control
Allowed statuses and their rules:
1. Active (ID: 1)
   - Default initial status for new records
   - Allows modification of all fields
   - Requires CREATED_BY

2. Inactive (ID: 2)
   - Requires DELETION_REASON
   - Requires END_DATE
   - Requires DELETED_BY
   - Does not allow subsequent modifications
   - Must record DELETION_DATE

#### 2.6 Additional Validation Rules
- DISABILITY_PERCENTAGE must be between 0 and 100 when DISABILITY = 'YES'
- If POSITION_CHANGE = 'YES', OBSERVATIONS field is mandatory
- If END_DATE exists, the record must change to Inactive status
- CREATED_BY, MODIFIED_BY, and DELETED_BY must record the user performing the operation
- MODIFICATION_DATE must be automatically updated when modifying any field

### 3. Response Handling

#### 3.1 Base Response Model
```csharp
public class BaseResponse<T>
{
    public bool? Success { get; set; }
    public string? Message { get; set; }
    public T? Data { get; set; }
    public int? Code { get; set; }
    public int? TotalRows { get; set; }
    public string? Exception { get; set; }
}
```

#### 3.2 HTTP Response Codes
- 200 OK: Successful request (GET, PUT/PATCH)
- 400 Bad Request: Validation errors
- 404 Not Found: Resource not found
- 500 Internal Server Error: Unhandled errors

## Deliverables
1. Git repository with complete source code
2. Installation and execution instructions in README

## Evaluation Criteria
- Code structure and organization
- Correct implementation of requested patterns
- Validation and exception handling
- Proper use of AutoMapper
- Clean and properly commented code
- Correct application of Gitflow (Main -> Development -> Feature)

## Delivery Time
- 5 business days from test receipt
- Delivery will be confirmed through creation of a Release PR to main

## Installation Instructions
The candidate must provide clear instructions for:
1. Local PostgreSQL installation
2. Database script execution
3. Running migration with database-first approach using EF with Fluent API
4. Project configuration

## Extras (Not Mandatory)
- Docker

## Delivery Format
- Git repository (GitHub, GitLab, Bitbucket)
- Repository must include this README updated with specific installation instructions for your implementation

---
*Note: For any questions or clarifications about requirements, please create an issue in the repository.*





# Matias Fanucchi Prueba Tecnica Backend Java


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
-Use the command in the terminal:
docker-compose up --build
Important: When running the command in the terminal, you must be located at the same level as the Dockerfile.



If You Don’t Have Docker:

1)Install JDK (to ensure Java runs properly).
2)Configure the application.properties file to connect to your database.
3)Navigate to the HrMedicalRecordsManagementSystemApplication file and run the application.



You can then test the endpoints using tools like POSTMAN with the corresponding URLs.








