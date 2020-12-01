package uk.ac.ox.ctsu.arts.addressservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import uk.ac.ox.ctsu.arts.addressservice.exception.NotFoundException;
import uk.ac.ox.ctsu.arts.addressservice.model.Address;
import uk.ac.ox.ctsu.arts.addressservice.model.AddressRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Logger;


@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/get/{id}")
    Address get(
        @PathVariable
            Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @GetMapping("")
    Page<Address> getPaged(
        @RequestParam
            int page,
        @RequestParam
            int size) {
        return addressRepository.findAll(PageRequest.of(page, size));
    }

    @PostMapping("/create")
    Address create(
        @RequestBody
            Address address, @AuthenticationPrincipal Jwt jwt) {
        address.setChangedWhen(LocalDateTime.now());
        address.setChangedWho(jwt.getClaimAsString("unique_name"));
        return addressRepository.save(address);
    }

    @PostMapping("/update")
    Address update(
        @RequestBody
            Address address) {
        return addressRepository.save(address);
    }

    @GetMapping("/oidc-principal")
    public String getOidcUserPrincipal(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello, %s!", jwt.getClaimAsString("name"))+"Token claims: "+Arrays.toString(jwt.getClaims().entrySet().toArray())+"Spring authorities: "+Arrays.toString(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray());
    }



    @GetMapping("/secured-by-role-superuser")
    @PreAuthorize("hasAuthority('ROLE_superuser')")
    public String getSecuredByRole() {
        return "Success - secured by superuser role.";

    }

    @GetMapping("/secured-by-role-nonsense")
    @PreAuthorize("hasAuthority('ROLE_no_such_role')")
    public String getSecuredByBadRole() {
        return "How did we get through here?";

    }
}
