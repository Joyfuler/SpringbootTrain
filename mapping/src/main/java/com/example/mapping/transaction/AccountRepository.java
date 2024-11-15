package com.example.mapping.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // CRUD 기본으로 JpaRepository를 사용하나, 다른 repository를 확장해 사용할 수도 있음.
}
