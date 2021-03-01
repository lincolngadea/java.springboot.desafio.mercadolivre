package io.orange.mercadolivre.repository;

import io.orange.mercadolivre.entity.UserLogin;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class UserLoginRepository {

    public Query findByUsername(EntityManager manager, String username) {
        Query query = manager.createQuery("SELECT u FROM UserLogin u WHERE u.username =:user", UserLogin.class)
                .setParameter("user", username);
        return query;
    }
}
