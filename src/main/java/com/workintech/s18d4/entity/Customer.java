package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer",schema = "fsweb")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private double salary;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;
    //private List<Account> accounts=new ArrayList<>();
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    public void addAccount(Account account){
        if(accounts==null){
            accounts=new ArrayList<>();
        }
        accounts.add(account);
    }

}
