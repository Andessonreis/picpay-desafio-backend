package com.github.andessonreis.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.andessonreis.api.domain.user.User;
import com.github.andessonreis.api.domain.user.UserType;
import com.github.andessonreis.api.domain.user.dto.UserDTO;
import com.github.andessonreis.api.repository.UserRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> listUsers(){
        return this.userRepository.findAll();
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public User getUserById(@NotNull Long id) throws Exception {
        if (id == null) {
            throw new IllegalAccessException("ID do usuário não pode ser nulo");
        }
        return userRepository.findById(id)
                             .orElseThrow(() -> new Exception("Usuário não encontrado"));
    }
    
    public void validateUser(User sender, BigDecimal amount) throws Exception{

    if(sender.getUserType() == UserType.MERCHANT) {
        throw new Exception("Usuário do tipo lojista não pode realizar transações");
    }

    if(sender.getBalance().compareTo(amount) < 0) {
        throw  new Exception("Saldo insuficiente");
    }
    }

}

