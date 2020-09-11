package com.github.ucm.jpa;

import com.github.ucm.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {}
