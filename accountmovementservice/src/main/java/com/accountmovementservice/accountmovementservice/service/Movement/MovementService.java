package com.accountmovementservice.accountmovementservice.service.Movement;

import com.accountmovementservice.accountmovementservice.dtos.movement.MovementBody;
import com.accountmovementservice.accountmovementservice.dtos.movement.MovementResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementService {

    boolean createMovement(MovementBody movement);

    boolean editMovement(MovementBody movement, Integer movementId);

    boolean deleteMovement(Integer movementId);

    MovementResponse getMovement(Integer movementId);

    List<MovementResponse> getMovements(Integer accountId, LocalDateTime from, LocalDateTime to) ;

}
