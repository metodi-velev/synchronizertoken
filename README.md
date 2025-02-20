# Synchronizer Token Pattern in Spring Boot

This project demonstrates the implementation of the **Synchronizer Token Pattern** in a Spring Boot application to prevent **duplicate form submissions**. It includes features like token generation, validation, and database integration to store tokens securely.

## Features

- **Token Generation**: Unique tokens are generated for each form submission.
- **Token Validation**: Tokens are validated to prevent duplicate submissions.
- **Database Integration**: Tokens are stored in a MySQL database for persistence.
- **Scheduler**: A background task periodically cleans up expired tokens.
- **CSRF Protection**: Prevents Cross-Site Request Forgery (CSRF) attacks.

## Technologies Used

- **Spring Boot**: Backend framework for building the application.
- **MySQL**: Database for storing tokens.
- **Thymeleaf**: Server-side Java template engine for rendering HTML.
- **Lombok**: Reduces boilerplate code with annotations.
- **Git**: Version control system.

## Prerequisites

Before running the project, ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- MySQL Server
- Maven (for building the project)

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/metodi-velev/synchronizertoken.git
   cd synchronizertoken

2. **Configure the Database**:
   - Create a MySQL database named `csrf_db`.
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/csrf_db?createDatabaseIfNotExist=true
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

     # Hibernate configuration
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

3. **Build and Run the Application**:
   - Build the project using Maven:
       ```bash
       mvn clean install
   - Run the application:
       ```bash
       mvn spring-boot:run

4. **Access the Application**:
   - Open your browser and navigate to `http://localhost:8080/form`.

5. **API Endpoints**:
   - Described the two main endpoints:
     - **GET** `/form`: Displays the form with a unique CSRF token.
     - **POST** `/submit`: Handles form submission and validates the token.
       
---

### **Testing the `/submit` Endpoint with Postman**

To test the `/submit` endpoint using **Postman**, follow these steps:

1. **Obtain a Token**:
   - Open Postman and create a first request.
   - Set the request type to **GET**
   - Enter the URL: `http://localhost:8080/form`.
   - Extract the token value from the form (it will be in a hidden input field named token in the Response).

2. **Create a POST Request**:
   - Create a second request in Postman.
   - Set the request type to **POST**.
   - Enter the URL: `http://localhost:8080/submit`.

3. **Set Request Body**:
   - Go to the **Body** tab.
   - Select **x-www-form-urlencoded**.
   - Add the following key-value pairs:
     - `inputData`: Enter any value (e.g., `Test Data`).
     - `token`: Enter the token value obtained from the `/form` endpoint.

4. **Send the Request**:
   - Click **Send** to submit the request.
   - If the token is valid, you will receive a success response. Otherwise, you will receive an error.

---

## Next Steps

Now that you've set up the project, here are some suggestions for what you can do next:

### **1. Explore the Code**
- Dive into the codebase to understand how the Synchronizer Token Pattern is implemented.
- Key files to explore:
  - `FormController.java`: Handles form submission and token validation.
  - `TokenService.java`: Manages token generation, validation, and cleanup.
  - `Token.java`: Entity class representing the token in the database.
  - `application.properties`: Contains database and Hibernate configurations.

### **2. Test the Application**
- Access the form at `http://localhost:8080/form` and submit it multiple times to observe how the token prevents duplicate submissions.
- Check the database (`csrf_db`) to see how tokens are stored and marked as used.

### **3. Enhance the Project**
Here are some ideas for enhancing the project:
- **Token Expiration**: Add a feature to automatically expire tokens after a certain period.
- **User Authentication**: Integrate Spring Security to add user authentication and authorization.
- **Frontend Improvements**: Use a modern frontend framework (e.g., React or Angular) to improve the user interface.
- **Logging**: Add detailed logging to track token generation, validation, and cleanup processes.
- **Unit Tests**: Write unit tests for the `TokenService` and `FormController` classes.

### **4. Debugging and Troubleshooting**
- If you encounter issues, check the following:
  - **Database Connection**: Ensure the MySQL server is running and the credentials in `application.properties` are correct.
  - **Token Validation**: Verify that tokens are being generated and validated correctly by inspecting the logs.
  - **Scheduler**: Ensure the token cleanup task is running as expected by checking the logs for cleanup messages.

### **5. Contribute to the Project**
Contributions are welcome! Here's how you can contribute:
1. Fork the repository.
2. Create a new branch for your feature or bugfix:
   ```bash
   git checkout -b feature/your-feature-name
