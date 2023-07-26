package br.com.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.admin.exception.ResourceNotFoundException;
import br.com.admin.model.BaseEntityInterface;
import br.com.admin.util.ObjectUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntityInterface, R extends JpaRepository<E, Long>>
        implements BaseServiceInterface<E> {

    protected final R repository;

    @Override
    public E create(E entity) throws Exception {
        return repository.save(entity);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public E findById(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public E update(E entity) throws Exception {
        try {
            E existent = repository.findById(entity.getId()).orElseThrow(ResourceNotFoundException::new);
            ObjectUtil.copy(entity, existent);

            return repository.save(existent);
        } catch (Exception e) {
            throw e;
        }

    }

}
