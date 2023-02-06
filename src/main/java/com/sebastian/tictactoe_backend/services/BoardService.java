package com.sebastian.tictactoe_backend.services;

import java.util.List;

import com.sebastian.tictactoe_backend.entities.Board;

public interface BoardService {

	List<Board> index();

	void destroy(Long id) throws Exception;
	
	Board refresh(Long id) throws Exception;
	
	Board play(Long boardId, Long playerId,  int index) throws Exception;

}
