package com.example.spring_securities_1.utils;

import com.example.spring_securities_1.entities.enums.Permission;
import static com.example.spring_securities_1.entities.enums.Permission.*;
import com.example.spring_securities_1.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.spring_securities_1.entities.enums.Role.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionMapping {

    private static final Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(USER_VIEW,POST_VIEW),
            CREATOR, Set.of(POST_CREATE,POST_UPDATE,USER_UPDATE),
            ADMIN, Set.of(POST_CREATE,USER_UPDATE,POST_UPDATE,USER_DELETE,USER_CREATE,POST_DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthorityForRole(Role role){
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
