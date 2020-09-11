package com.github.ucm.service;

import com.github.ucm.jpa.ClientRepository;
import com.github.ucm.jpa.CountryRepository;
import com.github.ucm.model.Client;
import com.github.ucm.model.User;
import com.github.ucm.web.ClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;
  private final CountryRepository countryRepository;


  public Optional<Client> findUserClient(UUID clientId, UUID userId) {
    return clientRepository.findByIdAndUser_Id(clientId, userId);
  }

  public Client addNew(ClientDetails details, User user) {
    return clientRepository.save(build(Client.builder().user(user), details));
  }

  public Client update(Client existingClient, ClientDetails details) {
    return clientRepository.save(build(existingClient.toBuilder(), details));
  }

  private Client build(Client.ClientBuilder builder, ClientDetails details) {
    return builder.firstName(details.getFirstName())
      .lastName(details.getLastName())
      .username(details.getUserName())
      .email(details.getEmail())
      .address(details.getAddress())
      .country(countryRepository.findById(details.getCountryCode())
        .orElseThrow(() -> new UnknownCountry(details.getCountryCode())))
      .build();
  }
}
