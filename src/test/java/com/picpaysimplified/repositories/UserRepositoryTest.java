package com.picpaysimplified.repositories;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.domain.user.UserType;
import com.picpaysimplified.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("findByDocument return object User when Successful")
    void findUserByDocument_WhenSuccessful() {
        String document = "9999999901";
        UserDTO data = new UserDTO("Stheven","Alves", document, "test@gmail.com", new BigDecimal(10), "4444", UserType.COMMOM);
        this.createUser(data);

        Optional<User> userOptional = this.userRepository.findUserByDocument(document);
        assertThat(userOptional.isPresent()).isTrue();
    }

    @Test
    @DisplayName("findByDocument when User not found")
    void findUserByDocument_WhenUserNotFound() {
        String document = "9999999901";
        Optional<User> userOptional = this.userRepository.findUserByDocument(document);
        assertThat(userOptional.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}