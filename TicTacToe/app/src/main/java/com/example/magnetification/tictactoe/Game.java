package com.example.magnetification.tictactoe;

import android.service.quicksettings.Tile;

import java.io.Serializable;

public class Game implements Serializable {
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
                return TileState.CROSS;
            } else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }
        } else {
            return TileState.INVALID;
        }
    }

    public GameState won() {
        if (threeInARow())
            if (playerOneTurn)
                return GameState.PLAYER_TWO;
            else
                return GameState.PLAYER_ONE;

        boolean filled = true;
        for (int i = 0; i<BOARD_SIZE; i++)
            for (int j = 0; j<BOARD_SIZE; j++)
                if (board[i][j].equals(TileState.BLANK))
                    filled = false;

        if (filled)
            return GameState.DRAW;

        return GameState.IN_PROGRESS;
    }

    private Boolean threeInARow() {
        for (int i = 0; i<BOARD_SIZE; i++) {
            if (    (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && board[i][0] != TileState.BLANK) ||
                    (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && board[0][i] != TileState.BLANK))
                return true;
        }

        return (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[0][0] != TileState.BLANK) ||
                (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[0][2] != TileState.BLANK);
    }

    public TileState getState(int row, int column) {
        return board[row][column];
    }

    public int[] getCoords(int id) {
        int[] coords = new int[2];
        System.out.println(id);

        switch (id) {
            case 2131165216:
                coords = new int[]{0, 0};
                break;
            case 2131165217:
                coords = new int[]{0, 1};
                break;
            case 2131165218:
                coords = new int[]{0, 2};
                break;
            case 2131165219:
                coords = new int[]{1, 0};
                break;
            case 2131165220:
                coords = new int[]{1, 1};
                break;
            case 2131165221:
                coords = new int[]{1, 2};
                break;
            case 2131165222:
                coords = new int[]{2, 0};
                break;
            case 2131165223:
                coords = new int[]{2, 1};
                break;
            case 2131165224:
                coords = new int[]{2, 2};
                break;
        }

        return coords;
    }

    public int getID(int row, int column) {
        int id = 0;

        switch (row) {
            case 0:
                switch (column) {
                    case 0:
                        id = 2131165216;
                        break;
                    case 1:
                        id = 2131165217;
                        break;
                    case 2:
                        id = 2131165218;
                        break;
                }
                break;
            case 1:
                switch (column) {
                    case 0:
                        id = 2131165219;
                        break;
                    case 1:
                        id = 2131165220;
                        break;
                    case 2:
                        id = 2131165221;
                        break;
                }
                break;
            case 2:
                switch (column) {
                    case 0:
                        id = 2131165222;
                        break;
                    case 1:
                        id = 2131165223;
                        break;
                    case 2:
                        id = 2131165224;
                        break;
                }
                break;
        }

        return id;
    }
}
