package com.sebastian.tictactoe_backend.services;

import java.util.List;

import com.sebastian.tictactoe_backend.entities.Player;

public interface PlayerService {

	List<Player> index();

	Player show(Long id) throws Exception;

	Player create(Player newPlayer) throws Exception;

	Player update(Long id, Player player) throws Exception;

	void destroy(Long id) throws Exception;

	Long createBoard(Long playerId) throws Exception;

}
