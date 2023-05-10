package com.anywr.test.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anywr.test.dao.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByLogin(String login);

}
