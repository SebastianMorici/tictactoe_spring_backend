package com.sebastian.tictactoe_backend.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.tictactoe_backend.entities.Board;
import com.sebastian.tictactoe_backend.services.BoardService;

import lombok.Getter;

@RestController
public class BoardController {

	@Autowired
	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/boards")
	public ResponseEntity<?> index() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(boardService.index());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

	@GetMapping("/boards/{id}/refresh")
	public ResponseEntity<?> refresh(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(boardService.refresh(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}
	
	@PostMapping("/boards/{id}/play")
	public ResponseEntity<?> play(@PathVariable Long id, @Valid @RequestBody Play play) {
		try {
			Board board = boardService.play(id, play.getPlayerId(), play.getIndex()); 
			return ResponseEntity.status(HttpStatus.OK).body(board);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

	@DeleteMapping("/boards/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		try {
			boardService.destroy(id);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Succesfully deleted\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

}

@Getter
class Play {
	@NotNull
	private Long playerId;
	@NotNull
	private int index;
}
