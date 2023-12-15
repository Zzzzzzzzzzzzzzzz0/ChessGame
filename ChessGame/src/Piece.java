import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import icons.Icons;

/**
 * Piece class "Have-A" relationship with Board class. And "Is-A" relationship with King,Queen,Knight,Bishop,Rook and pawn class"
 * This class represent the Black and White pieces in ChessGame.
 */
public abstract class Piece extends JButton
{
	public int xLocation;
	public int yLocation;
	public Icon image;
	static String piececolor = "";
	static public String pieceName;
	static Board board = null;
	public static int clickedX;
	public static int clickedY; 
	public static Icon oldIcon;
	public static Icon forMoveBack = null;
	public static Icon attackedForMoveBack = null;
	public static boolean eliminatedKing = false;
	static String playerWin = null;
	//constructor of the class
	public Piece()
	{
		
	}
	public Piece(Board board, int x, int y, Icon image) {
		xLocation = x;
		yLocation = y;
		this.board = board;
		this.image = image;
	}
	
	/**
	 * This method will take the x y location and icon image 
	 * @param x x location of the clicked piece
	 * @param y y location of the clicked piece
	 */
	public static void getClickedPiece(int x, int y)
	{
		clickedX = x;
		clickedY = y;
		oldIcon = board.squares[x][y].getIcon();
		
	}

	/**
	 * This method will check the clicked piece is white or black or blank
	 * @param button button from the check board
	 * @return WHITE if the clicked piece is white, BLACK if the clicked piece is black and BLANK if there is nothing
	 */
	public static String checkBlackOrWhite(JButton button)	
	{
		String iconName = "" + button.getIcon();
		if(iconName.contains("white"))
		{
			piececolor = "WHITE";
		}
		else if(iconName.contains("black"))
		{
			piececolor = "BLACK";
		}
		else {
			piececolor = "BLANK";
		}
		return piececolor;
	}
	
	/**
	 * getting x location of the piece
	 */
	public int getX()
	{
		return xLocation;
	}
	
	/**
	 * getting y location of the piece
	 */
	public int getY()
	{
		return yLocation;
	}

	/**
	 * This method will show if the clicked piece is pawn,rook,bishop,knight,queen, and king
	 * @param button clicked button from the chess board
	 * @return return the name of the chess piece
	 */
	public static String getPiece(JButton button)
	{
		String iconName = "" + button.getIcon();
		if(iconName.contains("Pawn"))
		{
			pieceName = "PAWN";
		}
		else if(iconName.contains("Rook"))
		{
			pieceName = "ROOK";
		}
		else if(iconName.contains("Bishop"))
		{
			pieceName = "BISHOP";
		}
		else if(iconName.contains("Knight"))
		{
			pieceName = "KNIGHT";
		}
		else if(iconName.contains("King"))
		{
			pieceName = "KING";
		}
		else if(iconName.contains("Queen"))
		{
			pieceName = "QUEEN";
		}
		else if(iconName.contains(""))
		{
			pieceName = "BLANK";
		}
		
		return pieceName;
	}
	
	/**
	 * This method is for attacking the check piece
	 * @param x getting x location of the chess piece to attack
	 * @param y getting y location of the chess piece to attack
	 */
	public static void attack(int x, int y)
	{
		attackedForMoveBack = board.squares[x][y].getIcon();
		board.squares[clickedX][clickedY].setIcon(null);
		if(getPiece(board.squares[x][y])== "KING")
		{
			eliminatedKing = true;
			if(checkBlackOrWhite(board.squares[x][y])== "WHITE")
			{
				playerWin = Board.player2Name;
			}
			else if(checkBlackOrWhite(board.squares[x][y])== "BLACK")
			{
				playerWin = Board.player1Name;
			}
		}
		board.squares[x][y].setIcon(oldIcon);
		forMoveBack = oldIcon;
		oldIcon = null;
		board.refresh();
	}

	/**
	 * This method will return if the player click the same piece or not
	 * @param x x location of the place where the player clicked
	 * @param y y location of the place where the player clicked
	 * @return true if the player clicked the same button and otherwise not
	 */
	public static boolean isTheSameButton(int x, int y)
	{
		if(clickedX == x && clickedY == y)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	/**
	 * This method is to move the chess piece
	 * @param x x location of the place where the chess piece moved
	 * @param y y location of the place where the chess piece moved
	 */
	public static void moveTo(int x,int y)
	{
			attackedForMoveBack = board.squares[x][y].getIcon();
			board.squares[clickedX][clickedY].setIcon(null);
			if(getPiece(board.squares[x][y])== "KING")
			{
				eliminatedKing = true;
				if(checkBlackOrWhite(board.squares[x][y])== "WHITE")
				{
					playerWin = Board.player2Name;
				}
				else if(checkBlackOrWhite(board.squares[x][y])== "BLACK")
				{
					playerWin = Board.player1Name;
				}
			}
			else
			{
				eliminatedKing = false;
			}
			board.squares[x][y].setIcon(oldIcon);
			forMoveBack = oldIcon;
			oldIcon = null;
			board.refresh();
	}
	
	/**
	 * This method is for chess piece to move back when the king is in under attack
	 * @param x current x location of the chess piece
	 * @param y current y location of the chess piece
	 * @param oldX previous x location of the chess piece
	 * @param oldY previous y location of the chess piece
	 */
	public static void moveBack(int x, int y,int oldX, int oldY)
	{
		board.squares[x][y].setIcon(null);
		board.squares[oldX][oldY].setIcon(forMoveBack);
		eliminatedKing = false;
	}
	/**
	 * This method is for chess piece to move back when the king is in under attack while the piece is make a move for attacking
	 * @param x current x location of the chess piece
	 * @param y current y location of the chess piece
	 * @param oldX previous x location of the chess piece
	 * @param oldY previous y location of the chess piece
	 */
	public static void moveBackforAttacking(int x, int y,int oldX, int oldY)
	{
		board.squares[x][y].setIcon(attackedForMoveBack);
		board.squares[oldX][oldY].setIcon(forMoveBack);
	}
	

    /**
     * getting x location of the chess piece
     * @param color color of the chess piece
     * @param piece type of the chess piece
     * @return x location of the chess piece
     */
	public static int getXLocation(String color,String piece)
	{
		int x = 0;
		for(int i = 0; i < 8 ; i++) 
		{
			for(int j = 0; j < 8 ; j++)
			{
				if(getPiece(board.squares[i][j]) == piece && checkBlackOrWhite(board.squares[i][j]) == color)
				{
					x = i;
				}
			}
		}
		return x;
	}
	
	/**
     * getting y location of the chess piece
     * @param color color of the chess piece
     * @param piece type of the chess piece
     * @return y location of the chess piece
     */
	public static int getYLocation(String color,String piece)
	{
		int y = 0;
		for(int i = 0; i < 8 ; i++) 
		{
			for(int j = 0; j < 8 ; j++)
			{
				if(getPiece(board.squares[i][j]) == piece && checkBlackOrWhite(board.squares[i][j]) == color)
				{
					y = j;
				}
			}
		}
		return y;
	}
	
}
