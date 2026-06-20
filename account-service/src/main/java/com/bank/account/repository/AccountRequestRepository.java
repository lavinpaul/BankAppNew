package com.bank.account.repository;

import com.bank.account.entity.AccountRequest;
import com.bank.account.entity.AccountRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRequestRepository
        extends JpaRepository<AccountRequest, Long> {

    List<AccountRequest> findByCustomerId(Long customerId);

    List<AccountRequest> findByStatus(
            AccountRequestStatus status);
}