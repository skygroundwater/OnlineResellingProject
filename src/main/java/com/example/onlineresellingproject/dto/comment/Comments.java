package com.example.onlineresellingproject.dto.comment;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comments {

    private Integer count;

    private List<Comment> results;

}
