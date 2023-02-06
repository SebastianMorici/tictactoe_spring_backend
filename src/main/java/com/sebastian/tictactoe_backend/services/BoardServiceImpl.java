package com.sebastian.tictactoe_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.tictactoe_backend.entities.Board;
import com.sebastian.tictactoe_backend.entities.Board.Turn;
import com.sebastian.tictactoe_backend.entities.Board.Winner;
import com.sebastian.tictactoe_backend.repositories.BoardRepository;
import static com.sebastian.tictactoe_backend.util.BoardUtils.checkTurn;
import static com.sebastian.tictactoe_backend.util.BoardUtils.isPositionAvailable;
import static com.sebastian.tictactoe_backend.util.BoardUtils.switchTurn;
import static com.sebastian.tictactoe_backend.util.BoardUtils.checkWinner;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private final BoardRepository boardRepository;

	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public List<Board> index() {
		return boardRepository.findAll();
	}

	@Override
	public void destroy(Long id) throws Exception {
		boardRepository.deleteById(id);
	}

	@Override
	public Board refresh(Long id) throws Exception {
		return boardRepository.findById(id).get();
	}

	@Override
	public Board play(Long boardId, Long playerId, int index) throws Exception {
		Board board = boardRepository.findById(boardId).get();
		String[] state = board.getState().split(",");

		if (!(checkTurn(board, playerId) && isPositionAvailable(state, index)
				&& (board.getWinner() == Winner.PENDING))) {
			return board;
		}

		Turn turn = board.getTurn();
		String sTurn = (turn == Turn.X) ? "x" : "o";
		state[index] = sTurn;
		board.setState(String.join(",", state));
		board.setWinner(checkWinner(state, turn));
		switchTurn(board);

		return boardRepository.save(board);

	}

}
