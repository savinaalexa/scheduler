package com.savina.scheduler.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserCredentials, Integer> {

    @Query("select u from UserCredentials u where u.login = ?1 and u.password = ?2")
    List<UserCredentials> findByCredentials(String login, String password);

    @Query("select u from UserCredentials u where u.login = ?1")
    List<UserCredentials> findByLogin(String login);
}
