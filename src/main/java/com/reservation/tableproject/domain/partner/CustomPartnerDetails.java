package com.reservation.tableproject.domain.partner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomPartnerDetails implements UserDetails {

    private Partner partner;

    public CustomPartnerDetails(Partner partner) {
        this.partner = partner;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_PARTNER");
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return partner.getPassword();
    }

    @Override
    public String getUsername() {
        return partner.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

