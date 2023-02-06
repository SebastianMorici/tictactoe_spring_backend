package com.sebastian.tictactoe_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.tictactoe_backend.entities.Player;
import com.sebastian.tictactoe_backend.services.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	private final PlayerService playerService;

	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping("/players")
	public ResponseEntity<?> index() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(playerService.index());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

	@GetMapping("/players/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(playerService.show(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

	@PostMapping("/players")
	public ResponseEntity<?> create(@RequestBody Player newPlayer) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(playerService.create(newPlayer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

	@PutMapping("/players/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Player player) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(playerService.update(id, player));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

	@DeleteMapping("/players/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		try {
			playerService.destroy(id);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Succesfully deleted\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}
	
	@PostMapping("/players/{id}/create_board")
	public ResponseEntity<?> createBoard(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body("{ \"id\": " + playerService.createBoard(id) + " }");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + e.getMessage() + "\" }");
		}
	}

}
