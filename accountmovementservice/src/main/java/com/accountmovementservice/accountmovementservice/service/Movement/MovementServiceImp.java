package com.accountmovementservice.accountmovementservice.service.Movement;

import com.accountmovementservice.accountmovementservice.dtos.movement.MovementBody;
import com.accountmovementservice.accountmovementservice.dtos.movement.MovementResponse;
import com.accountmovementservice.accountmovementservice.exceptions.BalanceNotAvailableException;
import com.accountmovementservice.accountmovementservice.model.Account;
import com.accountmovementservice.accountmovementservice.model.Movements;
import com.accountmovementservice.accountmovementservice.repository.MovementRepository;
import com.accountmovementservice.accountmovementservice.service.account.AccountEntityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImp implements MovementService {

    private final AccountEntityService accountEntityService;
    private final MovementRepository movementRepository;

    @Autowired
    public MovementServiceImp(AccountEntityService accountEntityService, MovementRepository movementRepository) {
        this.accountEntityService = accountEntityService;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public boolean createMovement(MovementBody movement) {
        Optional<Account> accountOpt = accountEntityService.getAccount(movement.getAccountId());
        if(accountOpt.isPresent()) {
            Account account = accountOpt.get();
            // Check if the balance is available, balance withdrawal are negative and deposits are positive
            if(account.getInitialBalance().add(movement.getBalance()).compareTo(BigDecimal.ZERO) < 0) {
                throw new BalanceNotAvailableException("Saldo no disponible");
            }
            account.setInitialBalance(account.getInitialBalance().add(movement.getBalance()));
            accountEntityService.createAccount(account);
            Movements mov = new Movements(movement.getDate(), movement.getType(), movement.getBalance(), account);
            return movementRepository.save(mov) != null;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean editMovement(MovementBody movement, Integer movementId) {
        //movements doesn't allow to edit balance and account
        Optional<Movements> moveOpt = movementRepository.findById(movementId);
        if(moveOpt.isPresent()){
            Movements move = moveOpt.get();
            move.setDate(movement.getDate());
            move.setType(movement.getType());
            return movementRepository.save(move) != null;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteMovement(Integer movementId) {
        Optional<Movements> move = movementRepository.findById(movementId);
        if (move.isPresent()){
            movementRepository.delete(move.get());
            return true;
        }
        return false;
    }

    @Override
    public MovementResponse getMovement(Integer movementId) {
        Optional<Movements> move = movementRepository.findById(movementId);
        return move.map(movement -> MovementResponse.builder()
                .movementId(movement.getMovementId())
                .date(movement.getDate())
                .type(movement.getType())
                .balance(movement.getBalance())
                .accountId(movement.getAccount().getAccountId())
                .build()).orElse(null);
    }

    @Override
    public List<MovementResponse> getMovements(Integer accountId, LocalDateTime from, LocalDateTime to) {
        List<Movements> movements = movementRepository.findByAccountId(accountId, from, to);
        return movements.stream().map(movement -> MovementResponse.builder()
                .movementId(movement.getMovementId())
                .date(movement.getDate())
                .type(movement.getType())
                .balance(movement.getBalance())
                .accountId(movement.getAccount().getAccountId())
                .build()).toList();
    }
}
