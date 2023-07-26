package br.com.admin.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "partner", schema = "admin")
@JsonIgnoreProperties({ "hibernate_lazy_initializer", "handler" })
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Partner extends BaseEntity {

    @Id
    private Long id;

    private String cnpj;

    private String tradeName;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    private BigDecimal salesCommission;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    private BigDecimal clickCommission;

    @Column(name = "level", columnDefinition = "integer default 0")
    private int level;

    @Column(columnDefinition = "boolean default false")
    private Boolean active;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Partner parent;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private Set<Partner> children;

    @JsonIgnore
    public List<Partner> getAllChildren() {
        return getAllChildren(this);
    }

    private List<Partner> getAllChildren(Partner parent) {
        List<Partner> allChildren = new ArrayList<>();

        for(Partner child : parent.getChildren()) {
            allChildren.add(child);
            allChildren.addAll(getAllChildren(child));
        }
        return allChildren;
    }

}
