package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address",schema = "fsweb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "street")
    private String street;

    @Column(name = "no")
    private Integer no;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "description")
    private String description;
    @JsonBackReference
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    private Customer customer;
}
