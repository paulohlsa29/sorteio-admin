package br.com.admin.controller.partner;

import br.com.admin.controller.partner.dto.CreatePartnerRequest;
import br.com.admin.enumeration.AuthRole;
import br.com.admin.security.AuthenticatedUser;
import br.com.admin.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestAttribute(name = "user") AuthenticatedUser user,
            @RequestAttribute(name = "token") String token,
                                    @RequestBody CreatePartnerRequest dto) {

        if(AuthRole.AFFILIATE.key().equals(user.getRole()))
            dto.setParentId(user.getId());

        return new ResponseEntity(service.createPartner(token, dto), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listPartners(@RequestAttribute(name = "user") AuthenticatedUser user,
                                          Pageable pageable) {

        if(AuthRole.AFFILIATE.key().equals(user.getRole()))
            return ResponseEntity.ok().body(service.findChildren(user.getId(), pageable));

        return ResponseEntity.ok().body(service.findAll(pageable));
    }

}