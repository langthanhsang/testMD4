package com.example.thi_th.service;

import com.example.thi_th.model.City;
import com.example.thi_th.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends IGeneralService<City>{

    Page<City>findAll(Pageable pageable);

    Page<City>findAllByName(Pageable pageable,String name);

    Page<City>findAllByCountry(Pageable pageable, Country country);
}
