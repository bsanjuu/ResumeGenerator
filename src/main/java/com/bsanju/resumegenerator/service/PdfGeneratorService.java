package com.bsanju.resumegenerator.service;

import com.bsanju.resumegenerator.model.ResumeData;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    private final SpringTemplateEngine templateEngine;

    public PdfGeneratorService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(ResumeData resumeData) throws Exception {
        Context context = new Context();
        context.setVariable("resumeData", resumeData);

        String htmlContent = templateEngine.process("resume-template", context);
        System.out.println(htmlContent);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
            return outputStream.toByteArray();
        }
    }
}
