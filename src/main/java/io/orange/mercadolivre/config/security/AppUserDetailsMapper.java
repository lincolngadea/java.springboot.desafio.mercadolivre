package io.orange.mercadolivre.config.security;

import io.orange.mercadolivre.model.UserLogin;
import io.orange.mercadolivre.model.LoggedUser;
import io.orange.mercadolivre.utils.UserDetailsMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper {

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new LoggedUser((UserLogin) shouldBeASystemUser);
    }
}
