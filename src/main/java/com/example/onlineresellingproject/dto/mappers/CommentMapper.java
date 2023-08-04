package com.example.onlineresellingproject.dto.mappers;

import com.example.onlineresellingproject.dto.comment.CommentDTO;
import com.example.onlineresellingproject.entity.CommentEntity;

public interface CommentMapper<C extends CommentDTO> extends Mapper<CommentEntity, C> {

}
