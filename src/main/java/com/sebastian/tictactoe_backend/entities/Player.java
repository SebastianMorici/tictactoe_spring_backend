package com.sebastian.tictactoe_backend.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player extends Base {

	private static final long serialVersionUID = 1L;

	private String name;

	// Asociaciones
	@JsonIgnore //Evita que se envie esta relacion en el JSON.
	@OneToMany(mappedBy = "playerX")
	private Set<Board> playerXBoards = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "playerO")
	private Set<Board> playerOBoards = new HashSet<>();

}
