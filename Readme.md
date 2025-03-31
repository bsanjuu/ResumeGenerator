# Resume Generator

A Spring Boot-based web application that allows users to generate resumes in PDF format based on user input. The application features a clean UI, dynamic preview, and a downloadable PDF resume.

## Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS
- **PDF Generation**: iText or similar library (via `PdfGeneratorService`)
- **Build Tool**: Maven

##  Project Structure

```
ResumeGenerator/
├── controller/              # Handles HTTP requests
├── model/                  # Contains Java models for Resume, Education, etc.
├── service/                # Contains logic for PDF generation
├── templates/              # HTML templates for resume and preview
├── static/css/             # Styling (CSS)
├── application.properties  # Spring Boot configuration
├── ResumeGeneratorApplication.java  # Main class
```

##  Features

- Input resume details such as education, experience, and projects
- Live preview of resume
- Download resume as a professionally formatted PDF

##  How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/bsanjuu/ResumeGenerator.git
   cd ResumeGenerator
   ```

2. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

## ️ UI Templates

- `index.html`: Main input form
- `resume-preview.html`: Preview before generating PDF
- `resume-template.html`: Used to render the final resume layout

##  PDF Generation

PDFs are dynamically generated from user input using `PdfGeneratorService.java`. The service takes data from the form and fills it into the predefined HTML template.

##  Tests

Basic tests included under `src/test/java` using Spring Boot's test framework.

##  License

This project is licensed under the MIT License. Feel free to use and modify.

