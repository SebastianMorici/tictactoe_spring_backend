package com.sebastian.tictactoe_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sebastian.tictactoe_backend.entities.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByIsFull(Boolean isFull);
}
