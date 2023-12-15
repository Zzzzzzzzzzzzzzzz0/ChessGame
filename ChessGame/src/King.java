import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * King class "Have-A" relationship with Board class. And "Is-A" relationship with Piece class"
 * This class represent the Black and White King piece in ChessGame.
 */
public class King extends Piece
{

	//constructor of the class
	public King(Board board,int x, int y, Icon image)
	{
		super(board,x, y, image);
	}

	//start placing the King position
	public void  startKing()
	{
		board.squares[xLocation][yLocation].setIcon(image);
	}

    /**
	 * This method is to show the valid moves for the King piece
     * @param x current x Location of the King piece
     * @param y current y location of the King piece
	 */
	public static void validateMoveForKing(int x, int y)
	{
		if(x != 0 && y != 0)
		{
			for(int i = x - 1; i <= x +1 && i < 8; i++)
			{
				for(int j = y - 1; j <= y + 1 && j < 8; j++)
				{
					if(i != clickedX || j != clickedY)
					{
						if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
					}
				}
			}
		}
		else if( x == 0 && y ==0)
		{
			for(int i = x; i <= x +1 && i < 8; i++)
			{
				for(int j = y; j <= y + 1 && j < 8; j++)
				{
					if(i != clickedX || j != clickedY)
					{
						if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
					}
				}
			}
		}
		else if( x == 0)
		{
			for(int i = x; i <= x +1 && i < 8; i++)
			{
				for(int j = y - 1; j <= y + 1 && j < 8; j++)
				{
					if(i != clickedX || j != clickedY)
					{
						if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
					}
				}
			}
		}
		else if( y == 0)
		{System.out.println( "y == 0 is running");
			for(int i = x - 1; i <= x +1 && i < 8; i++)
			{
				for(int j = y; j <= y + 1 && j < 8; j++)
				{
					if(i != clickedX || j != clickedY)
					{
						if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLANK")
						{
							board.squares[i][j].setBackground(Color.green);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "WHITE")
						{
							board.squares[i][j].setBackground(Color.red);
						}
						else if(checkBlackOrWhite(board.squares[clickedX][clickedY]) == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "BLACK")
						{
							board.squares[i][j].setBackground(Color.red);
						}
					}
				}
			}
		}

	}
	/**
	 * This method is to check if the king is in check position or not
	 * @param color The king color we want to check
	 * @return true if the king is in check position and otherwise false
	 */
	public static boolean isCheck(String color)
	{
		if(color == "BLACK")
		{
			for(int x = 0; x < 8; x++)
			{
				for(int y = 0; y < 8 ; y++)
				{
					if(checkBlackOrWhite(board.squares[x][y]) == "WHITE")
					{
						if(getPiece(board.squares[x][y]) == "PAWN" && Pawn.checkAttackForKing(x,y) == true)
						{
							return true;
						}
						else if(getPiece(board.squares[x][y]) == "ROOK" && Rook.checkAttackForKing(x,y) == true)
						{
							return true;
						}
						else if(getPiece(board.squares[x][y]) == "KNIGHT" && Knight.checkAttackForKing(x, y) == true)
						{
							return true;
						}
						else if(getPiece(board.squares[x][y]) == "BISHOP" && Bishop.checkAttackForKing(x, y) == true)
						{
							return true;
						}
						else if(getPiece(board.squares[x][y]) == "QUEEN" && Queen.checkAttackforKing(x,y) == true)
						{
							return true;
						}
					}
				}
			}
		}
		else if(color == "WHITE")
		{
			for(int x = 0; x < 8; x++)
			{
				for(int y = 0; y < 8 ; y++)
				{
					//if this is a white turn, opponent pieces should be black
					if(checkBlackOrWhite(board.squares[x][y]) == "BLACK")
					{
						if(getPiece(board.squares[x][y]) == "PAWN" && Pawn.checkAttackForKing(x,y) == true)
						{
							return true;
						}
					    if(getPiece(board.squares[x][y]) == "ROOK" && Rook.checkAttackForKing(x,y) == true)
						{
							return true;
						}
						else if(getPiece(board.squares[x][y]) == "KNIGHT" && Knight.checkAttackForKing(x, y) == true)
						{
							return true;
						}
						else if(getPiece(board.squares[x][y]) == "BISHOP" && Bishop.checkAttackForKing(x, y) == true)
						{
							return true;
						}
						else if(getPiece(board.squares[x][y]) == "QUEEN" && Queen.checkAttackforKing(x,y) == true)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * This method is to check King is in possible checkMate position in or not
	 * @param color the king color we want to check
	 * @return true if the king is in chess mate position otherwise false
	 */
	public static boolean isCheckMate(String color)
	{
		int x = 0,y = 0;
		boolean result = false;
		if(board.isBlackTurn && isCheck("BLACK") && color =="BLACK")
		{
			x = getXLocation("BLACK","KING");
			y = getYLocation("BLACK","KING");
		}
		else if(!board.isBlackTurn && isCheck("WHITE") && color == "WHITE")
		{
			x = getXLocation("WHITE","KING");
			y = getYLocation("WHITE","KING");
		}
		if(x != 0 && y != 0)
		{
			for(int i = x - 1; i <= x +1 && i < 8; i++)
			{
				for(int j = y - 1; j <= y + 1 && j < 8; j++)
				{
					if(i != x || j != y)
					{
						if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) != "BLACK")
						{
							if(Rook.ImagineryCheckMate(i, j, "BLACK"))
							{
								if(Bishop.ImagineryCheckMate(x, y, "BLACK"))
								{
									if(Knight.ImagineryCheckMate(i,j, "BLACK"))
									{
										if(Queen.ImagineryCheckMate(i, j, "BLACK"))
										{
											result = true;
										}
									}
								}
							}
							else
							{
								return false;
							}
						}
						else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) != "WHITE")
						{
							if(Rook.ImagineryCheckMate(i, j, "WHITE") || Bishop.ImagineryCheckMate(i, j, "WHITE")  || Knight.ImagineryCheckMate(i, j, "WHITE")|| Queen.ImagineryCheckMate(i, j, "WHITE"))
							{
								result = true;
							}
							else
							{
								return false;
							}
						}
						else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "WHITE") 
						{
							result = true;
						}
						else if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLACK") 
						{
							result = true;
						}
					}
				}
			}
		}
		else if( x == 0 && y ==0)
		{

				for(int i = x; i <= x +1 && i < 8; i++)
				{
					for(int j = y; j <= y + 1 && j < 8; j++)
					{
						if(i != x || j != y)
						{
							if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) != "BLACK")
							{
								if(Rook.ImagineryCheckMate(i, j, "BLACK")  || Bishop.ImagineryCheckMate(i, j, "BLACK") || Knight.ImagineryCheckMate(i, j, "BLACK")|| Queen.ImagineryCheckMate(i, j, "BLACK"))
								{
									result = true;
								}
								else
								{
									return false;
								}
							}
							else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) != "WHITE")
							{
								if(Rook.ImagineryCheckMate(i, j, "WHITE")  || Bishop.ImagineryCheckMate(i, j, "WHITE") || Knight.ImagineryCheckMate(i, j, "WHITE")|| Queen.ImagineryCheckMate(i, j, "WHITE"))
								{
									result = true;
								}
								else
								{
									return false;
								}
							}
							else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "WHITE") 
							{
								result = true;
							}
							else if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLACK") 
							{
								result = true;
							}
						}
					}
				}
			}
			else if( x == 0)
			{
				for(int i = x; i <= x +1 && i < 8; i++)
				{
					for(int j = y - 1; j <= y + 1 && j < 8; j++)
					{
						if(i != x || j != y)
						{
							if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) != "BLACK")
							{
								if(Rook.ImagineryCheckMate(i, j, "BLACK") || Bishop.ImagineryCheckMate(i, j, "BLACK") || Knight.ImagineryCheckMate(i, j, "BLACK")|| Queen.ImagineryCheckMate(i, j, "BLACK"))
								{
									result = true;
								}
								else
								{
									return false;
								}
							}
							else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) != "WHITE")
							{
								if(Rook.ImagineryCheckMate(i, j, "WHITE") || Bishop.ImagineryCheckMate(i, j, "WHITE") || Knight.ImagineryCheckMate(i, j, "WHITE")|| Queen.ImagineryCheckMate(i, j, "WHITE"))
								{
									result = true;
									System.out.println("WHITE" + result);
								}
								else
								{
									return false;
								}
							}
							else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "WHITE") 
							{
								result = true;
							}
							else if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLACK") 
							{
								result = true;
							}
						}
					}
				}
			}
			else if( y == 0)
			{System.out.println( "y == 0 is running");
				for(int i = x - 1; i <= x +1 && i < 8; i++)
				{
					for(int j = y; j <= y + 1 && j < 8; j++)
					{
						if(i != x || j != y)
						{
							if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) != "BLACK")
							{
								if(Rook.ImagineryCheckMate(i, j, "BLACK")  || Bishop.ImagineryCheckMate(i, j, "BLACK") || Knight.ImagineryCheckMate(i, j, "BLACK")|| Queen.ImagineryCheckMate(i, j, "BLACK"))
								{

									result = true;
								}
								else
								{
									return false;
								}
							}
							else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) != "WHITE")
							{
								if(Rook.ImagineryCheckMate(i, j, "WHITE")  || Bishop.ImagineryCheckMate(i, j, "BLACK") || Knight.ImagineryCheckMate(i, j, "WHITE")  || Queen.ImagineryCheckMate(i, j, "WHITE"))
								{
									result = true;
								}
								else
								{
									return false;
								}
							}
							else if(color == "WHITE" && checkBlackOrWhite(board.squares[i][j]) == "WHITE") 
							{
								result = true;
							}
							else if(color == "BLACK" && checkBlackOrWhite(board.squares[i][j]) == "BLACK") 
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
