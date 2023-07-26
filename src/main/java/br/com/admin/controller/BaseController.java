package br.com.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import br.com.admin.model.BaseEntity;
import br.com.admin.service.BaseServiceInterface;
import br.com.admin.util.Const;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RequiredArgsConstructor
public abstract class BaseController<E extends BaseEntity, S extends BaseServiceInterface<E>>
        implements BaseControllerInterface<E> {

    protected final S service;

    @Override
    public ResponseEntity<?> create(E entity) throws Exception {
        try {
            E created = service.create(entity);
            return ResponseEntity.ok().body(created);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) throws Exception {
        try {
            Page<E> page = service.findAll(pageable);
            if (page.isEmpty())
                return (ResponseEntity<?>) ResponseEntity.noContent();

            return ResponseEntity.ok().body(page);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    @Override
    public ResponseEntity<?> findById(Long id) throws Exception {
        try {
            if (id == null)
                throw new IllegalArgumentException(Const.ID_CANNOT_BE_NULL.label());

            return ResponseEntity.ok().body(service.findById(id));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<?> update(E entity) throws Exception {
        try {
            return ResponseEntity.ok().body(service.update(entity));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

}