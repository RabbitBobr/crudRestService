package ru.bobrov.crudservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bobrov.crudservice.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
