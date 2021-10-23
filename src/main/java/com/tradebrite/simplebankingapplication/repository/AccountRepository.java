package com.tradebrite.simplebankingapplication.repository;

import com.tradebrite.simplebankingapplication.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {

}
