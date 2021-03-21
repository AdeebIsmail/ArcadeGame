## Wumpus World
# Made for NCHack March 2021

This project was made in the Java 8 JDK. Please download and install this JDK to ensure that the project can run. You can find it here - https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

If you are using Intellij, press ctrl + alt + s + shift, go to Project and change the sdk to version 1.8.x and make sure the Project language level is at 8


## Welcome to Wumpus World 2.0
In this immmersive top-down adventure game, the player must fulfill his quest of finding the hidden treasure of the caves - the gold. But beware, a Giant monster, known as a Wumpus, guards this cave. The cave is entirely unknown to the player - walking over a tile is the only thing that reveals the tile's contents.

# The tiles
Blank tile - This tile is nothing special, just something that the player can walk on<br/>
Pit tile - This tile contains a pit - if the player walks on it, then the player falls and dies
Breeze tile - This tile has a breeze. The breezes are the blue wavy lines which are always fully surrounding a pit on the cardinal directions. They are safe to walk on, but they indicate that there is the danger of a pit nearby
Wumpus tile - This tile contains the Wumpus - if the player walks on it, then the player gets eaten and dies
Stench tile - This tile has a stench. The stenches are the green wavy lines which are always fully surrounding the Wumpus on the cardinal directions. They are safe to walk on, but they indicate that there is the danger of the Wumpus nearby. 
Ladder tile - What the player spawns on, and the thing that the player must walk to in order to escape the cave after getting the gold
Gold tile - The goal of the game. The player must walk to the gold and pick it up. If done so, the gold will go into the player's inventory and the player can then succesfully leave the cave.


## The Objective
The player must simply get the gold while avoiding the pits and Wumpus. The player can utilize his one arrow to kill the Wumpus if he can surmise its location, making it safe to walk on the same tile as the Wumpus once was.


## Controls
W - Moves the player character up
A - Moves the player character to the left
S - Moves the player character down
D - Moves the player character to the right
I - Shoots your one arrow up
J - Shoots your one arrow to the left
K - Shoots your one arrow down
L - Shoots your one arrow to the left
P - Pick up gold when you are on the same tile as the gold
C - Climb the ladder (Can only be done if the gold is in the inventory of the player, and the player is on the same tile as the ladder
N - Starts a new game after death
8 - Turns on Cheat Mode, reveals all of the tiles.


If there are any issues with JavaFX not compiling, refer to this https://openjfx.io/openjfx-docs/#introduction
