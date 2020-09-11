package com.github.ucm.jpa;

import com.github.ucm.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {

  Optional<Client> findByIdAndUser_Id(UUID clientId, UUID userId);
}
