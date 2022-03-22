package com.example.thi_th.controller;

import com.example.thi_th.model.City;
import com.example.thi_th.model.Country;
import com.example.thi_th.service.ICityService;
import com.example.thi_th.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/country")
public class CountryController {
    @Autowired
    private ICountryService iCountryService;

    @Autowired
    private ICityService iCityService;

    @ModelAttribute(name = "country")
    private Iterable<Country> findAdd() {
        return iCountryService.findAll();
    }

    @GetMapping
    public ModelAndView showListCountry() {
        return new ModelAndView("country/list-country");
    }

    @GetMapping("/city-in")
    public ModelAndView showAll(@PageableDefault(value = 3) Pageable pageable,
                                Optional<Long> country_id) {
        ModelAndView modelAndView = new ModelAndView("country/city-in-country");
        Optional<Country> country = iCountryService.findById(country_id.orElse(0L));
        Page<City> cities = iCityService.findAllByCountry(
                pageable, country.orElse(new Country()));
        modelAndView.addObject("country_id", country_id.orElse(0L));
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PageableDefault(value = 3) Pageable pageable, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("city/list");
        iCityService.delete(id);
        Page<City> cities = iCityService.findAll(pageable);
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("city/edit");
        Optional<City> city = iCityService.findById(id);
        city.ifPresent(value -> modelAndView.addObject("city", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent( @Valid @ModelAttribute("city") City city,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("city", city);
            return "city/edit";
        }
        city.setId(id);
        iCityService.save(city);
        return "redirect:/city";
    }



}
