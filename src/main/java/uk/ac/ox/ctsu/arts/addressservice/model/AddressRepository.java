package uk.ac.ox.ctsu.arts.addressservice.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
//    List<Address> findAll(Pageable pageable);
}

