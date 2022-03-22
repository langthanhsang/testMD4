package com.example.thi_th.repository;

import com.example.thi_th.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends PagingAndSortingRepository<Country,Long> {
}
