package com.example.onlineresellingproject.dto.comment;

import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrUpdateComment extends CommentDTO {

    private String text;

}