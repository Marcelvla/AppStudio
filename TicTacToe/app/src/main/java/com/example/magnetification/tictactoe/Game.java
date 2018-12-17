package com.example.magnetification.tictactoe;

import android.service.quicksettings.Tile;
import android.support.v4.content.res.TypedArrayUtils;
import android.widget.Toast;

import java.io.Serializable;
import java.net.IDN;
import java.util.Arrays;
import java.util.Random;

public class Game implements Serializable {

    // Global Variables
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;
    private int[] ids = new int[]{2131165216, 2131165217, 2131165218, 2131165219, 2131165220, 2131165221, 2131165222, 2131165223, 2131165224};

    // Class constructor, makes a 2D array that stores the current game state
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i<BOARD_SIZE; i++)
            for (int j = 0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
    }

    // Function that fills in the square in the game board with a cross or a circle if the square
    // is still available.
    public TileState choose(int row, int column) {
        TileState xy = board[row][column];

        if (xy.equals(TileState.BLANK)) {
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                removeID(row, column);
                return TileState.CROSS;
            } else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                removeID(row, column);
                return TileState.CIRCLE;
            }
        } else {
            return TileState.INVALID;
        }
    }

    // Function that checks if the game is over, either if someone has won or the entire board is full
    public GameState won(GameState gameState) {
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

        return gameState;
    }

    // Function that checks if someone there are either 3 crosses or circles in a row
    private Boolean threeInARow() {
        for (int i = 0; i<BOARD_SIZE; i++) {
            if ((board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && board[i][0] != TileState.BLANK) ||
                (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && board[0][i] != TileState.BLANK))
                return true;
        }

        return (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[0][0] != TileState.BLANK) ||
                (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[0][2] != TileState.BLANK);
    }

    // Function that returns the value of square
    public TileState getState(int row, int column) {
        return board[row][column];
    }

    // Function that determines the next computermove by randomly selecting a tile from the tiles
    // that are still available.
    public int[] computerMove() {
        Random randomGenerator = new Random();
        int ID = ids[randomGenerator.nextInt(ids.length)];
        int[] co = getCoords(ID);
        playerOneTurn = true;
        removeID(co[0], co[1]);
        board[co[0]][co[1]] = TileState.CIRCLE;
        return new int[]{co[0], co[1]};
    }

    // Function that returns the coordinates of the board based on the id of the button in the gridlayout
    public int[] getCoords(int id) {
        int[] coords = new int[2];

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

    // Function that returns the ID of the item in the gridlayout based on the coordinates in the
    // board
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

    // Function that removes a coordinate from the ids array, which is used by the computer to make
    // its next move.
    private void removeID(int row, int col) {
        int [] temp = new int[ids.length - 1];
        int ID = getID(row, col);
        int next = 0;
        for (int id : ids) {
            if (id != ID && next < ids.length -1) {
                temp[next] = id;
                next++;
            }
        }
        ids = temp;
    }
}
