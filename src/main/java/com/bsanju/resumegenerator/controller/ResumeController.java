package com.bsanju.resumegenerator.controller;

import com.bsanju.resumegenerator.model.Education;
import com.bsanju.resumegenerator.model.Experience;
import com.bsanju.resumegenerator.model.Project;
import com.bsanju.resumegenerator.model.ResumeData;
import com.bsanju.resumegenerator.service.DeepSeekService;
import com.bsanju.resumegenerator.service.PdfGeneratorService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("resumeData")
public class ResumeController {

    private final PdfGeneratorService pdfGeneratorService;
    private final DeepSeekService deepSeekService;

    public ResumeController(PdfGeneratorService pdfGeneratorService, DeepSeekService deepSeekService) {
        this.pdfGeneratorService = pdfGeneratorService;
        this.deepSeekService = deepSeekService;
    }

    @GetMapping("/")
    public String Home()
    {
        return "index";
    }
    @ModelAttribute("resumeData")
    public ResumeData getResumeData() {
        ResumeData resumeData = new ResumeData();
        resumeData.setExperienceList(new ArrayList<>(List.of(new Experience())));
        resumeData.setProjectList(new ArrayList<>(List.of(new Project())));
        resumeData.setEducationList(new ArrayList<>(List.of(new Education())));
        resumeData.setTechnicalSkills(new ArrayList<>());
        resumeData.setSoftSkills(new ArrayList<>());
        return resumeData;
    }

    @GetMapping("/resume")
    public String showForm(Model model) {
        if (!model.containsAttribute("resumeData")) {
            model.addAttribute("resumeData", getResumeData());
        }
        return "index";
    }

    @PostMapping("/resume")
    public String handleForm(@ModelAttribute("resumeData") ResumeData resumeData,
                             @RequestParam(value = "action", required = false, defaultValue = "submit") String action,
                             Model model) {
        switch (action) {
            case "addExperience":
                resumeData.getExperienceList().add(new Experience());
                break;
            case "addProject":
                resumeData.getProjectList().add(new Project());
                break;
            case "addEducation":
                resumeData.getEducationList().add(new Education());
                break;
            case "submit":
                model.addAttribute("resumeData", resumeData);
                return "resume-preview";
        }
        model.addAttribute("resumeData", resumeData);
        return "index";
    }

    @PostMapping("/resume/pdf")
    public ResponseEntity<byte[]> generatePdf(@ModelAttribute("resumeData") ResumeData resumeData) throws Exception {
        byte[] pdfBytes = pdfGeneratorService.generatePdf(resumeData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @PostMapping("/resume/generate-description")
    public String generateExperienceDescription(
            @ModelAttribute("resumeData") ResumeData resumeData,
            Model model) {

        if (!resumeData.getExperienceList().isEmpty()) {
            Experience exp = resumeData.getExperienceList().get(0);

            // Create prompt with proper line breaks
            String prompt = "Generate exactly 5-6 concise bullet points of roles and responsibilities for a " +
                    exp.getTitle() + " at " + exp.getCompany() + ". " +
                    "Required format for each point:" +
                    System.lineSeparator() +
                    "- Start with strong action verb (Designed, Developed, Implemented, etc.)" +
                    System.lineSeparator() +
                    "- Include 1-2 key technologies from: " + String.join(", ", resumeData.getTechnicalSkills()) +
                    System.lineSeparator() +
                    "- Focus on achievements/impact (quantify when possible)" +
                    System.lineSeparator() +
                    "- Each point must be 1 sentence only" +
                    System.lineSeparator() +
                    "Example format:" +
                    System.lineSeparator() +
                    "- Designed microservices using Spring Boot that reduced API latency " +
                    System.lineSeparator() +
                    "- Implemented AWS cloud solutions cutting infrastructure costs";

            String aiText = deepSeekService.generateDescription(prompt);

            // Clean up response
            String cleanedText = aiText.lines()
                    .filter(line -> line.startsWith("- "))
                    .collect(Collectors.joining(System.lineSeparator()));

            exp.setDescription(cleanedText);
        }

        model.addAttribute("resumeData", resumeData);
        return "index";
    }

}
