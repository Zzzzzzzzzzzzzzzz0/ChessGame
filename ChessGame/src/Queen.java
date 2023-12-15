import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Queen class "Have-A" relationship with Board class. And "Is-A" relationship with Piece class"
 * This class represent the Black and White Queen piece in ChessGame.
 */
public class Queen extends Piece
{
	//constructor of the class
	public Queen(Board board,int x, int y, Icon image)
	{
		super(board,x, y, image);	
	}

	/**
	 * start placing the queen position
	 */
	public void startQueen()
	{
		board.squares[xLocation][yLocation].setIcon(image);
	}

    /**
	 * This method is to show the valid moves for the Queen piece
     * @param x current x Location of the Queen piece
     * @param y current y location of the Queen piece
	 */
	public static void validateMove(int x, int y)
	{
		Bishop.validateMove(x, y);
		Rook.validateMove(x, y);
	}

	/**
	 * This method will check if the Queen is in attacking position to the King
	 * @param x x Location of the Queen
	 * @param y y Location of the Queen
	 * @return true if the Queen is in attacking position to the King Otherwise false
	 */
	public static boolean checkAttackforKing(int x, int y)
	{
		if(Rook.checkAttackForKing(x,y) || Bishop.checkAttackForKing(x,y))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This method will check the possible check mate position
	 * @param Ix Imaginary x location of the King
	 * @param Iy Imaginary y location of the King
	 * @param color color of the King(White, Black)
	 * @return true if the Queen is attacking position to the Imaginary x y location of the King
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
					if(getPiece(board.squares[x][y]) == "QUEEN"  && checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						// for up left
						int i = x - 1;
						int j = y - 1;
						while(i >= 0 && i > Ix && j >= 0 && j > Iy && getPiece(board.squares[i][j]) == "BLANK" )
						{
							i--;
							j--;
						}
						if( i == Ix && j == Iy)
						{
							result = true;
						}

						// for up 
						int q = x - 1;
						while( q >= 0 && q >= Ix && getPiece(board.squares[q][y]) == "BLANK")
						{
							if(q == Ix && y == Iy)
							{
								result = true;
							}
							q--;
						}
						
						// for right
						int r = y + 1;
						while(r < 8 && r <= Iy && getPiece(board.squares[x][r]) == "BLANK" )
						{
							if(x == Ix && r == Iy)
							{
								result = true;
							}
							r++;
						}
						// for left
						int s = y - 1;
						while(s >= 0 && s >= Iy && getPiece(board.squares[x][s]) == "BLANK")
						{
							if(x == Ix && s == Iy)
							{
								result = true;
							}
							s--;
						}
						
						int k = x - 1;
						int l = y + 1;
						// for up right
						while(k >= 0 && k > Ix && l < 8 && l < Iy && getPiece(board.squares[k][l]) == "BLANK" )
						{
							k--;
							l++;
						}
						if( k == Ix && l == Iy)
						{
							result = true;
						}
						
						int m = x + 1;
						int n = y + 1;
						// for down right

						while(m < 8 && m < Ix && n < 8 && n < Iy && getPiece(board.squares[m][n]) == "BLANK" )
						{
							m++;
							n++;
						}
						if(m == Ix && n == Iy)
						{
							result= true;
						}
						
						int o = x + 1;
						int p = y - 1;
						// for down left
						while(o < 8 && o < Ix && p >= 0 && p > Iy && getPiece(board.squares[o][p]) == "BLANK" )
						{
							System.out.println(o + " , " + p);
							o++;
							p--;
						}
						if(o == Ix && p ==Iy)
						{
							result = true;
						}
						//for down
						int t = x + 1;
						while(t < 8 && t <= Ix && getPiece(board.squares[t][y]) == "BLANK" )
						{
							if(t == Ix && y == Iy)
							{
								result = true;
							}
							t++;
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
					if(getPiece(board.squares[x][y]) == "QUEEN"  && checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						// for up left
						int i = x - 1;
						int j = y - 1;
						while(i >= 0 && i > Ix && j >= 0 && j > Iy && (getPiece(board.squares[i][j]) == "BLANK" || getPiece(board.squares[i][j]) == "KING"))
						{
							i--;
							j--;
						}
						if( i == Ix && j == Iy)
						{
							result = true;
						}

						// for up right
						int k = x - 1;
						int l = y + 1;
						while(k >= 0 && k > Ix && l < 8 && l < Iy  && (getPiece(board.squares[k][l]) == "BLANK" || getPiece(board.squares[k][l]) == "KING"))
						{
							k--;
							l++;
						}
						if( k == Ix && l == Iy)
						{
							result = true;
						}
						
						// for up 
						int q = x - 1;
						while( q >= 0 && q >= Ix  && (getPiece(board.squares[q][y]) == "BLANK" || getPiece(board.squares[q][y]) == "KING"))
						{
							if(q == Ix && y == Iy)
							{
								result = true;
							}
							if(q != 0)
								q--;
						}
						
						// for right
						int r = y + 1;
						while(r < 8 && r <= Iy && (getPiece(board.squares[x][r]) == "BLANK" || getPiece(board.squares[x][r]) == "KING") )
						{
							if(x == Ix && r == Iy)
							{
								result = true;
							}
							r++;
						}
						// for left
						int s = y - 1;
						while(s >= 0 && s >= Iy && (getPiece(board.squares[x][s]) == "BLANK" || getPiece(board.squares[x][s]) == "KING"))
						{
							if(x == Ix && s == Iy)
							{
								result = true;
							}
							s--;
						}
						
						int m = x + 1;
						int n = y + 1;
						// for down right
						while(m < 8 && m < Ix && n < 8 && n < Iy  && (getPiece(board.squares[m][n]) == "BLANK" || getPiece(board.squares[m][n]) == "KING"))
						{
							m++;
							n++;
						}
						if(m == Ix && n == Iy)
						{
							result = true;
						}
						
						int o = x + 1;
						int p = y - 1;
						// for down left
						while(o < 8 && o < Ix && p >= 0 && p > Iy  && (getPiece(board.squares[o][p]) == "BLANK" || getPiece(board.squares[o][p]) == "KING") )
						{ 
							o++;
							p--;
						}
						if(o == Ix && p ==Iy)
						{
							result = true;
						}
						//for down
						int t = x + 1;
						while(t < 8 && t <= Ix  && (getPiece(board.squares[t][y]) == "BLANK" || getPiece(board.squares[t][y]) == "KING"))
						{
							if(t == Ix && y == Iy)
							{
								result = true;
							}
							t++;
						}
					}
				}
			}
		}
		return result;
	}

}
