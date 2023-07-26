package br.com.admin.service;

import br.com.admin.controller.partner.dto.CreatePartnerRequest;
import br.com.admin.controller.partner.dto.ListPartnerResponse;
import br.com.admin.exception.NotSupportedOperationException;
import br.com.admin.exception.ResourceNotFoundException;
import br.com.admin.external.auth.domain.AuthUser;
import br.com.admin.external.auth.domain.CreateUserRequestBody;
import br.com.admin.external.auth.router.AuthRouter;
import br.com.admin.mapper.CreatePartnerRequestMapper;
import br.com.admin.mapper.CreateUserRequestMapper;
import br.com.admin.model.Partner;
import br.com.admin.repository.PartnerRepository;
import br.com.admin.util.AdminUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {
    @Autowired
    protected CreateUserRequestMapper userRequestMapper;
    @Autowired
    protected CreatePartnerRequestMapper partnerRequestMapper;
    private final PartnerRepository repository;
    private final AuthClientService authClientService;
    @Value("${partner.max-level-allowed}")
    private int maxLevelAllowed;

    public Partner createPartner(String token, CreatePartnerRequest dto) {

        Partner parent = null;
        if(dto.getParentId() != null) {
            parent = repository.findById(dto.getParentId()).orElseThrow(ResourceNotFoundException::new);
        }

        String escapedCnpj = AdminUtil.removeSpecialCharacters(dto.getCnpj());

        CreateUserRequestBody userRequestBody = userRequestMapper.toEntity(dto);
        userRequestBody.setNews(Boolean.TRUE);
        userRequestBody.setPolicy(Boolean.TRUE);
        userRequestBody.setPassword(escapedCnpj);
        userRequestBody.setPasswordConfirmation(escapedCnpj);

        AuthUser user = authClientService.create(token, userRequestBody, AuthRouter.CREATE_AFFILIATE_URI).block();

        Partner partner = partnerRequestMapper.toEntity(dto);
        partner.setCnpj(escapedCnpj);
        partner.setId(user.getId());

        if (parent != null) {
            int l = parent.getLevel();
            if (maxLevelAllowed == l)
                throw new NotSupportedOperationException();

            partner.setLevel(l+1);
            partner.setParent(parent);
        }

        return repository.save(partner);
    }

    public Page<ListPartnerResponse> findAll(Pageable pageable) {
        if (pageable == null)
            pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        return repository.findAll(pageable).map(ListPartnerResponse::fromPartner);
    }

    public Page<ListPartnerResponse> findChildren(Long parentId, Pageable pageable) {
        Partner parent = repository.findById(parentId).orElseThrow(ResourceNotFoundException::new);

        if (pageable == null)
            pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        if (parent.getChildren() != null) {
            List<Partner> list = parent.getAllChildren();
            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > list.size() ? list.size()
                    : (start + pageable.getPageSize());
            parent.getChildren();

            return new PageImpl<>(list.subList(start, end), pageable, list.size()).map(ListPartnerResponse::fromPartner);
        }

        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }

}
