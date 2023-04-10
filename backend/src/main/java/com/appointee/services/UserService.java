package com.appointee.services;

import com.appointee.models.User;
import com.appointee.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@Service
@Data
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    /*
    Hibernate generates an INSERT INTO query for userRepository.findByUsername because the findByUsername method
    is likely annotated with @Transactional, which means it participates in the current transaction.
    When the createUser method is executed, it starts a transaction with the @Transactional annotation,
    and the transaction is not committed until the method completes or until an exception is thrown.
    In the catch block of the createUser method, if an exception occurs and the cause of the exception is a
    ConstraintViolationException, it means that there was a constraint violation while trying to save the user
     using userRepository.saveAndFlush(user). In this case, the transaction has not been committed yet,
      and the user entity is still in a transient state within the transaction.
      When the exception occurs, the createUser method tries to retrieve an existing user with the same
      username using userRepository.findByUsername(username). Since the transaction has not been committed yet,
       and the entity manager is still within the transaction, Hibernate generates an INSERT INTO query for
        userRepository.findByUsername to ensure that
       it retrieves the latest state of the database, including any changes made within the same transaction.
       This behavior is consistent with Hibernate's first-level cache (also known as the persistence context), which keeps track of changes made to entities within a transaction and synchronizes them with the database upon transaction commit. Until the transaction is committed, Hibernate may generate additional queries to the database to ensure that it has the latest state of the entities within the transaction.
     */

    // will throw error
    @Transactional
    public String createUserWrongMethod(String username, String name){
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        try {
            User savedUser = userRepository.saveAndFlush(user);
            return savedUser.getName();
        } catch (Exception exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof ConstraintViolationException constraintViolationException) {
                if (constraintViolationException.getCause() instanceof SQLException sqlException) {
                    User existingUser = userRepository.findByUsername(username).get();
                    return existingUser.getName();
                }
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }
    @Transactional
    public String createUser(String username, String name){
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        try {
            User savedUser = userRepository.saveAndFlush(user);
            return savedUser.getName();
        } catch (Exception exception) {
            entityManager.clear();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            Throwable cause = exception.getCause();
            if (cause instanceof ConstraintViolationException constraintViolationException) {
                if (constraintViolationException.getCause() instanceof SQLException sqlException) {
                    User existingUser = userRepository.findByUsername(username).get();
                    return existingUser.getName();
                }
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

}
