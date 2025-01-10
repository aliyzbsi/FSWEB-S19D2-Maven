package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.exceptions.EntityException;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }
    @GetMapping("/{id}")
    public Account findById(@PathVariable("id")long id){
        return accountService.find(id);
    }
    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable("customerId") long customerId, @RequestBody Account account){
        Customer customer=customerService.find(customerId);
        customer.addAccount(account);
        account.setCustomer(customer);
        accountService.save(account);
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),
                new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary()));
    }
    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable("customerId") long customerId,@RequestBody Account account){
        Customer customer=customerService.find(customerId);
        Account toBeUpdatedAccount=null;
        for (Account account1:customer.getAccounts()){
            if(account.getId()==account1.getId()){
                toBeUpdatedAccount=account1;
            }
        }
        if(toBeUpdatedAccount==null){
            throw new EntityException(customerId+": id'li account için bir müşteri bulunamadı! ", HttpStatus.NOT_FOUND);
        }
        int indexOfToBeUpdated=customer.getAccounts().indexOf(toBeUpdatedAccount);
        customer.getAccounts().set(indexOfToBeUpdated,account);
        account.setCustomer(customer);
        accountService.save(account);
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),
                new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary()));
    }
    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable("id")long id){
        Account account=accountService.find(id);
        if(account==null){
            throw new EntityException(id+": bu id de bir account bulunamadı!",HttpStatus.NOT_FOUND);
        }
        accountService.delete(id);
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),
                new CustomerResponse(account.getCustomer().getId(),account.getCustomer().getEmail(),account.getCustomer().getSalary()));
    }
}
