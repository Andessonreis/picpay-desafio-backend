package com.github.andessonreis.api.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.andessonreis.api.domain.transaction.Transaction;
import com.github.andessonreis.api.domain.transaction.dto.TransactionDTO;
import com.github.andessonreis.api.domain.user.User;
import com.github.andessonreis.api.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = userService.getUserById(transactionDTO.senderId());
        User receiver = userService.getUserById(transactionDTO.receiverId());

        validateTransaction(sender, transactionDTO.amount());

        if (!authorizedTransaction()) {
            throw new Exception("Transação não autorizada.");
        }

        Transaction newTransaction = buildTransaction(sender, receiver, transactionDTO.amount());

        updateBalances(sender, receiver, transactionDTO.amount());

        sendNotifications(sender, receiver);

        saveEntities(sender, receiver, newTransaction);

        return newTransaction;
    }

    private void validateTransaction(User sender, BigDecimal amount) throws Exception {
        userService.validateUser(sender, amount);
    }

    private Transaction buildTransaction(User sender, User receiver, BigDecimal amount) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmout(amount);
        newTransaction.setPayer(sender);
        newTransaction.setPayee(receiver);
        newTransaction.setTransactionTime(LocalDateTime.now());
        return newTransaction;
    }

    private void updateBalances(User sender, User receiver, BigDecimal amount) {
        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
    }

    private void sendNotifications(User sender, User receiver) {
        notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso!");
    }

    private void saveEntities(User sender, User receiver, Transaction transaction) {
        transactionRepository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    private boolean authorizedTransaction() {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",
                Map.class);
        return response.getStatusCode() == HttpStatus.OK
                && "Autorizado".equalsIgnoreCase((String) response.getBody().get("message"));
    }

    public List<Transaction> listTransaction(){
        return this.transactionRepository.findAll();
    }
}
