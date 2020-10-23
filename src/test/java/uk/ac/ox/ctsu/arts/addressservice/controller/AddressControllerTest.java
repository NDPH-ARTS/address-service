package uk.ac.ox.ctsu.arts.addressservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import uk.ac.ox.ctsu.arts.addressservice.exception.NotFoundException;
import uk.ac.ox.ctsu.arts.addressservice.model.Address;
import uk.ac.ox.ctsu.arts.addressservice.model.AddressRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        when(addressRepository.findById(2l)).thenReturn(Optional.of(new Address()));
        addressController.get(id);
    }

    @Test
    void getPagedCallsRepository() {
        int pageNumber = 1;
        int pageSize = 40;
        @SuppressWarnings("unchecked")
        Page<Address> page = (Page<Address>) mock(Page.class);

        when(addressRepository.findAll(argThat((PageRequest pr) -> pr.getPageSize() == pageSize && pr.getPageNumber() == 1)))
            .thenReturn(page);
        addressController.getPaged(pageNumber, pageSize);
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

    @Test
    void updateCallsRepository() {
        Address address = new Address();
        when(addressRepository.save(address)).thenReturn(address);
        addressController.update(address);
    }
}
