package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.repository.CommentEntityRepo;
import com.example.onlineresellingproject.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends AbstractServiceImpl<CommentEntity, Long, CommentEntityRepo> implements CommentService {
    CommentServiceImpl(CommentEntityRepo repository) {
        super(repository);
    }
}
