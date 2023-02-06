package com.sebastian.tictactoe_backend.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends Base {

	private static final long serialVersionUID = 1L;

	private String state = " , , , , , , , , ";
	private Boolean isFull = false;
	private Turn turn = Turn.X;
	private Winner winner = Winner.PENDING;

	// Asociaciones
	@ManyToOne
	@JoinColumn(name = "player_x_id")
	private Player playerX;

	@ManyToOne
	@JoinColumn(name = "player_o_id")
	private Player playerO;

	@AllArgsConstructor
	@ToString
	public enum Turn {
		X("x"), O("o");

		String turnName;
	}

	@AllArgsConstructor
	@ToString
	public enum Winner {
		X("x"), O("o"), PENDING("pending"), DRAW("draw");
		String winnerName;
	}
}
