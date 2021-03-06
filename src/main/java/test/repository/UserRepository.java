package test.service;

import org.springframework.data.repository.CrudRepository;
import test.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(Long id);
}
