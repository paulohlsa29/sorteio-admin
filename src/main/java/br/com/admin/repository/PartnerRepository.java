package br.com.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.admin.model.Partner;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query("select p from Partner p where p.level = 0")
    Page<Partner> findAllTopLevelPartners(Pageable pageable);

}
