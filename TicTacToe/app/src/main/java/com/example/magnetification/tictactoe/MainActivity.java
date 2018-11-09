package com.example.magnetification.tictactoe;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Game game;
    GameState gameState = GameState.IN_PROGRESS;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.newpc:
            oneplayerClicked();
            Toast.makeText(this, "Starting a game against the computer..", Toast.LENGTH_SHORT).show();
            return(true);
        case R.id.new2player:
            twoplayerClicked();
            Toast.makeText(this, "Starting a game with 2 players..", Toast.LENGTH_SHORT).show();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!= null) {
            game = (Game) savedInstanceState.getSerializable("Game");
            gameState = (GameState) savedInstanceState.getSerializable("GameState");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    TileState state = game.getState(i, j);
                    int id = game.getID(i, j);
                    ImageButton button = findViewById(id);

                    if (state.equals(TileState.CROSS)) {
                        button.setImageResource(R.drawable.cross);
                        button.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    } else if (state.equals(TileState.CIRCLE)) {
                        button.setImageResource(R.drawable.dot);
                        button.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }
                }
            }
        } else {
            game = new Game();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Game", game);
        outState.putSerializable("GameState", gameState);
    }

    public void tileClicked(View view) {
        int id = view.getId();
        int[] coords = game.getCoords(id);

        TileState state = game.choose(coords[0], coords[1]);
        ImageButton button = findViewById(id);

        if (gameState.equals(GameState.IN_PROGRESS)) {
            switch (state) {
                case CROSS:
                    button.setImageResource(R.drawable.cross);
                    button.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case CIRCLE:
                    button.setImageResource(R.drawable.dot);
                    button.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case INVALID:
                    Toast.makeText(this, "You can't select this button", Toast.LENGTH_SHORT).show();
                    break;
            }

            gameState = game.won(gameState);

            switch (gameState) {
                case PLAYER_ONE:
                    Toast.makeText(this, "Crosses wins!", Toast.LENGTH_SHORT).show();
                    break;
                case PLAYER_TWO:
                    Toast.makeText(this, "Circles wins!", Toast.LENGTH_SHORT).show();
                    break;
                case DRAW:
                    Toast.makeText(this, "No one wins, no one loses.\n Hooray for communism!", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (gameState.equals(GameState.COMPUTER)) {
            switch (state) {
                case CROSS:
                    button.setImageResource(R.drawable.cross);
                    button.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case INVALID:
                    Toast.makeText(this, "You can't select this button", Toast.LENGTH_SHORT).show();
                    break;
            }

            gameState = game.won(gameState);
            switchGameState(gameState);

            if (gameState.equals(GameState.COMPUTER)) {
                int[] cp = game.computerMove();
                id = game.getID(cp[0], cp[1]);

                button = findViewById(id);
                button.setImageResource(R.drawable.dot);
                button.setScaleType(ImageView.ScaleType.FIT_CENTER);

                gameState = game.won(gameState);
                switchGameState(gameState);
            }
        } else {
            Toast.makeText(this, "Game has ended, start new game to continue!", Toast.LENGTH_SHORT).show();
        }
    }

    private void oneplayerClicked() {
        game = new Game();
        gameState = GameState.COMPUTER;
        setContentView(R.layout.activity_main);
    }

    private void twoplayerClicked() {
        game = new Game();
        gameState = GameState.IN_PROGRESS;
        setContentView(R.layout.activity_main);
    }

    private void switchGameState(GameState gameState) {
        switch (gameState) {
            case PLAYER_ONE:
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
                break;
            case PLAYER_TWO:
                Toast.makeText(this, "Computer wins!", Toast.LENGTH_SHORT).show();
                break;
            case DRAW:
                Toast.makeText(this, "No one wins, no one loses.\n Hooray for communism!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
