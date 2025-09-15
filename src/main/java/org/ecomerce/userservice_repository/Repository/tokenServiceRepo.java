package org.ecomerce.userservice_repository.Repository;

import org.ecomerce.userservice_repository.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface tokenServiceRepo extends JpaRepository<Token, Long> {
    Token save(Token token);
//    Optional<Token> findByEmail(String email);
//
//    void deleteById(String email);

    List<Token> findByUser_email(String userEmail);

    Optional<Token> findByValueAndIsDeletedAndExpryAtGreaterThan(String tokenValue, boolean isDeleted, Date expiryAt);

    //select * from token where value=? and expiryaTime > currenttime stamp and deleted=false
}
