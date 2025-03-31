package com.bsanju.resumegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeData {
    private String name;
    private String fullName;
    private String email;
    private String phone;
    private String website;
    private String linkedin;
    private String github;
    private String languages;
    private String skills;
    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Project> projectList;
    private List<String> technicalSkills;
    private List<String> softSkills;
}
