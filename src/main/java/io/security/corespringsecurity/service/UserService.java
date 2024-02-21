package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;

import java.util.List;

public interface UserService {

    Account editByUser(Long userId, Account updatedAccount);
    void createUser(Account account);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    List<Account> findByUser(Long id);
    AccountDto getUser(Long id);

    void deleteUser(Long idx);

    Account getUser(String user);
}
