package com.bsanju.resumegenerator.controller;

import com.bsanju.resumegenerator.model.Education;
import com.bsanju.resumegenerator.model.Experience;
import com.bsanju.resumegenerator.model.Project;
import com.bsanju.resumegenerator.model.ResumeData;
import com.bsanju.resumegenerator.service.PdfGeneratorService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("resumeData")
public class ResumeController {

    private final PdfGeneratorService pdfGeneratorService;

    public ResumeController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
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
}
