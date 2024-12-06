package com.example.tic_tac_toe;

import com.example.tic_tac_toe.exception.GameAlreadyFinishedException;
import com.example.tic_tac_toe.exception.InvalidMoveException;
import com.example.tic_tac_toe.model.Game;
import com.example.tic_tac_toe.model.Move;
import com.example.tic_tac_toe.model.Player;
import com.example.tic_tac_toe.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TicTacToeApplication implements CommandLineRunner {

	private GameService gameService;
	private Scanner scanner;

	public TicTacToeApplication(GameService gameService) {
		this.gameService = gameService;
		this.scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Welcome to Tic-Tac-Toe!");
		System.out.print("Enter Player X name: ");
		String playerXName = scanner.nextLine();
		System.out.print("Enter Player O name: ");
		String playerOName = scanner.nextLine();

		String gameId = gameService.createGame(playerXName, playerOName);
		System.out.println("Game created with ID: " + gameId);

		while (true) {
			Game game = gameService.getGame(gameId);
			printBoard(game.getBoard());

			if (game.isFinished()) {
				if (game.getWinner() != null) {
					System.out.println("Winner: " + game.getWinner());
				} else {
					System.out.println("It's a draw!");
				}
				break;
			}

			Player currentPlayer = game.isXTurn() ? game.getPlayerX() : game.getPlayerO();
			System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
			System.out.print("Enter row (0-2): ");
			int row = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter column (0-2): ");
			int col = Integer.parseInt(scanner.nextLine());

			try {
				Move move = new Move(row, col, currentPlayer);
				gameService.makeMove(gameId, move);
			} catch (InvalidMoveException e) {
				System.out.println(e.getMessage());
			} catch (GameAlreadyFinishedException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		scanner.close();
	}

	private void printBoard(char[][] board) {
		System.out.println("Current Board:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char symbol = board[i][j] == '\0' ? '-' : board[i][j];
				System.out.print(symbol + " ");
			}
			System.out.println();
		}
	}
}