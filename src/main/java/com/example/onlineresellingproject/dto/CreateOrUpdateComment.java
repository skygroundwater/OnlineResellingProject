package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrUpdateComment {

    private String text;

}
