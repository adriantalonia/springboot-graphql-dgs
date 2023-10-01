package com.course.graphqldemo.security;

import com.course.graphqldemo.datasource.problemz.entity.Userz;
import com.course.graphqldemo.datasource.problemz.repository.UserzRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class ProblemzAuthenticationProvider implements AuthenticationProvider {

    private UserzRepository userzRepository;

    public ProblemzAuthenticationProvider(UserzRepository userzRepository) {
        this.userzRepository = userzRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = userzRepository.findUserByToken(authentication.getCredentials().toString())
                .orElse(new Userz());

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials().toString(),
                getAuthorities(user.getUserRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
        var authorities = new ArrayList<GrantedAuthority>();

        if (StringUtils.isNotBlank(userRole)) {
            authorities.add(new SimpleGrantedAuthority(userRole));
        }

        return authorities;
    }
}
