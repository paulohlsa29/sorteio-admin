package br.com.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bank_account_info", schema = "admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountInfo extends BaseEntity{

    @Id
    private Long id;

}
