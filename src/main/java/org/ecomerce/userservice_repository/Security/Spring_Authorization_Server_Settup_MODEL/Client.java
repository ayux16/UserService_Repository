package org.ecomerce.userservice_repository.Security.Spring_Authorization_Server_Settup_MODEL;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`client`")
public class Client {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    @Column(length = 1000)
    @Lob
    private String clientAuthenticationMethods;
    @Column(length = 1000)
    @Lob
    private String authorizationGrantTypes;
    @Column(length = 1000)
    @Lob
    private String redirectUris;
    @Column(length = 1000)
    @Lob
    private String postLogoutRedirectUris;
    @Column(length = 1000)
    @Lob
    private String scopes;
    @Column(length = 2000)
    @Lob
    private String clientSettings;
    @Column(length = 2000)
    @Lob
    private String tokenSettings;

}