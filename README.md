# Arcade
Welcome to the text-based arcade! In this arcade, simply type the name of the four included games that you want to play, and boom! You can play those games.







# Wumpus World
## Made for NCHack March 2021

This project was made in the Java 8 JDK. Please download and install this JDK to ensure that the project can run. You can find it here - https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

If you are using Intellij, press ctrl + alt + s + shift, go to Project and change the sdk to version 1.8.x and make sure the Project language level is at 8
<br/>

## Welcome to Wumpus World 2.0
In this immmersive top-down adventure game, the player must fulfill his quest of finding the hidden treasure of the caves - the gold. But beware, a Giant monster, known as a Wumpus, guards this cave. The cave is entirely unknown to the player - walking over a tile is the only thing that reveals the tile's contents.

## The tiles
Blank tile - This tile is nothing special, just something that the player can walk on<br/>
Pit tile - This tile contains a pit - if the player walks on it, then the player falls and dies<br/>
Breeze tile - This tile has a breeze. The breezes are the blue wavy lines which are always fully surrounding a pit on the cardinal directions. They are safe to walk on, but they indicate that there is the danger of a pit nearby<br/>
Wumpus tile - This tile contains the Wumpus - if the player walks on it, then the player gets eaten and dies<br/>
Stench tile - This tile has a stench. The stenches are the green wavy lines which are always fully surrounding the Wumpus on the cardinal directions. They are safe to walk on, but they indicate that there is the danger of the Wumpus nearby <br/>
Ladder tile - What the player spawns on, and the thing that the player must walk to in order to escape the cave after getting the gold<br/>
Gold tile - The goal of the game. The player must walk to the gold and pick it up. If done so, the gold will go into the player's inventory and the player can then succesfully leave the cave.<br/>
<br/>

## The Objective
The player must simply get the gold while avoiding the pits and Wumpus. The player can utilize his one arrow to kill the Wumpus if he can surmise its location, making it safe to walk on the same tile as the Wumpus once was.


## Controls
W - Moves the player character up<br/>
A - Moves the player character to the left<br/>
S - Moves the player character down<br/>
D - Moves the player character to the right<br/>
I - Shoots your one arrow up<br/>
J - Shoots your one arrow to the left<br/>
K - Shoots your one arrow down<br/>
L - Shoots your one arrow to the left<br/>
P - Pick up gold when you are on the same tile as the gold<br/>
C - Climb the ladder (Can only be done if the gold is in the inventory of the player, and the player is on the same tile as the ladder)<br/>
N - Starts a new game after death<br/>
8 - Turns on Cheat Mode, reveals all of the tiles.<br/>

# Tower of Hanoi
## How to play
The aim of the game is to move all the disks on the first pillar to the last pillar. You cannot put the larger disk on a smaller disk. Press 1,2 or 3 to select which pole you would like to move the top most disk. Then press 1, 2 or 3 to move that disk to the corresponding pole.
# Shape Smasher
## How to play
The aim of the game is to get the highest score possiable. Clicking the triangle will give you 5 points, circle 3, square 1. Each shape stay on the screen in a spot for a certain amount of time. If you do not click the shape before it moves, then you will lose one life. You get three lives and each time you lose one a skull appears in the bottom of the screen.
# Hey That's my Fish
## How to play
This is a two player game and the aim is to get more points than your enemy. Each player gets 5 penguins. At the start of the game, each player takes turns placeing down their penguin. At the start the penguins can only be placed on the 1 fish square. After that, each player takes turns moving their penguins. On your turn, you can only choose one of your penguins to move.

<br/>
If there are any issues with JavaFX not compiling, refer to this https://openjfx.io/openjfx-docs/#introduction
