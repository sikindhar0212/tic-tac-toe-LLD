package com.example.tic_tac_toe.model;

import com.example.tic_tac_toe.exception.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player playerX;
    private Player playerO;
    private char[][] board;
    private boolean isXTurn;
    private boolean isFinished;
    private String winner;

    public Game(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new char[3][3];
        this.isXTurn = true;
        this.isFinished = false;
        this.winner = null;
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isXTurn() {
        return isXTurn;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getWinner() {
        return winner;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }



    public void makeMove(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        char symbol = move.getPlayer().getSymbol();

        if (board[row][col] != '\0') {
            throw new InvalidMoveException("Cell already occupied.");
        }

        board[row][col] = symbol;
        isXTurn = !isXTurn;
        checkForWinner();
    }

    private void checkForWinner() {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '\0' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                isFinished = true;
                winner = String.valueOf(board[i][0]);
                return;
            }
            if (board[0][i] != '\0' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                isFinished = true;
                winner = String.valueOf(board[0][i]);
                return;
            }
        }
        if (board[0][0] != '\0' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            isFinished = true;
            winner = String.valueOf(board[0][0]);
            return;
        }
        if (board[0][2] != '\0' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            isFinished = true;
            winner = String.valueOf(board[0][2]);
            return;
        }

        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            isFinished = true;
        }
    }

}