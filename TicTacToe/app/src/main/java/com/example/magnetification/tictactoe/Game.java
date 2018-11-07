package com.example.magnetification.tictactoe;

import android.service.quicksettings.Tile;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i<BOARD_SIZE; i++)
            for (int j = 0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        TileState xy = board[row][column];

        if (xy.equals(TileState.BLANK)) {
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS
            } else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }
        } else {
            return TileState.INVALID;
        }
    }
}
