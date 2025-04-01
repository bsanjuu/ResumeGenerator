
# Resume Generator

## Overview

**Resume Generator** is a powerful Java-based web application built with **Spring Boot** and **DeepSeek AI** integration. The application allows users to generate customized resumes by leveraging AI capabilities to enhance and format content. It enables users to preview their resumes and download them in a clean, professional PDF format.

The backend is implemented using **Spring Boot**, with AI integration to optimize and personalize the resume content based on user input. It also includes the ability to generate PDF resumes on the fly using dynamic templates.

## Features

- **Resume Generation**: AI-powered resume content creation using DeepSeek AI.
- **Dynamic Preview**: View and customize your resume live before generating the PDF.
- **Download Resume**: Generate and download a resume in PDF format.
- **User-friendly Interface**: Simple and intuitive HTML templates for users to fill out resume details.
- **Spring Boot**: The backend is built with Spring Boot for easy scalability and deployment.

## Tech Stack

- **Backend**: Java, Spring Boot
- **AI**: DeepSeek AI integration for resume content generation.
- **Frontend**: HTML, CSS (with `styles.css` for styling)
- **PDF Generation**: Java-based PDF generation service.
- **Build Tool**: Maven (`pom.xml` for dependencies)
- **Version Control**: GitHub

## Project Structure

```
├── pom.xml               # Maven project descriptor
├── src                   # Source code for the project
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── bsanju
│   │   │           └── resumegenerator
│   │   │               ├── AppConfig.java         # Application configuration
│   │   │               ├── ResumeGeneratorApplication.java  # Main Spring Boot application
│   │   │               ├── controller
│   │   │               │   └── ResumeController.java   # Controller for handling resume requests
│   │   │               ├── dto
│   │   │               │   ├── DeepSeekRequest.java  # Request DTO for AI API
│   │   │               │   └── DeepSeekResponse.java # Response DTO for AI API
│   │   │               ├── model
│   │   │               │   ├── Education.java       # Model for education data
│   │   │               │   ├── Experience.java      # Model for experience data
│   │   │               │   ├── Project.java         # Model for project data
│   │   │               │   └── ResumeData.java      # Model for resume data
│   │   │               └── service
│   │   │                   ├── DeepSeekService.java  # Service for handling AI-powered content generation
│   │   │                   └── PdfGeneratorService.java # Service for PDF resume generation
│   ├── resources
│   │   ├── application.properties # Configuration properties
│   │   ├── static
│   │   │   └── css
│   │   │       └── styles.css      # Styles for frontend pages
│   │   └── templates
│   │       ├── index.html          # Main page template
│   │       ├── resume-preview.html # Resume preview template
│   │       └── resume-template.html # Template for resume structure
│   └── test
│       └── java
│           └── com
│               └── bsanju
│                   └── resumegenerator
│                       └── ResumeGeneratorApplicationTests.java # Test cases for the application
└── target                 # Compiled and packaged application
```

## Installation

### Prerequisites

- JDK 11 or later
- Maven
- DeepSeek API credentials (if applicable)

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/bsanjuu/ResumeGenerator.git
   cd ResumeGenerator
   ```

2. Build the application using Maven:

   ```bash
   ./mvnw clean install
   ```

3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Visit `http://localhost:8080` to use the resume generator.

### Frontend Customization

You can modify the `index.html`, `resume-preview.html`, and `resume-template.html` files to adjust the appearance and flow of the web interface. All styles are defined in `static/css/styles.css`.

## Usage

1. Open your browser and navigate to `http://localhost:8080`.
2. Enter your personal information, education details, work experience, and projects.
3. Click **Generate Resume** to preview your resume.
4. Once you're happy with the preview, click **Download Resume** to download your personalized resume in PDF format.

## Contribution

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License.






