package com.example.thi_th.service.imp;

import com.example.thi_th.model.Country;
import com.example.thi_th.repository.ICountryRepository;
import com.example.thi_th.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService implements ICountryService {
    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    public Iterable findAll() {
        return iCountryRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return iCountryRepository.findById(id);
    }

    @Override
    public void save(Country country) {
        iCountryRepository.save(country);
    }


    @Override
    public void delete(Long id) {
        iCountryRepository.deleteById(id);
    }
}
