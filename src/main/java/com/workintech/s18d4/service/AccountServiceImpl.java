package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.exceptions.EntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account find(long id) {
        Optional<Account> account= accountRepository.findById(id);
        if(account.isPresent()){
            return account.get();
        }else {
            throw new EntityException(id+":bu id'de bir account bulunamadı!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Account save(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public Account delete(long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            accountRepository.delete(account.get());
            return account.get();
        } else {
            return null;
        }
    }


}
