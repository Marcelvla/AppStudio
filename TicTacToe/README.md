USER GUIDE FOR TICTACTOE:

On startup the app goes to a two player game where crosses starts first. When the game has ended you
can start a new one by opening the menu and selecting if you want to start a single or two player game. The computer
makes moves at random from the possible solutions, so it shouldn't be too hard to defeat it every once in a while. 

Basic Functionality:
When a tile is clicked the app checks the GameState. For IN_PROGRESS it will check if the tile selected is empty and sets
a cross or circle depending on the value of playerOneTurn. After every click the app also checks if the game is over. The 
different cases can be that either one of the players has three in a row, or that it's a draw. Checking if the board is filled 
is done by looping over the entire board. Since there is an array with all available tiles for the computergame, this could be 
done more efficiently by checking the length of the array, but this works as well so I won't change that for the time being 
(and probably not ever, because let's be honest this app will never make it to the app store). 

Implementation of computergame:
Added an extra value to the GameState Enum to keep track between a regular two player game which has GameState COMPUTER. 
After every user move the computer checks available tiles and chooses one randomly. The available tiles are being stored
in an array where the button that is selected will be removed. 

Some notes:
Added functionality could include keeping score of number of games won by the players, changing who goes first in
a computergame and making a function for a better strategy for the computer. 
