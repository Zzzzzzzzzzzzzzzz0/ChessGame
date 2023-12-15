import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Rook class "Have-A" relationship with Board class. And "Is-A" relationship with Piece class"
 * This class represent the Black and White Rook piece in ChessGame.
 */
public class Rook extends Piece
{
	//constructor of the class
	public Rook(Board board,int x, int y, Icon image)
	{
		super(board,x, y, image);
	}

	//start placing the Rook 
	public void startRook()
	{
		board.squares[xLocation][yLocation].setIcon(image);
	}

    /**
	 * This method is to show the valid moves for the Rook piece
     * @param x current x Location of the Rook piece
     * @param y current y location of the Rook piece
	 */
	public static void validateMove(int x, int y)
	{
		//for down	
		int i = x + 1;
		while(i < 8 && getPiece(board.squares[i][y]) == "BLANK" )
		{
			board.squares[i][y].setBackground(Color.green);
			i++;
		}
		if(i < 8 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[i][y]) == "WHITE")
		{
			board.squares[i][y].setBackground(Color.red);
		}
		else if(i < 8 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[i][y]) == "BLACK")
		{
			board.squares[i][y].setBackground(Color.red);
		}
		// for up 
		int j = x - 1;
		while( j >= 0 && getPiece(board.squares[j][y]) == "BLANK")
		{
			board.squares[j][y].setBackground(Color.green);
			j--;
		}
		if(j >= 0 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[j][y]) == "WHITE")
		{
			board.squares[j][y].setBackground(Color.red);
		}
		else if(j >= 0 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[j][y]) == "BLACK")
		{
			board.squares[j][y].setBackground(Color.red);
		}
		
		
		// for right
		int k = y + 1;
		while(k < 8 && getPiece(board.squares[x][k]) == "BLANK" )
		{
			board.squares[x][k].setBackground(Color.green);
			k++;
		}
		if(k < 8 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[x][k]) == "WHITE" )
		{
			board.squares[x][k].setBackground(Color.red);
		}
		else if(k < 8 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[x][k]) == "BLACK" )
		{
			board.squares[x][k].setBackground(Color.red);
		}
		// for left
		int l = clickedY - 1;
		while(l >= 0 && getPiece(board.squares[x][l]) == "BLANK")
		{
			board.squares[x][l].setBackground(Color.green);
			l--;
		}
		if(l >= 0 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[x][l]) == "WHITE" )
		{
			board.squares[x][l].setBackground(Color.red);
		}
		else if(l >= 0 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[x][l]) == "BLACK" )
		{
			board.squares[x][l].setBackground(Color.red);
		}
	}

	/**
	 * This method will check if the Rook is in attacking position to the King
	 * @param x x Location of the Rook
	 * @param y y Location of the Rook
	 * @return true if the Rook is in attacking position to the King Otherwise false
	 */
	public static boolean checkAttackForKing(int x, int y)
	{

		int i = x + 1;
		while(i < 8 && getPiece(board.squares[i][y]) == "BLANK" )
		{
			i++;
		}
		if(i < 8 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[i][y]) == "WHITE")
		{
			if(getPiece(board.squares[i][y]) == "KING")
			{
				return true;
			}
		}
		else if(i < 8 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[i][y]) == "BLACK")
		{
			if(getPiece(board.squares[i][y]) == "KING")
			{
				return true;
			}
		}
		// for up 
		int j = x - 1;
		while( j >= 0 && getPiece(board.squares[j][y]) == "BLANK")
		{
			j--;
		}
		if(j >= 0 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[j][y]) == "WHITE")
		{
			if(getPiece(board.squares[j][y]) == "KING")
			{
				return true;
			}
		}
		else if(j >= 0 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[j][y]) == "BLACK")
		{
			if(getPiece(board.squares[j][y]) == "KING")
			{
				return true;
			}
		}
		
		
		// for right
		int k = y + 1;
		while(k < 8 && getPiece(board.squares[x][k]) == "BLANK" )
		{
			k++;
		}
		if(k < 8 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[x][k]) == "WHITE" )
		{
			if(getPiece(board.squares[x][k]) == "KING")
			{
				return true;
			}
		}
		else if(k < 8 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[x][k]) == "BLACK" )
		{
			if(getPiece(board.squares[x][k]) == "KING")
			{
				return true;
			}
		}
		// for left
		int l = y - 1;
		while(l >= 0 && getPiece(board.squares[x][l]) == "BLANK")
		{
			l--;
		}
		if(l >= 0 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[x][l]) == "WHITE" )
		{
			if(getPiece(board.squares[x][l]) == "KING")
			{
				return true;
			}
		}
		else if(l >= 0 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[x][l]) == "BLACK" )
		{
			if(getPiece(board.squares[x][l]) == "KING")
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This method will check the possible check mate position
	 * @param Ix Imaginary x location of the King
	 * @param Iy Imaginary y location of the King
	 * @param color color of the King(White, Black)
	 * @return true if the Rook is attacking position to the Imaginary x y location of the King
	 */
	public static boolean ImagineryCheckMate(int Ix, int Iy, String color)
	{
		boolean result = false;
		if(color == "WHITE")
		{
			for(int x = 0 ; x < 8 ; x++)
			{
				for(int y = 0; y  < 8 ; y++)
				{
					if(getPiece(board.squares[x][y]) == "ROOK"  && checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						//for down
						int i = x + 1;
						while(i < 8 && i <= Ix && getPiece(board.squares[i][y]) == "BLANK" )
						{
							if(i == Ix && y == Iy)
							{
								result = true;
							}
							i++;
						}
						
						// for up 
						int j = x - 1;
						while( j >= 0 && j >= Ix && getPiece(board.squares[j][y]) == "BLANK")
						{
							if(j == Ix && y == Iy)
							{
								result = true;
							}
							j--;
						}
						// for right
						int k = y + 1;
						while(k < 8 && k <= Iy && getPiece(board.squares[x][k]) == "BLANK" )
						{
							if(x == Ix && k == Iy)
							{
								result = true;
							}
							k++;
						}
						// for left
						int l = y - 1;
						while(l >= 0 && l >= Iy && getPiece(board.squares[x][l]) == "BLANK")
						{
							if(x == Ix && l == Iy)
							{
								result = true;
							}
							l--;
						}
						
					}
				}
			}
		}
		else if(color == "BLACK")
		{
			for(int x = 0 ; x < 8 ; x++)
			{
				for(int y = 0; y  < 8 ; y++)
				{
					if(getPiece(board.squares[x][y]) == "ROOK"  && checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						int i = x + 1;
						while(i < 8 && i < Ix && getPiece(board.squares[i][y]) == "BLANK" )
						{
							if(i == Ix && y == Iy)
							{
								result = true;
							}
							i++;
						}
						
						// for up 
						int j = x - 1;
						while( j >= 0 && j > Ix && getPiece(board.squares[j][y]) == "BLANK")
						{
							if(j - 1 == Ix && y == Iy)
							{
								result = true;
							}
							j--;
						}
						
						
						
						// for right
						int k = y + 1;
						while(k < 8 && k < Iy && getPiece(board.squares[x][k]) == "BLANK" )
						{
							if(x == Ix && k + 1== Iy)
							{
								result = true;
							}
							k++;
						}
						// for left
						int l = y - 1;
						while(l >= 0 && l > Iy && getPiece(board.squares[x][l]) == "BLANK")
						{
							if(x == Ix && l - 1 == Iy)
							{
								result = true;
							}
							l--;
						}
					}
				}
			}
		}
		return result;
	}
	
	public static boolean checkMoveFromBlocking(int x, int y, int toX, int toY)
	{
		boolean result = false;
		//for down	
		int i = x + 1;
		while(i < 8 && getPiece(board.squares[i][y]) == "BLANK" )
		{
			if(i == toX && y == toY)
			{
				result = true;
			}
			i++;
		}
		
		// for up 
		int j = x - 1;
		while( j >= 0 && getPiece(board.squares[j][y]) == "BLANK")
		{
			if(j == toX && y == toY)
			{
				result = true;
			}
			j--;
		}
		
		// for right
		int k = y + 1;
		while(k < 8 && getPiece(board.squares[x][k]) == "BLANK" )
		{
			if(x == toX && k == toY)
			{
				result = true;
			}
			k++;
		}
		
		// for left
		int l = y - 1;
		while(l >= 0 && getPiece(board.squares[x][l]) == "BLANK")
		{
			if(x == toX && l == toY)
			{
				result = true;
			}
			l--;
		}
		
		return result;
	}
	
}