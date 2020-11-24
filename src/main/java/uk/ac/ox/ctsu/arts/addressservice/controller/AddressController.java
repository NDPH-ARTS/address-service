package uk.ac.ox.ctsu.arts.addressservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import uk.ac.ox.ctsu.arts.addressservice.exception.NotFoundException;
import uk.ac.ox.ctsu.arts.addressservice.model.Address;
import uk.ac.ox.ctsu.arts.addressservice.model.AddressRepository;

import java.time.LocalDateTime;


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
        return String.format("Hello, %s!", jwt.getClaimAsString("name"));
    }
}
