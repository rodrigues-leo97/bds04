package com.devsuperior.bds04.services;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class); //para imprimir logs

    @Autowired
    private UserRepository userRepository;


//    @Transactional(readOnly = true)
//    public Page<UserDTO> findAll(Pageable pageable) {
//        Page<User> userPage = userRepository.findAll(pageable);
//        return userPage.map(x -> new UserDTO(x));
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //irá fazer a busca por EMAIL

        User user = userRepository.findByEmail(username);
        if (user == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("E-mail not found");
        }
        logger.info("User found: " +  username);
        return user;
    }
}
