						Lpoo 2016-2017 T4G02 Project 2
	
	
 
 
 UML Class Diagram:
 
 
 ![UML Diagram](https://github.com/TheBrunoMiguel/Lpoo--2016-2017-T4G02-Project2/blob/master/Assets/Diagrama%20de%20Classes%20UML.png)
 
 
 
 
 
 	Design Patterns to use:



Sigleton -> The Singleton Pattern will be used in the game class, to ensure that only one instance of this class is created throughout the execution of the program

Template -> The Template Pattern will be used in the main and game classes, to build a stable structure for the game cycle 

Prototype -> The Prototype Pattern will be used in the enemy class, to handle the way the spawn of the enemies occurs.

Double Buffer -> The Double Bufffer Pattern will be used in the game class, to make sure the "world" the user is watching is coherent and well drawn




	GUI functionalities:

The GUI will be responsible for drawing the game, at a certain render rate.



	Game Mock-ups:

![Simple Game Mock Up](https://github.com/TheBrunoMiguel/Lpoo--2016-2017-T4G02-Project2/blob/master/Assets/Simple%20Game.png)
![Leaderboard Mock Up](https://github.com/TheBrunoMiguel/Lpoo--2016-2017-T4G02-Project2/blob/master/Assets/Leaderboard%20MockUp%20Image.png)
![Menu Mock Up](https://github.com/TheBrunoMiguel/Lpoo--2016-2017-T4G02-Project2/blob/master/Assets/Menu%20Mockup%20Image.png)
![Options Mock Up](https://github.com/TheBrunoMiguel/Lpoo--2016-2017-T4G02-Project2/blob/master/Assets/Options%20MockUp%20Image.png)




	Test Design:


In the final test cases we intend to cover: 
- the Player's Movement (hitting world borders, moving "into" enemies, etc), as well as the enemies' movement
- the "battle" between the Player and the Enemies (the way the Player hits/hurts the enemies, and the other way around)
- the functionalities of the main menu (the functionalities of the several buttons, like leaderboards, options, etc)
