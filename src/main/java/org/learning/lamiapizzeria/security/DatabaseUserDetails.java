package org.learning.lamiapizzeria.security;

import org.learning.lamiapizzeria.model.Role;
import org.learning.lamiapizzeria.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUserDetails implements UserDetails {

    private String password;

    private String username;

    private Integer id;

    private Set<GrantedAuthority> authorities;


    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.username = user.getUsername();
        authorities = new HashSet<GrantedAuthority>();
        for (Role r :
                user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
