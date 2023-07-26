package br.com.admin.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.admin.model.BaseEntityInterface;

public interface BaseControllerInterface<E extends BaseEntityInterface> {

    @PostMapping
    ResponseEntity<?> create(@RequestBody E entity) throws Exception;

    @PutMapping
    ResponseEntity<?> update(@RequestBody E entity) throws Exception;

    @GetMapping("/find-all")
    ResponseEntity<?> findAll(Pageable pageable) throws Exception;

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable("id") Long id) throws Exception;

}
