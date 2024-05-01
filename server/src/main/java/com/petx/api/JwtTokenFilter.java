package com.petx.api;

import com.petx.service.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.petx.service.SecurityUserDetailsService;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;

    private final SecurityUserDetailsService userDetailService;

    public JwtTokenFilter(JwtServiceImpl jwtService, SecurityUserDetailsService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            if (jwtService.isTokenValido(token)) {
                String login = jwtService.obterLoginUsuario(token);

                UserDetails usuarioAutenticado = userDetailService.loadUserByUsername(login);
                if(usuarioAutenticado != null){
                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuarioAutenticado, null, usuarioAutenticado.getAuthorities());

                    user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(user);
                } else {
                    UserDetails adminAutenticado = userDetailService.loadUserByAdminName(login);
                    if (adminAutenticado != null) {
                        UsernamePasswordAuthenticationToken admin = new UsernamePasswordAuthenticationToken(adminAutenticado, null, adminAutenticado.getAuthorities());

                        admin.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(admin);
                    }
                }
            }
        }
        filterChain.doFilter((ServletRequest) httpServletRequest, (ServletResponse) httpServletResponse);
    }
}
