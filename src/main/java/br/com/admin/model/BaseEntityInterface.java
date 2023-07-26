package br.com.admin.model;

import java.util.Date;

public interface BaseEntityInterface {

    // Entity id
    void setId(Long id);

    Long getId();

    // Date createdAt
    void setCreatedAt(Date createdAt);

    Date getCreatedAt();

    // Date updatedAt;
    void setUpdatedAt(Date updatedAt);

    Date getUpdatedAt();

    // Long createdBy;
    void setCreatedBy(String createdBy);

    String getCreatedBy();

    // Long updatedBy;
    void setUpdatedBy(String updatedBy);

    String getUpdatedBy();

}
