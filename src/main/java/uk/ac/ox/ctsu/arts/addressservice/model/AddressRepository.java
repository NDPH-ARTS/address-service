package uk.ac.ox.ctsu.arts.addressservice.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
}

