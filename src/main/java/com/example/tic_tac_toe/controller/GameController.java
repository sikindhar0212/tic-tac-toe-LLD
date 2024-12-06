package com.example.tic_tac_toe.controller;

import com.example.tic_tac_toe.model.Game;
import com.example.tic_tac_toe.model.Move;
import com.example.tic_tac_toe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public String createGame(@RequestParam String playerX, @RequestParam String playerO) {
        return gameService.createGame(playerX, playerO);
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping("/{gameId}/move")
    public void makeMove(@PathVariable String gameId, @RequestBody Move move) {
        gameService.makeMove(gameId, move);
    }
}