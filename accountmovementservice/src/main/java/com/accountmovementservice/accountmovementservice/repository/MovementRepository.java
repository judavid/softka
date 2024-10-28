package com.accountmovementservice.accountmovementservice.repository;

import com.accountmovementservice.accountmovementservice.model.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movements, Integer> {

    @Query("SELECT m from Movements m WHERE m.account.accountId = ?1 and m.date >= ?2 and m.date <= ?3")
    List<Movements> findByAccountId(Integer accountId, LocalDateTime from, LocalDateTime to);

}
