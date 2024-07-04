# ConnectX
This project should be runnable with JDK17

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [How to Play](#how-to-play)
- [Contribution Statements](#Contribution Statements)

## Introduction
ConnectX is a strategic game where players take turns dropping pieces into a grid, aiming to connect a sequence of pieces horizontally, vertically, or diagonally.This project delivers a robust game board structure with flexible dimensions and win conditions, allowing for strategic token dropping mechanics. Comprehensive unit tests were designed and executed to validate the accuracy of win conditions, ensuring reliability and a bug-free gaming experience.

## Features
- Scalable-player game modes
- Adjustable grid size
- Customizable winning sequence length
- Simple and intuitive user interface
- Real-time game status updates
- Comprehensive unit tests for reliability

## How to Play
 **Game Rules:**
   - Players take turns to drop their pieces into one of the columns.
   - The first player to connect a sequence of pieces (horizontally, vertically, or diagonally) wins the game.
   - The game ends in a draw if the grid is completely filled without any player connecting the required sequence.

*To compile and run the game type "make" or  "make run" to the console and it will start the game

*If you wish to test the program, enter "make test" to compile and "make testGB" or "make testGBmem" to run the tests.

## Contribution Statements:
Ryan Nelson(Ryan-Nelson1): Wrote some Non-function requirements, fixed the functional and non-functional requirements, wrote UML for gameScreen. Wrote BoardPosition.Java and GameScreen.Java.

Kaylee Pierce(kayleepierce): Wrote some functional and non-fucntional requirements (and corrected them). Wrote the UML's for GameBoard, BoardPosition, IGameBoard, AbsGameBoard, and corrected GameScreen UML. Wrote most of the code for IGameBoard, AbsGameBoard, GameScreen, GameBoard, and BoardPosition (and made corrections on these files after feedback). Wrote several test cases, make corrections based on project 3, and helped with the pdf.

Anthony Hay(anthayjr): Wrote some functional requirements and helped fix them. Made corrections to boardPosition and looked over files to add what was missing per instructor feedback / comments. Wrote several test cases.

Ashok Patel(ashokptl): Wrote some functional and non-functional statements. Wrote GameBoard.java as well a little bit of GameScreen.java and looked over corrections to Gameboard.java and BoardPosition.java. Wrote several tests and updated tests documentation for those test.
