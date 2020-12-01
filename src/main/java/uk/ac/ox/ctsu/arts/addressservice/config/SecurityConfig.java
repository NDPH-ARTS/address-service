package uk.ac.ox.ctsu.arts.addressservice.config;

import net.minidev.json.JSONArray;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/address").permitAll()
            .anyRequest().authenticated()
            .and().oauth2ResourceServer(oauth2 -> oauth2.jwt()
                .jwtAuthenticationConverter(getCustomJwtAuthorisationConverter()));
    }

    Converter<Jwt, AbstractAuthenticationToken> getCustomJwtAuthorisationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {

            if(jwt.getClaims().get("roles")!=null){
                List<String> roleClaims = (List) jwt.getClaims().get("roles");
                return roleClaims.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
            }else {
                return new ArrayList<>();
            }

            // TODO deal with scope claims too?  Spring's default behaviour is to turn 'read' into a GrantedAuthority called 'SCOPE_read' etc but we have overridden
            // TODO look up permission-role mappings (or find them in the token if we us aggregated claims?)


        });
        return converter;

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/configuration/ui",
                                   "/swagger-resources/**", "/swagger-ui", "/configuration/**", "/swagger-ui.html",
                                   "/webjars/**", "/favicon.ico");
    }
}

