package br.com.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.admin.model.BaseEntityInterface;

public interface BaseServiceInterface<E extends BaseEntityInterface> {

    E create(E entity) throws Exception;
    E update(E entity) throws Exception;
    Page<E> findAll(Pageable pageable);
    E findById(Long id);

}
