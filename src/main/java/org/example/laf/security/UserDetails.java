package org.example.laf.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

@Getter
public class UserDetails extends User {

    private final UUID uuid;

    public UserDetails(String username,
                       String password,
                       Collection<? extends GrantedAuthority> authorities,
                       UUID uuid) {
        super(username, password, authorities);
        this.uuid = uuid;
    }
}