package uk.ac.ox.ctsu.arts.addressservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uk.ac.ox.ctsu.arts.addressservice.exception.NotFoundException;
import uk.ac.ox.ctsu.arts.addressservice.model.Address;
import uk.ac.ox.ctsu.arts.addressservice.model.AddressRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@RestController
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/address/get/{id}")
    Address get(@PathVariable Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException());

        return address;
    }

    @GetMapping("/addresses")
    Page<Address> get(@RequestParam int page, @RequestParam int size) {
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
}
