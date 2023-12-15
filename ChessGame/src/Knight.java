import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Knight class "Have-A" relationship with Board class. And "Is-A" relationship with Piece class"
 * This class represent the Black and White Knight piece in ChessGame.
 */
public class Knight extends Piece
{
	
	//constructor of the Knight class
	public Knight(Board board,int x, int y, Icon image)
	{
		super(board, x, y, image);
	}

	//start placing the Knight position
	public void startKnight()
	{
		board.squares[xLocation][yLocation].setIcon(image);
	}

	/**
	 * This method is to show the valid moves for the Knight piece
     * @param x current x Location of the Knight piece
     * @param y current y location of the Knight piece
     */
	public static void validateMove(int x, int y)
	{
		for(int i = 0 ; i< 8; i++)
		{
			for(int j = 0 ; j < 8; j++)
			{
				if((i == x + 2 && j == y+ 1) || (i == x + 2 && j == y - 1))
				{
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "BLACK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "WHITE")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}					
				}
				else if((i == x - 2 && j == y+ 1) || (i == x - 2 && j == y - 1))
				{ 
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "BLACK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "WHITE")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}					
				}
				else if((i == x +1 && j == y + 2) || (i == x - 1 && j == y + 2))
				{
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "BLACK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "WHITE")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}					
				}
				else if((i == x +1 && j == y - 2) || (i == x - 1 && j == y - 2))
				{

					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "BLACK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[i][j]) != "WHITE")
						{
							board.squares[i][j].setBackground(Color.green);
						}
					}
				}
			}
		}
	}

	/**
	 * This method will check if the Knight is in attacking position to the King
	 * @param x x Location of the Knight
	 * @param y y Location of the Knight
	 * @return true if the Bishop is in attacking position to the King Otherwise false
	 */
	public static boolean checkAttackForKing(int x, int y)
	{
		for(int i = 0 ; i< 8; i++)
		{
			for(int j = 0 ; j < 8; j++)
			{
				if((i == x + 2 && j == y+ 1) || (i == x + 2 && j == y - 1))
				{
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
						
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
					}					
				}
				else if((i == x - 2 && j == y+ 1) || (i == x - 2 && j == y - 1))
				{ 
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
					}					
				}
				else if((i == x +1 && j == y + 2) || (i == x - 1 && j == y + 2))
				{
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
					}					
				}
				else if((i == x +1 && j == y - 2) || (i == x - 1 && j == y - 2))
				{
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
					}
					else if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(checkBlackOrWhite(board.squares[i][j]) != "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							if(getPiece(board.squares[i][j]) == "KING")
							{
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * This method will check the possible check mate position
	 * @param Ix Imaginary x location of the King
	 * @param Iy Imaginary y location of the King
	 * @param color color of the King(White, Black)
	 * @return true if the Knight is attacking position to the Imaginary x y location of the King
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
					if(getPiece(board.squares[x][y]) == "KNIGHT"  && checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{

						if((Ix == x + 2 && Iy == y+ 1) || (Ix == x + 2 && Iy == y - 1))
						{
							result = true;
						}
						else if((Ix == x - 2 && Iy == y+ 1) || (Ix == x - 2 && Iy == y - 1))
						{ 
							result = true;
						}
						else if((Ix == x +1 && Iy == y + 2) || (Ix == x - 1 && Iy == y + 2))
						{
							result = true;
						}
						else if((Ix == x +1 && Iy == y - 2) || (Ix == x - 1 && Iy == y - 2))
						{
							result = true;
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
					if(getPiece(board.squares[x][y]) == "KNIGHT"  && checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if((Ix == x + 2 && Iy == y+ 1) || (Ix == x + 2 && Iy == y - 1))
						{
							result = true;
						}
						else if((Ix == x - 2 && Iy == y+ 1) || (Ix == x - 2 && Iy == y - 1))
						{ 
							result = true;
						}
						else if((Ix == x +1 && Iy == y + 2) || (Ix == x - 1 && Iy == y + 2))
						{
							result = true;
						}
						else if((Ix == x +1 && Iy == y - 2) || (Ix == x - 1 && Iy == y - 2))
						{
							result = true;
						}
					}
				}
			}
		}
		return result;
	}
}
