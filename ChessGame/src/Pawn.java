import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import icons.Icons;

/**
 * Pawn class "Have-A" relationship with Board class. And "Is-A" relationship with Piece class"
 * This class represent the Black and White Bishop piece in ChessGame.
 */
public class Pawn extends Piece {
	
	private static int attackX;
    private static int attackY;

	//constructor of the class
	public Pawn (Board board, int x, int y, ImageIcon image) {
        super(board, x, y, image);
    }

	/**
	 * start placing the pawn position
	 */
    public void startPawn()
    {
		board.squares[xLocation][yLocation].setIcon(image);
    }
	

    /**
	 * This method is to show the valid moves for the Pawn piece
     * @param x current x Location of the Pawn piece
     * @param y current y location of the Pawn piece
	 */
	public static void validateMove(int x, int y)
	{
		if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
		{
			if(x ==6 )
			{
				if(checkBlackOrWhite(board.squares[x - 1][y]) == "BLANK")
				{
					board.squares[x -1][y].setBackground(Color.green);
					if(checkBlackOrWhite(board.squares[x - 2][y]) == "BLANK")
					{
						board.squares[x -2][y].setBackground(Color.green);
					}
				}
			}
			else
			{
				if(checkBlackOrWhite(board.squares[x - 1][y]) == "BLANK")
					board.squares[x -1][y].setBackground(Color.green);
			}
			checkAttack(x,y);
		}
		else
		{
			if(x == 1)
			{
				if(checkBlackOrWhite(board.squares[x + 1][y]) == "BLANK")
				{
					board.squares[x + 1][y].setBackground(Color.green);
					if(checkBlackOrWhite(board.squares[x + 2][y]) == "BLANK")
					{

						board.squares[x  + 2][y].setBackground(Color.green);
					}
				}
			}
			else
			{	
				if(checkBlackOrWhite(board.squares[x + 1][y]) == "BLANK")
					board.squares[x + 1][y].setBackground(Color.green);
			}
			checkAttack(x,y);
		}
	}
	
	/**
	 * Checking attack position for the Pawn
	 * @param x x location of the pawn
	 * @param y y location of the pawn
	 */
	public static void checkAttack(int x, int y)
	{
		if(y!=0)
		{
			for(int i = x - 1; i <= x +1 && i < 8; i++)
			{
				for(int j = y - 1; j <= y + 1 && j < 8; j++)
				{
					if(Math.abs(x-i) == Math.abs(y-j))
					{
						if(checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							attackX = i;
							attackY = j;
							if(attackX > x)
								board.squares[attackX][attackY].setBackground(Color.red);
						}
						else if(Piece.checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							attackX = i;
							attackY = j;
							if(attackX < x)
								board.squares[attackX][attackY].setBackground(Color.red);;
						}
					}
				}
			}
		}
		if(y == 0)
		{
			for(int i = x - 1; i <= x +1 && i < 8; i++)
			{
				for(int j = y; j <= y + 1 && j < 8; j++)
				{
					if(Math.abs(x-i) == Math.abs(y-j))
					{
						if(checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							attackX = i;
							attackY = j;
							if(attackX > x)
								board.squares[attackX][attackY].setBackground(Color.red);
						}
						else if(Piece.checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							attackX = i;
							attackY = j;
							if(attackX < x)
								board.squares[attackX][attackY].setBackground(Color.red);;
						}
					}
				}
			}
		}
	}
	
	/**
	 * This method will check if the Bishop is in attacking position to the King
	 * @param x x Location of the Bishop
	 * @param y y Location of the Bishop
	 * @return true if the Bishop is in attacking position to the King Otherwise false
	 */
	public static boolean checkAttackForKing(int x, int y)
	{
		if(y!=0)
		{
			for(int i = x - 1; i <= x +1 && i < 8; i++)
			{
				for(int j = y - 1; j <= y + 1 && j < 8; j++)
				{
					if(Math.abs(x-i) == Math.abs(y-j))
					{
						if(checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							attackX = i;
							attackY = j;
							if(attackX > x)
							{
								if(getPiece(board.squares[attackX][attackX]) == "KING")
								{
									return true;
								}
							}
						}
						else if(Piece.checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							attackX = i;
							attackY = j;
							if(attackX < x)
							{
								if(getPiece(board.squares[attackX][attackX]) == "KING")
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		if(y == 0)
		{
			for(int i = x - 1; i <= x +1 && i < 8; i++)
			{
				for(int j = y; j <= y + 1 && j < 8; j++)
				{
					if(Math.abs(x-i) == Math.abs(y-j))
					{
						if(checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							attackX = i;
							attackY = j;
							if(attackX > x)
							{
								if(getPiece(board.squares[attackX][attackX]) == "KING")
								{
									return true;
								}
							}
						}
						else if(Piece.checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							attackX = i;
							attackY = j;
							if(attackX < x)
							{
								if(getPiece(board.squares[attackX][attackX]) == "KING")
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * This method will check if the pawn need to be promotion or not
	 * @param x x location of the pawn piece
	 * @param y y location of the pawn piece
	 * @return true if the pawn is in a position to be promoted
	 */
	public static boolean Ispromotion(int x, int y)
	{
		boolean result = false;
		if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
		{
			if(x == 0)
			{
				return true;
			}
		}
		else if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
		{
			if(x == 7)
			return true;
		}
		return result;
	}
	
	/**
	 * This method is to promote the pawn to the desired piece
	 * @param x x location of the pawn
	 * @param y y location of the pawn
	 */
	public static void promotion(int x, int y)
	{
		if(Board.userInputforPromotion.equals("Q") || Board.userInputforPromotion.equals("q") )
		{
			if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
			{
				Queen pQueen = new Queen(board,x,y,Icons.queenWhite);
				pQueen.startQueen();
			}
			else
			{	
				Queen pQueen = new Queen(board,x,y,Icons.queenBlack);
				pQueen.startQueen();
			}

		}
		else if(Board.userInputforPromotion.equals("R")  || Board.userInputforPromotion.equals("r") )
		{
			if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
			{
				Rook pRook = new Rook(board,x,y,Icons.rookWhite);
				pRook.startRook();
			}
			else
			{
				Rook pRook = new Rook(board,x,y,Icons.rookBlack);
				pRook.startRook();
			}
		}
		else if(Board.userInputforPromotion.equals("B")  || Board.userInputforPromotion.equals("b") )
		{
			if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
			{
				Bishop pBishop = new Bishop(board,x,y,Icons.bishopWhite);
				pBishop.startBishop();
			}
			else
			{
				Bishop pBishop = new Bishop(board,x,y,Icons.bishopBlack);
				pBishop.startBishop();
			}
		}
		else if(Board.userInputforPromotion.equals("K")  ||Board.userInputforPromotion.equals("k") )
		{
			if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
			{
				Knight pKnight = new Knight(board,x,y,Icons.knightWhite);
				pKnight.startKnight();
			}
			else 
			{
				Knight pKnight = new Knight(board,x,y,Icons.knightBlack);
				pKnight.startKnight();
			}
		}
		else
		{
			
		}
		
	}
}