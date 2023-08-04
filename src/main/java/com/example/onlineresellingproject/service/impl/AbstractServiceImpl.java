package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.entity.OnlineResellingProjectAbstractModel;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.exceptions.NotValidDataException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.service.OnlineResellingProjectService;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
abstract class AbstractServiceImpl<M extends OnlineResellingProjectAbstractModel, O, R extends JpaRepository<M, O>> implements OnlineResellingProjectService<M> {

    private final R repository;

    AbstractServiceImpl(R repository) {
        this.repository = repository;
    }

    public M post(M model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    public M patch(M model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    public void delete(O o) {
        if (o != null) {
            repository.deleteById(o);
        } else throw new NotValidDataException();
    }

    public M get(O o) {
        if (o != null) {
            return repository.findById(o).orElseThrow(NotFoundInDataBaseException::new);
        } else throw new NotValidDataException();
    }
}
