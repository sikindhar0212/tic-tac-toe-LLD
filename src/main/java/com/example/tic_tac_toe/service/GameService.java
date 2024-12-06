package com.example.tic_tac_toe.service;

import com.example.tic_tac_toe.exception.GameNotFoundException;
import com.example.tic_tac_toe.exception.InvalidMoveException;
import com.example.tic_tac_toe.exception.GameAlreadyFinishedException;
import com.example.tic_tac_toe.model.Game;
import com.example.tic_tac_toe.model.Move;
import com.example.tic_tac_toe.model.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    private Map<String, Game> games = new HashMap<>();

    public String createGame(String playerXName, String playerOName) {
        Game game = new Game(new Player(playerXName, 'X'), new Player(playerOName, 'O'));
        String gameId = String.valueOf(games.size() + 1);
        games.put(gameId, game);
        return gameId;
    }

    public Game getGame(String gameId) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new GameNotFoundException("Game not found.");
        }
        return game;
    }

    public void makeMove(String gameId, Move move) {
        Game game = getGame(gameId);
        if (game.isFinished()) {
            throw new GameAlreadyFinishedException("Game is already finished.");
        }
        game.makeMove(move);
    }

    public Player getPlayerX(String gameId) {
        return getGame(gameId).getPlayerX();
    }

    public Player getPlayerO(String gameId) {
        return getGame(gameId).getPlayerO();
    }

}