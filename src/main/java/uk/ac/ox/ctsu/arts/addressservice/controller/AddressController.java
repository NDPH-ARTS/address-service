package uk.ac.ox.ctsu.arts.addressservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.ac.ox.ctsu.arts.addressservice.exception.NotFoundException;
import uk.ac.ox.ctsu.arts.addressservice.model.Address;
import uk.ac.ox.ctsu.arts.addressservice.model.AddressRepository;

import java.time.LocalDateTime;


@RestController
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/address/get/{id}")
    Address get(@PathVariable Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @GetMapping("/addresses")
    Page<Address> getPaged(@RequestParam int page, @RequestParam int size) {
        return addressRepository.findAll(PageRequest.of(page, size));
    }

    @PostMapping("/address/create")
    Address create(@RequestBody Address address) {
        address.setChangedWhen(LocalDateTime.now());
        address.setChangedWho("it was me");
        return addressRepository.save(address);
    }

    @PostMapping("/address/update")
    Address update(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @GetMapping("/address/getsomethingelse")
    int getsomethingelse() {
        return 1;
    }
}
