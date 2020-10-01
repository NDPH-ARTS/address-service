package uk.ac.ox.ctsu.arts.addressservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.ac.ox.ctsu.arts.addressservice.exception.NotFoundException;
import uk.ac.ox.ctsu.arts.addressservice.model.Address;
import uk.ac.ox.ctsu.arts.addressservice.model.AddressRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {
    private AddressController addressController;
    @Mock
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        addressController = new AddressController(addressRepository);
    }

    @Test
    void getCallsRepository() {
        long id = 1;
        when(addressRepository.findById(id)).thenReturn(Optional.of(new Address()));
        addressController.get(id);
    }

    @Test
    void getThrowsNotFoundException() {
        long id = 42;
        assertThrows(NotFoundException.class, () -> addressController.get(id));
    }

    @Test
    void createCallsRepository() {
        Address address = new Address();
        when(addressRepository.save(address)).thenReturn(address);
        addressController.create(address);
    }
}