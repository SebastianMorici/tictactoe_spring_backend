package com.sebastian.tictactoe_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.tictactoe_backend.entities.Board;
import com.sebastian.tictactoe_backend.entities.Player;
import com.sebastian.tictactoe_backend.repositories.BoardRepository;
import com.sebastian.tictactoe_backend.repositories.PlayerRepository;
import static com.sebastian.tictactoe_backend.util.BoardUtils.setFullBoard;
import static com.sebastian.tictactoe_backend.util.BoardUtils.setHalfBoard;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private final PlayerRepository playerRepository;

	@Autowired
	private final BoardRepository boardRepository;

	public PlayerServiceImpl(PlayerRepository playerRepository, BoardRepository boardRepository) {
		this.playerRepository = playerRepository;
		this.boardRepository = boardRepository;
	}

	@Override
	public List<Player> index() {
		return playerRepository.findAll();
	}

	@Override
	public Player show(Long id) {
		return playerRepository.findById(id).get();
	}

	@Override
	public Player create(Player newPlayer) {
		return playerRepository.save(newPlayer);
	}

	@Override
	public Player update(Long id, Player updatedPlayer) throws Exception {
		return playerRepository.findById(id).map(player -> {
			player.setName(updatedPlayer.getName());
			player.setPlayerOBoards(updatedPlayer.getPlayerOBoards());
			player.setPlayerXBoards(updatedPlayer.getPlayerXBoards());
			return playerRepository.save(player);
		}).orElseThrow(() -> new Exception());
	}

	@Override
	public void destroy(Long id) throws Exception {
		playerRepository.deleteById(id);
	}

	@Override
	public Long createBoard(Long playerId) throws Exception {
		Player player = playerRepository.findById(playerId).get();
		List<Board> nonFullBoards = boardRepository.findByIsFull(false);
		if (nonFullBoards.isEmpty()) {
			return boardRepository.save(setHalfBoard(player)).getId();
		} else {
			Board lastNonFullBoard = nonFullBoards.get(nonFullBoards.size() - 1);
			return boardRepository.save(setFullBoard(lastNonFullBoard, player)).getId();
		}
	}

}
