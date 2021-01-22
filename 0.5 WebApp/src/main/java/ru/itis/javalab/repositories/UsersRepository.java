package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User>{
    List<User> findAllByAge(Integer age);
    Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findOneById(Long id);
}
