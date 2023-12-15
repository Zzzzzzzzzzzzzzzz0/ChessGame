import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Bishop class "Have-A" relationship with Board class. And "Is-A" relationship with Piece class"
 * This class represent the Black and White Bishop piece in ChessGame.
 */
public class Bishop extends Piece
{
	static int attackX = 0;
	static int attackY = 0;

	//constructor of the Bishop class
	public Bishop(Board board,int x, int y, ImageIcon image)
	{
		super(board,x, y, image);
	}
	

	//start placing the Bishop 
    public void startBishop()
    {
		board.squares[xLocation][yLocation].setIcon(image);
    }
 

    /**
     * This method is to show the valid moves for the Bishop piece
     * @param x current x Location of the Bishop piece
     * @param y current y location of the Bishop piece
     */
	public static void validateMove(int x, int y)
	{

		// for up left
		int i = clickedX - 1;
		int j = clickedY - 1;
		while(i >= 0 && j >= 0 && getPiece(board.squares[i][j]) == "BLANK" )
		{
			board.squares[i][j].setBackground(Color.green);
			i--;
			j--;
		}
		if(i >= 0 && j >= 0 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
		{
			board.squares[i][j].setBackground(Color.red);
		}
		else if(i >= 0 && j >= 0 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
		{
			board.squares[i][j].setBackground(Color.red);
		}

		// for up right
		int k = clickedX - 1;
		int l = clickedY + 1;
		while(k >= 0 && l < 8 && getPiece(board.squares[k][l]) == "BLANK" )
		{
			board.squares[k][l].setBackground(Color.green);
			k--;
			l++;
		}
		if(k >= 0 && l < 8 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[k][l]) == "WHITE")
		{
			board.squares[k][l].setBackground(Color.red);
		}
		else if(k >= 0 && l < 8 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[k][l]) == "BLACK")
		{
			board.squares[k][l].setBackground(Color.red);
		}

		// for down right
		int m = clickedX + 1;
		int n = clickedY + 1;
		while(m < 8  && n < 8 && getPiece(board.squares[m][n]) == "BLANK" )
		{
			board.squares[m][n].setBackground(Color.green);
			m++;
			n++;
		}
		if(m < 8  && n < 8 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[m][n]) == "WHITE")
		{
			board.squares[m][n].setBackground(Color.red);
		}
		else if(m < 8  && n < 8 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[m][n]) == "BLACK")
		{
			board.squares[m][n].setBackground(Color.red);
		}

		// for down left
		int o = clickedX + 1;
		int p = clickedY - 1;
		while(o < 8  && p >= 0 && getPiece(board.squares[o][p]) == "BLANK" )
		{
			board.squares[o][p].setBackground(Color.green);
			o++;
			p--;
		}
		if(o < 8  && p >= 0 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[o][p]) == "WHITE")
		{
			board.squares[o][p].setBackground(Color.red);
		}
		else if(o < 8  && p >= 0 && checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[o][p]) == "BLACK")
		{
			board.squares[o][p].setBackground(Color.red);
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
		boolean result = false;

		// for up left
		int i = x - 1;
		int j = y - 1;
		while(i >= 0 && j >= 0 && getPiece(board.squares[i][j]) == "BLANK" )
		{
			i--;
			j--;
		}
		if(i >= 0 && j >= 0 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE" && getPiece(board.squares[i][j]) == "KING")
		{
			result = true;
		}
		else if(i >= 0 && j >= 0 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK" && getPiece(board.squares[i][j]) == "KING")
		{
			result = true;
		}
		
		// for up right
		int k = x - 1;
		int l = y + 1;
		while(k >= 0 && l < 8 && getPiece(board.squares[k][l]) == "BLANK" )
		{
			k--;
			l++;
		}
		if(k >= 0 && l < 8 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[k][l]) == "WHITE" && getPiece(board.squares[k][l]) == "KING")
		{
			result = true;
		}
		else if(k >= 0 && l < 8 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[k][l]) == "BLACK" && getPiece(board.squares[k][l]) == "KING")
		{
			result = true;
		}

		// for down right
		int m = x + 1;
		int n = y + 1;
		while(m < 8  && n < 8 && getPiece(board.squares[m][n]) == "BLANK" )
		{
			m++;
			n++;
		}
		if(m < 8  && n < 8 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[m][n]) == "WHITE" && getPiece(board.squares[m][n]) == "KING")
		{
			result = true;
		}
		else if(m < 8  && n < 8 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[m][n]) == "BLACK" && getPiece(board.squares[m][n]) == "KING")
		{
			result = true;
		}

		// for down left
		int o = x + 1;
		int p = y - 1;
		while(o < 8  && p >= 0 && getPiece(board.squares[o][p]) == "BLANK" )
		{
			o++;
			p--;
		}
		if(o < 8  && p >= 0 && checkBlackOrWhite(board.squares[x][y]) == "BLACK" && checkBlackOrWhite(board.squares[o][p]) == "WHITE" && getPiece(board.squares[o][p]) == "KING")
		{
			result = true;
		}
		else if(o < 8  && p >= 0 && checkBlackOrWhite(board.squares[x][y]) == "WHITE" && checkBlackOrWhite(board.squares[o][p]) == "BLACK" && getPiece(board.squares[o][p]) == "KING")
		{
			result = true;
		}
		
		return result;
	}

	/**
	 * This method will check the possible check mate position
	 * @param Ix Imaginary x location of the King
	 * @param Iy Imaginary y location of the King
	 * @param color color of the King(White, Black)
	 * @return true if the bishop is attacking position to the Imaginary x y location of the King
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
					if(getPiece(board.squares[x][y]) == "BISHOP"  && checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						//for down
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
							return true;
						}

						// for up right
						int k = x - 1;
						int l = y + 1;
						while(k >= 0 && k > Ix && l < 8 && l < Iy && getPiece(board.squares[k][l]) == "BLANK" )
						{
							k--;
							l++;
						}
						if( k == Ix && l == Iy)
						{
							return true;
						}

						// for down right
						int m = x + 1;
						int n = y + 1;
						while(m < 8 && m < Ix && n < 8 && n < Iy && getPiece(board.squares[m][n]) == "BLANK" )
						{
							m++;
							n++;
						}
						if(m == Ix && n == Iy)
						{
							return true;
						}

						// for down left
						int o = x + 1;
						int p = y - 1;
						while(o < 8 && o < Ix && p >= 0 && p > Iy && getPiece(board.squares[o][p]) == "BLANK" )
						{
							System.out.println(o + " , " + p);
							o++;
							p--;
						}
						if(o == Ix && p ==Iy)
						{
							return true;
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
					if(getPiece(board.squares[x][y]) == "BISHOP"  && checkBlackOrWhite(board.squares[x][y]) == "WHITE")
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
							return true;
						}

						// for up right
						int k = x - 1;
						int l = y + 1;
						while(k >= 0 && k > Ix && l < 8 && l < Iy && getPiece(board.squares[k][l]) == "BLANK" )
						{
							k--;
							l++;
						}
						if( k == Ix && l == Iy)
						{
							return true;
						}

						// for down right
						int m = x + 1;
						int n = y + 1;
						while(m < 8 && m < Ix && n < 8 && n < Iy && getPiece(board.squares[m][n]) == "BLANK" )
						{
							m++;
							n++;
						}
						if(m == Ix && n == Iy)
						{
							return true;
						}

						// for down left
						int o = x + 1;
						int p = y - 1;
						while(o < 8 && o < Ix && p >= 0 && p > Iy && getPiece(board.squares[o][p]) == "BLANK" )
						{ 
							o++;
							p--;
						}
						if(o == Ix && p ==Iy)
						{
							return true;
						}
					}
				}
			}
		}
		return result;
	}
	
	
}
