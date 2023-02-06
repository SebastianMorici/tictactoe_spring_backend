package com.sebastian.tictactoe_backend.util;

import com.sebastian.tictactoe_backend.entities.Board;
import com.sebastian.tictactoe_backend.entities.Board.Turn;
import com.sebastian.tictactoe_backend.entities.Board.Winner;
import com.sebastian.tictactoe_backend.entities.Player;

public class BoardUtils {

	private static final int[][] winArray = { { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 1, 2 }, { 3, 4, 5 },
			{ 6, 7, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

	public static Board setFullBoard(Board board, Player player) {
		board.setPlayerO(player);
		board.setIsFull(true);
		return board;
	}

	public static Board setHalfBoard(Player player) {
		Board board = new Board();
		board.setPlayerX(player);
		return board;
	}

	public static Boolean checkTurn(Board board, Long playerId) {
		Long playerXId = board.getPlayerX().getId();
		Long playerOId = board.getPlayerO().getId();
		Turn turn = board.getTurn();
		Boolean variable = (playerId == playerXId && turn == Turn.X || playerId == playerOId && turn == Turn.O);
		return variable;

	}

	public static void switchTurn(Board board) {
		if (board.getWinner() != Winner.PENDING) return;
		Turn newTurn = (board.getTurn() == Turn.X) ? Turn.O : Turn.X;
		board.setTurn(newTurn);
	}

	public static Boolean isPositionAvailable(String[] state, int index) {
		Boolean variable = (state[index].equals(" "));
		return variable;
	}

	public static Winner checkWinner(String[] state, Turn turn) {
		int emptyCells = 0;
		String sTurn = (turn == Turn.X) ? "x" : "o";
		Winner winner;
		for (int[] winCombination : winArray) {
			int count = 0;
			for (int cell : winCombination) {
				if (state[cell].equals(sTurn)) {
					count++;
				} else if (state[cell].equals(" ")) {
					emptyCells++;
				}
			}
			if (count == 3) {
				String sWinner = "x";
				winner = (sWinner.equals(sTurn)) ? Winner.X : Winner.O;
				return winner;
			}
		}
		winner = (emptyCells == 0) ? Winner.DRAW : Winner.PENDING;
		return winner;
	}
	

//	public static Boolean canPlay() {
//		
//	}

}
