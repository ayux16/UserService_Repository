package org.ecomerce.userservice_repository.Repository;

import org.ecomerce.userservice_repository.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface UserServicRepo extends JpaRepository<User,Long> {
    @Override
    User save(User user);//this operation acts as a upsurt operation that is update + insert  i.e up+sert

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String email);
}
