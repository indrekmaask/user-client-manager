package com.github.ucm.web;

import com.github.ucm.jpa.CountryRepository;
import com.github.ucm.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
class CountryController {

  private final CountryRepository countryRepository;

  @GetMapping
  public List<CountryModel> getCountries() {
    Iterable<Country> countries = countryRepository.findAll();
    return StreamSupport.stream(countries.spliterator(), false)
      .map(CountryModel::new)
      .collect(toList());
  }
}
