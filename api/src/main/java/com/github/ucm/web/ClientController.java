package com.github.ucm.web;

import com.github.ucm.model.Client;
import com.github.ucm.service.ClientService;
import com.github.ucm.web.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/clients")
class ClientController {

  private final ClientService clientService;

  @GetMapping
  public List<ClientListItem> getUserClients(@AuthenticationPrincipal UserPrincipal principal) {
    return principal.user().clients().stream()
      .map(ClientListItem::new)
      .collect(toList());
  }

  @GetMapping("{clientId}")
  public ResponseEntity<ClientDetails> getClientDetails(
    @PathVariable UUID clientId,
    @AuthenticationPrincipal UserPrincipal principal) {

    return clientService.findUserClient(clientId, principal.user().id())
      .map(ClientDetails::new)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Client> addNewClient(@RequestBody @Valid ClientDetails clientDetails,
                                             @AuthenticationPrincipal UserPrincipal principal) {
    Client client = clientService.addNew(clientDetails, principal.user());
    return ResponseEntity.created(URI.create(client.id().toString()))
      .body(client);
  }

  @PutMapping("{clientId}")
  public ResponseEntity<Client> updateClient(@RequestBody @Valid ClientDetails clientDetails,
                                             @PathVariable String clientId,
                                             @AuthenticationPrincipal UserPrincipal principal) {

    Optional<Client> client = clientService.findUserClient(UUID.fromString(clientId), principal.user().id());
    if (client.isEmpty())
      return ResponseEntity.badRequest().build();


    Client updatedClient = clientService.update(client.get(), clientDetails);
    return ResponseEntity.ok(updatedClient);
  }
}
