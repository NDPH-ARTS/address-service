package uk.ac.ox.ctsu.arts.addressservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthorisationConverter authorisationConverter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/address").permitAll()
            .anyRequest().authenticated()
            .and().oauth2ResourceServer(oauth2 -> oauth2.jwt()
                .jwtAuthenticationConverter(getCustomJwtAuthConverter()));
    }

    private Converter<Jwt, AbstractAuthenticationToken> getCustomJwtAuthConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter(); //Authentication
        converter.setJwtGrantedAuthoritiesConverter(authorisationConverter); //Authorisation
        return converter;

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/configuration/ui",
                                   "/swagger-resources/**", "/swagger-ui", "/configuration/**", "/swagger-ui.html",
                                   "/webjars/**", "/favicon.ico");
    }
}

