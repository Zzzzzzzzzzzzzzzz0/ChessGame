import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import icons.Icons;


//This is the Game Class and it is for starting the game and placing all the chess pieces in specific location.
//This class have "Have-A" relationship with Board class, and "HAS-A" relationship with Pawn,Bishop,Knight,Rook,King,Queen.
public class Game
{
	Board board;
	Pawn blackpawn1,blackpawn2,blackpawn3,blackpawn4,blackpawn5,blackpawn6,blackpawn7,blackpawn8,
		whitePawn1,whitePawn2,whitePawn3,whitePawn4,whitePawn5,whitePawn6,whitePawn7,whitePawn8;
	Bishop whiteBishop1,whiteBishop2,blackBishop1,blackBishop2;
	Knight whiteKnight1,whiteKnight2,blackKnight1,blackKnight2;
	Rook whiteRook1,whiteRook2,blackRook1,blackRook2;
	King whiteKing,blackKing;
	Queen whiteQueen,blackQueen;
	
	//constructor of the class.
	public Game()
	{
		startGame();
	}

	/**
	 * Placing all the game pieces in specific location
	 */
	public void startGame()
	{
		board = new Board();
		blackpawn1 = new Pawn(board, 1, 0,Icons.pawnBlack);
		blackpawn1.startPawn();
		blackpawn2 = new Pawn(board, 1, 1,Icons.pawnBlack);
		blackpawn2.startPawn();
		blackpawn3 = new Pawn(board, 1, 2,Icons.pawnBlack);
		blackpawn3.startPawn();
		blackpawn4 = new Pawn(board, 1, 3,Icons.pawnBlack);
		blackpawn4.startPawn();
		blackpawn5 = new Pawn(board, 1, 4,Icons.pawnBlack);
		blackpawn5.startPawn();
		blackpawn6 = new Pawn(board, 1, 5,Icons.pawnBlack);
		blackpawn6.startPawn();
		blackpawn7 = new Pawn(board, 1, 6,Icons.pawnBlack);
		blackpawn7.startPawn();
		blackpawn8 = new Pawn(board, 1, 7,Icons.pawnBlack);
		blackpawn8.startPawn();
	
		whitePawn1 = new Pawn(board, 6, 0,Icons.pawnWhite);
		whitePawn1.startPawn();
		whitePawn2 = new Pawn(board, 6, 1,Icons.pawnWhite);
		whitePawn2.startPawn();
		whitePawn3 = new Pawn(board, 6, 2,Icons.pawnWhite);
		whitePawn3.startPawn();
		whitePawn4 = new Pawn(board, 6, 3,Icons.pawnWhite);
		whitePawn4.startPawn();
		whitePawn5 = new Pawn(board, 6, 4,Icons.pawnWhite);
		whitePawn5.startPawn();
		whitePawn6 = new Pawn(board, 6, 5,Icons.pawnWhite);
		whitePawn6.startPawn();
		whitePawn7 = new Pawn(board, 6, 6,Icons.pawnWhite);
		whitePawn7.startPawn();
		whitePawn8 = new Pawn(board, 6, 7,Icons.pawnWhite);
		whitePawn8.startPawn();
		
		whiteBishop1 = new Bishop(board,7,2,Icons.bishopWhite);
		whiteBishop1.startBishop();
		whiteBishop2 = new Bishop(board,7,5,Icons.bishopWhite);
		whiteBishop2.startBishop();

		blackBishop1 = new Bishop(board,0,2,Icons.bishopBlack);
		blackBishop1.startBishop();
		blackBishop2 = new Bishop(board,0,5,Icons.bishopBlack);
		blackBishop2.startBishop();
		
		whiteKnight1 = new Knight(board,7,1,Icons.knightWhite);
		whiteKnight1.startKnight();
		whiteKnight2 = new Knight(board,7,6,Icons.knightWhite);
		whiteKnight2.startKnight();
		
		blackKnight1 = new Knight(board,0,1,Icons.knightBlack);
		blackKnight1.startKnight();
		blackKnight2 = new Knight(board,0,6,Icons.knightBlack);
		blackKnight2.startKnight();
		
		whiteRook1 = new Rook(board,7,0,Icons.rookWhite);
		whiteRook1.startRook();
		whiteRook2 = new Rook(board,7,7,Icons.rookWhite);
		whiteRook2.startRook();
		
		blackRook1 = new Rook(board,0,0,Icons.rookBlack);
		blackRook1.startRook();
		blackRook2 = new Rook(board,0,7,Icons.rookBlack);
		blackRook2.startRook();
		
		whiteKing = new King(board,7,4,Icons.kingWhite);
		whiteKing.startKing();
		
		blackKing = new King(board,0,4,Icons.kingBlack);
		blackKing.startKing();
		
		whiteQueen = new Queen(board,7,3,Icons.queenWhite);
		whiteQueen.startQueen();
		
		blackQueen = new Queen(board,0,3,Icons.queenBlack);
		blackQueen.startQueen();
	}

	/**
	 * This method will write the game result to the gameHistory file to store the result.
	 * @param a String value that will write to the file.(eg. Player1 Vs Player2 Player2 wins the game)
	 */
	public static void gameHistory(String a)
	{
		try {
		      FileWriter myWriter = new FileWriter("gameHistory.txt",true);
		      myWriter.write(a + "\n");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    }
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	
}
