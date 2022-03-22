package com.example.thi_th.repository;

import com.example.thi_th.model.City;
import com.example.thi_th.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<City,Long> {

    Page<City>findAllByNameContaining(Pageable pageable,String name);

    Page<City> findByCountry(Pageable pageable, Country country);
}
