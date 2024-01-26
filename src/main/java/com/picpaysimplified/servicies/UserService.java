package com.picpaysimplified.servicies;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.domain.user.UserType;
import com.picpaysimplified.dtos.UserDTO;
import com.picpaysimplified.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.COMMOM){
            throw new Exception("Lojista não está autorizado a realizar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }
    public User findUserById(Long id) throws Exception {
    return this.repository.findUserById(id).orElseThrow(() ->
            new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public User createUser(UserDTO dto) {
        User user = new User(dto);
        this.repository.save(user);
        return user;
    }

    public List<User> getAllUser() {
        return repository.findAll();
    }
}
