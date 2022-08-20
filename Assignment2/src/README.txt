How to play:
Flag all mines on the board and don't reveal any.
Left click to reveal tiles
Right click to place/remove flags.


Displaying the data:

My GUI has several components for structure to make it appear as it does.My options and statistics are held in a 
HBOX named topContainer this contains my menubar, the clock, the flag count and the flags variable. I have used 
spacing to space them apart and have set the priority to be the width of the scene so it always fits. The GUI is 
kept up to date by clearing/ removing the needed content and re-adding it.

Editting the data:
Whenever a tile is clicked whether right or left clicked, the appropriate method is called to adjust the data and 
appearance of the board. When the dialog buttons are pressed the same occurs. And when textFields requiring desired
parameters for a new game are entered. This data is eddited by created a new board with the inputted data. Flags
placed on board are counted and removed as they are placed and removed.

Optional Extras:
A working timer - records the time taken (in seconds) for user to either lose or win. Tells you how long it took 
either way your game goes (win or lose).

Fully customisable dimension of game and number of mines - rather than doing a set difficulty user can chose to 
what level they want to play at themselves.

Flag counter - game counts number of flags on board.

Aesthetics - made game look more appealing rather than simple basic button look

Options button -

added option menu for player to exit game or create a new game

Screen resizes for bigger built boards

Retry and change difficulty option on win/loss - after losing or winning a game a dialog appears with the option
to retry the last difficulty played or to change difficulty
