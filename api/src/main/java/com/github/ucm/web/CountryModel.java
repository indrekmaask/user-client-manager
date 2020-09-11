package com.github.ucm.web;

import com.github.ucm.model.Country;
import lombok.Value;

@Value
class CountryModel {
  String code;
  String name;

  public CountryModel(Country entity) {
    code = entity.code();
    name = entity.name();
  }
}
