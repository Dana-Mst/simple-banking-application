package com.tradebrite.simplebankingapplication.model.customer;

import com.tradebrite.simplebankingapplication.model.AccountModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String cnp;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String address;

    private Date createDate;

//    @Column
//    private CustomerType customerType;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="Customer_Account",
//            joinColumns = {@JoinColumn(name = "customer_id")},
//            inverseJoinColumns = {@JoinColumn(name = "account_id")}
//    )
//    private Set<AccountModel> accounts;

}
