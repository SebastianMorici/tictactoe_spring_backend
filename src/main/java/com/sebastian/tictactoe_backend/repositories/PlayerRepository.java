package com.sebastian.tictactoe_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sebastian.tictactoe_backend.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
