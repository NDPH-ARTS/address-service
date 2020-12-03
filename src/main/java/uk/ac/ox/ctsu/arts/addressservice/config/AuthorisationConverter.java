package uk.ac.ox.ctsu.arts.addressservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import uk.ac.ox.ctsu.arts.addressservice.model.Role;
import uk.ac.ox.ctsu.arts.addressservice.model.RoleRepository;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AuthorisationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Autowired
    private RoleRepository roleRepository;

    private String PERMISSION_PREFIX= "PERMISSION_";
    private String ROLE_PREFIX= "ROLE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Object rolesInToken = jwt.getClaims().get("roles");
        if(rolesInToken!=null){
            List<String> roleClaims = (List) rolesInToken;
            authorities.addAll(roleClaims.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.toUpperCase())).collect(Collectors.toList()));
            authorities.addAll(getPermissionsForRoles(roleClaims));
        }
        return authorities;

        // TODO deal with scope claims too?  Spring's default behaviour is to turn 'read' into a GrantedAuthority called 'SCOPE_read' etc but we have overridde
    }

    protected Collection<GrantedAuthority> getPermissionsForRoles(List<String> roleClaims){ // from DB.
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String roleName : roleClaims){
            Optional<Role> role = roleRepository.findById(roleName);

            if(role.isPresent()){
                authorities.addAll(role.get().getPermissions().stream().map(permission -> new SimpleGrantedAuthority(PERMISSION_PREFIX + permission.getName().toUpperCase())).collect(Collectors.toList()));
            }
        }
        return authorities;
    }


}
