package com.example.hiringsqlbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitFinalQueryRequest {
    private String finalQuery;
}
