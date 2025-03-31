package com.bsanju.resumegenerator.service;

import com.bsanju.resumegenerator.model.ResumeData;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    private final TemplateEngine templateEngine;

    public PdfGeneratorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(ResumeData resumeData) throws Exception {
        Context context = new Context();
        context.setVariable("resumeData", resumeData);
        String html = templateEngine.process("resume-template", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            renderer.createPDF(baos);
            return baos.toByteArray();
        }
    }
}
