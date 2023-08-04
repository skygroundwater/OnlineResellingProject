package com.example.onlineresellingproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Comments {

    private Integer count;

    private List<Comment> comments;
}
