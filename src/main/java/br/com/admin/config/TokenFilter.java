package br.com.admin.config;

import br.com.admin.enumeration.AuthRole;
import br.com.admin.external.auth.domain.SigninResponse;
import br.com.admin.security.AuthenticatedUser;
import br.com.admin.service.AuthClientService;
import br.com.admin.util.AdminUtil;
import br.com.admin.util.Const;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final AuthClientService authClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        SigninResponse authUser = null;
        final String reqTokenHeader = request.getHeader(Const.AUTHORIZATION_HEADER.label());

        if(!AdminUtil.isNullOrEmpty(reqTokenHeader) && AdminUtil.startsWith(reqTokenHeader, Const.BEARER.label())) {
            String token = reqTokenHeader.substring(7);
            authUser = authClient.decodeToken(token).block();
            if (authUser == null || AuthRole.CUSTOMER.key().equalsIgnoreCase(authUser.getUser().getRole())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            request.setAttribute(Const.TOKEN.label(), token);
        }

        if(authUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var adminUser = new AuthenticatedUser(
                    authUser.getUser().getId(),
                    authUser.getUser().getName(),
                    authUser.getUser().getRole()
            );
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(adminUser,
                            null,
                            adminUser.getAuthorities()
                    );

            request.setAttribute(Const.AUTHENTICATED_USER.label(), adminUser);
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }

        filterChain.doFilter(request, response);
    }
}
