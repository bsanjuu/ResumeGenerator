package com.bsanju.resumegenerator.dto;

import lombok.Data;
import java.util.List;

@Data
public class DeepSeekResponse {
    private List<Choice> choices;

    @Data
    public static class Choice {
        private String text;
    }
}