package com.savina.scheduler.service;

import com.savina.scheduler.data.Credentials;
import com.savina.scheduler.data.User;
import com.savina.scheduler.data.UserCredentials;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService {

    void create(UserCredentials userEntity);

    List<UserCredentials> readAll();

    @Nullable
    UserCredentials findByCredentials(Credentials credentials);

   // UserCredentials update(UserCredentials userEntity, int id);

    //boolean delete(int id);
}
