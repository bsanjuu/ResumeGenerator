package com.bsanju.resumegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String university;
    private String degree;
    private String location;
    private String gradYear;
}
