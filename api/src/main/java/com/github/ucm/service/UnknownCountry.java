package com.github.ucm.service;

public class UnknownCountry extends RuntimeException {

  public UnknownCountry(String code) {
    super("Unknown country: " + code);
  }
}
