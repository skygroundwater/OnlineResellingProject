package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.DTO;
import com.example.onlineresellingproject.entity.ProjectEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.exceptions.NotValidDataException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.service.OnlineResellingProjectService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Getter
public abstract class AbstractService<M extends ProjectEntity, O, R extends JpaRepository<M, O>, D extends DTO> implements OnlineResellingProjectService<M, O, D> {

    private final R repository;

    private final ModelMapper modelMapper;

    AbstractService(R repository,
                    ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public final M post(M model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public final M patch(M model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public final void delete(O o) {
        if (o != null) {
            repository.deleteById(o);
        } else throw new NotValidDataException();
    }

    @Override
    public final M get(O o) {
        if (o != null) {
            return repository.findById(o).orElseThrow(NotFoundInDataBaseException::new);
        } else throw new NotValidDataException();
    }

    @Override
    public final List<M> findAll() {
        return repository.findAll();
    }
}
