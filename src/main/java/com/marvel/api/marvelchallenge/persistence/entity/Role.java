package com.marvel.api.marvelchallenge.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Setter
@Getter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private List<GrantedPermission> permissionList;

    @Override
    public String getAuthority() {
        if(name == null) return null;
        return "ROLE_" + name.name();
    }

    public static enum RoleEnum{
        CUSTOMER,
        ADMIN
    }
}
