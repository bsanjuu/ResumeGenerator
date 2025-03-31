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

import java.util.List;

@Controller
@SessionAttributes("resumeData")
public class ResumeController {

    private final PdfGeneratorService pdfGeneratorService;

    public ResumeController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/resume")
    public String showForm(Model model) {
        ResumeData data = new ResumeData();
        data.setExperienceList(List.of(new Experience()));
        data.setProjectList(List.of(new Project()));
        data.setEducationList(List.of(new Education()));
        model.addAttribute("resumeData", data);
        return "resume-form";
    }

    @PostMapping("/resume")
    public String previewResume(@ModelAttribute ResumeData resumeData, Model model) {
        model.addAttribute("resumeData", resumeData);
        return "resume-preview";
    }

    @PostMapping("/resume/pdf")
    public ResponseEntity<byte[]> generatePdf(@ModelAttribute ResumeData resumeData) throws Exception {
        byte[] pdfBytes = pdfGeneratorService.generatePdf(resumeData);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
