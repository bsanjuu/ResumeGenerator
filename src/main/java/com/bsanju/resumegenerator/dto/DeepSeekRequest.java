package com.bsanju.resumegenerator.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeepSeekRequest {
    private String prompt;
    private String model;
    private int max_tokens = 500;
}