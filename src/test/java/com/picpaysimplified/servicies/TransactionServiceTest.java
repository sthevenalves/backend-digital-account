package com.picpaysimplified.servicies;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.domain.user.UserType;
import com.picpaysimplified.dtos.TransactionDTO;
import com.picpaysimplified.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository repository;

    @Mock
    private authorizationService authService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createTransaction return object transaction when successful")
    void createTransaction_Case1_WhenSuccessful() throws Exception {
        User sender = new User(1L,"Stheven","Alves", "123445678", "test@gmail.com", "4444",new BigDecimal(10), UserType.COMMOM);
        User receiver = new User(2L,"Joao","Souza", "847156248", "test2@gmail.com", "4444",new BigDecimal(10), UserType.COMMOM);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);
        when(authService.authorizeTransaction(any(),any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(10),1L,2L);
        transactionService.createTransaction(request);

        verify(repository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(20));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "Transação realizada com sucesso");
        verify(notificationService, times(1)).sendNotification(receiver, "Transação recebida com sucesso");


    }

    @Test
    @DisplayName("createTransaction throw new Exception when Transaction not allowed")
    void createTransaction_Case2_WhenSuccessful() throws Exception {

        User sender = new User(1L,"Stheven","Alves", "123445678", "test@gmail.com", "4444",new BigDecimal(10), UserType.COMMOM);
        User receiver = new User(2L,"Joao","Souza", "847156248", "test2@gmail.com", "4444",new BigDecimal(10), UserType.COMMOM);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);
        when(authService.authorizeTransaction(any(),any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            TransactionDTO request = new TransactionDTO(new BigDecimal(10),1L,2L);
            transactionService.createTransaction(request);
            });
        Assertions.assertEquals("Transação não autorizada", thrown.getMessage());
    }
}