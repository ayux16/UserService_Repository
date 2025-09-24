package org.ecomerce.userservice_repository.Security.Spring_Authorization_Server_Settup_REPOSITORY;

import org.ecomerce.userservice_repository.Security.Spring_Authorization_Server_Settup_MODEL.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}