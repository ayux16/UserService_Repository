//instead of hardcoding credentails we should get it from the db

-------------------------------------------------------------------
public RegisteredClientRepository registeredClientRepository() {

-------------------------------------------------------------------
{{{{Used for registering client }}}}

-------------------------------------------------------------------
return new InMemoryRegisteredClientRepository(oidcClient)-->> we are saving the details of the client(oidcClient) in memory
}
-------------------------------------------------------------------
public JWKSource<SecurityContext> jwkSource() {
        OATH 2 uses jwt token and in c part of jwt is encrepted part A+B
        by default it uses RSA algorithm
    }


-------------------------------------------------------------------

private static KeyPair generateRsaKey() {
        it is used to generate keyPair
}

-------------------------------------------------------------------
to validate that token we need to decode the token
public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {

        when we get the token we need to decode it
        so for this we are using jwt decodder
}

-------------------------------------------------------------------
public AuthorizationServerSettings authorizationServerSettings() {
      Setting authorizationin server
}

-------------------------------------------------------------------
instead of storing data in memory we have to store it in db

Use spring authorozation official documentation
and use that to create the POJO Repo & Services

--------------------------------------------------------------------
BLOB this error comes where data size is very HUGE // Very Large //Enormous
use @LOB before the column or row where you are getting the error
ex

    @Column(length = 1000)
    private String clientAuthenticationMethods;## Error

    @Column(length = 1000)
    private String authorizationGrantTypes;## Error
    -->> to overcome we can use @Lob

    @Column(length = 1000)
    @Lob
    private String clientAuthenticationMethods;

    @Column(length = 1000)
    @Lob
    private String authorizationGrantTypes;

--------------------------------------------------------------------