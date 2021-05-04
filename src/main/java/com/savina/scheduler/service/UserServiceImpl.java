package com.savina.scheduler.service;

import com.savina.scheduler.data.Credentials;
import com.savina.scheduler.data.User;
import com.savina.scheduler.data.UserCredentials;
import com.savina.scheduler.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserCredentials userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public List<UserCredentials> readAll() {
        List<UserCredentials> userEntities = new ArrayList<>();
        for (UserCredentials userEntity : userRepository.findAll()) {
            userEntities.add(userEntity);
        }
        return userEntities;
    }

    @Override
    public UserCredentials findByCredentials(Credentials credentials) {
        List<UserCredentials> userCredsEntities = userRepository.findByCredentials(credentials.getLogin(), credentials.getPassword());
        return userCredsEntities.isEmpty()
                ? null
                : userCredsEntities.get(0);
    }

    /*@Override
    public UserCredsEntity update(UserCredsEntity userEntity, int id) {
        return userRepository.save(userEntity);
    }

    @Override
    public boolean delete(int id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }*/


    /*
    // Хранилище пользователей
    private static final Map<Integer, User> USER_REPOSITORY_MAP = new HashMap<>();
    private static final Map<Credentials, Integer> USER_CREDENTIALS_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID пользователя
    private static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(UserCredentials userCredentials) {
        final int userId = USER_ID_HOLDER.incrementAndGet();
        User user = new User(userId, userCredentials.getName(), userCredentials.getSurname(), userCredentials.getEmail(), userCredentials.getPhone());
        Credentials credentials = new Credentials(userCredentials.getLogin(), userCredentials.getPassword());
        USER_REPOSITORY_MAP.put(userId, user);
        USER_CREDENTIALS_REPOSITORY_MAP.put(credentials, userId);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(USER_REPOSITORY_MAP.values());
    }

    @Override
    public User read(Credentials credentials) {
        int userId = USER_CREDENTIALS_REPOSITORY_MAP.get(credentials);
        return USER_REPOSITORY_MAP.get(userId);
    }

    @Override
    public boolean update(User user, int id) {
        if (USER_REPOSITORY_MAP.containsKey(id)) {
            user.setId(id);
            USER_REPOSITORY_MAP.put(id, user);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (USER_CREDENTIALS_REPOSITORY_MAP.values().remove(id)) {
            return USER_REPOSITORY_MAP.remove(id) != null;
        } else {
            return false;
        }
    }
     */
}
