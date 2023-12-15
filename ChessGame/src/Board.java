import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//This is the Board class and it has "IS-A" relationship with JFrame.
public class Board extends JFrame
{

	public JButton[][] squares;
	private final int numRows = 8;
	private final int numCols = 8;
	public String toChange;
	public boolean isBlackTurn = false;
	public int oldX;
	public int oldY;
	Stack<String> moveHistory = new Stack<>();
	JPanel notify;
	JPanel moveHistoryPanel;
	JTextArea field;
	JTextArea field1;
	JTextField playerName1;
	JTextField playerName3;
	static String player1Name = "Player 1";
	static String player2Name = "Player 2";
	static String userInputforPromotion;
	//constructor of the class
	public Board()
	{
		//This is for the game Panel
				JPanel gamePanel = new JPanel();
				
				//2D array for game buttons
				squares = new JButton[numRows][numCols];
				//using grid layout for the Game Board
				gamePanel.setLayout(new GridLayout(numRows,numCols));
				//creating button in 8*8 grids and changing color
				for (int x = 0; x < numRows; x++)
				{
					for (int y = 0; y < numCols; y++)
					{
						squares[x][y] = new JButton();
			                
						gamePanel.add(squares[x][y]);
						if((x + y) % 2 == 0)
						{
							squares[x][y].setBackground(new Color(228,158,119));
						}
						else
						{
							squares[x][y].setBackground(new Color(161,102,47));
						}
						
						//adding mouseActionListener to the button
						squares[x][y].addMouseListener(new mouseAction(this,x,y));
					}
				}
				
				moveHistoryPanel = new JPanel();
				moveHistoryPanel.setLayout(new BoxLayout(moveHistoryPanel, BoxLayout.PAGE_AXIS));
				// Set up labels for player names
				JLabel playerName1 = new JLabel(player1Name);
				playerName1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				playerName1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
				JLabel playerName2 = new JLabel(player2Name);
				playerName2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
				playerName2.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
				// Add player names to moveHistoryPanel
				moveHistoryPanel.add(playerName2);
				moveHistoryPanel.add(Box.createRigidArea(new java.awt.Dimension(0, 10)));
				// Create a JTextArea for move history and add it to a JScrollPane
				field = new JTextArea("This is for moveHistory");
				field.setRows(30); 
				JScrollPane scrollPane = new JScrollPane(field);
				moveHistoryPanel.add(scrollPane);
				field.setColumns(10); 
				field.setLineWrap(true); 
				field.setWrapStyleWord(true);
				field.setEditable(false);
				// Create a JTextArea for notifications
				field1 = new JTextArea("This is for notify");
				field1.setLineWrap(true); 
				field1.setWrapStyleWord(true);
				field1.setEditable(false);
				moveHistoryPanel.add(field1);
				moveHistoryPanel.add(Box.createRigidArea(new java.awt.Dimension(0, 10)));
				moveHistoryPanel.add(playerName1);
				setLayout(new BorderLayout());
				setTitle("Chess Game");
				add(gamePanel, BorderLayout.CENTER);
				add(moveHistoryPanel, BorderLayout.EAST);
				//add(notify, BorderLayout.WEST);
				setSize(1000,800);
		      	setLocationRelativeTo(null);
				setResizable(false);
				setVisible(true);
	}	
	
	/**
	 * This method is to refresh(cancel the green and red)  the board after the piece is moved
	 */
	public void refresh()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if((i + j) % 2 == 0)
				{
					squares[i][j].setBackground(new Color(228,158,119));
				}
				else
				{
					squares[i][j].setBackground(new Color(161,102,47));
				}
			}
		}
	}
		
		/**
		 * This method check the board has green square or not
		 * @return true if the board has green place and false if the board has not
		 */
	public boolean checkGreen()
	{
		for(int i = 0; i < 8 ; i++ )
		{
			for(int j = 0; j < 8; j++)
			{
				if(squares[i][j].getBackground() == Color.green)
					return true;
			}
		}
		return false;
	}

	/**
	 * This method check the board has red square or not
	 * @return true if the board has green place and false if the board has not
	 */
	public boolean checkRed()
	{
		for(int i = 0; i < 8 ; i++ )
		{
			for(int j = 0; j < 8; j++)
			{
				if(squares[i][j].getBackground() == Color.red)
					return true;
			}
		}
		return false;
	}
	    

    public static void messageBoxForPormotion()
    {
    	 do {
             String userInput = JOptionPane.showInputDialog("Enter promotion piece (Q, R, B, or K):");
             userInputforPromotion = userInput;
         } while (!isValidInput(userInputforPromotion));
     }

    public static boolean isValidInput(String input) {
        return input != null && (input.equalsIgnoreCase("Q") || input.equalsIgnoreCase("q") ||
       		 				  input.equalsIgnoreCase("R") || input.equalsIgnoreCase("r") ||
                                 input.equalsIgnoreCase("B") || input.equalsIgnoreCase("b") ||
                                 input.equalsIgnoreCase("K") || input.equalsIgnoreCase("k"));
    } 
    
	public class mouseAction implements MouseListener
	{	
			int x;
	    	int y;
	    	Board board;
	    	
	    	public mouseAction(Board board,int x, int y)
	    	{
	    		this.x = x;
	    		this.y = y;
	    		this.board = board;
	    	}
	    	
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JButton clickedButton = (JButton) e.getSource();
				
				// getting information from the Chess Piece based on White and Black
				//If the board has no green or red and when the player click the piece it will collect the data of the piece
				if(Piece.getPiece(clickedButton) != "BLANK" && !checkGreen() && !checkRed())
				{
					//This is for BlackTurn
					if((isBlackTurn && Piece.checkBlackOrWhite(clickedButton) == "BLACK"))
					{
						if( Piece.getPiece(clickedButton) == "PAWN")
						{ 
							Pawn.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "PAWN";
							Pawn.validateMove(x,y);		
						}		
						else if( Piece.getPiece(clickedButton) == "BISHOP")
						{ 
							Bishop.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "BISHOP";
							Bishop.validateMove(x,y);
						}
						else if( Piece.getPiece(clickedButton) == "KNIGHT")
						{ 
							Bishop.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "KNIGHT";
							Knight.validateMove(x, y);
						}
						else if( Piece.getPiece(clickedButton) == "ROOK")
						{ 
							Rook.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "ROOK";
							Rook.validateMove(x, y);
						}
						else if( Piece.getPiece(clickedButton) == "QUEEN")
						{ 
							Queen.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "QUEEN";
							Queen.validateMove(x, y);
						}
						else if( Piece.getPiece(clickedButton) == "KING")
						{ 
							King.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "KING";
							King.validateMoveForKing(x, y);
						}
					}
					//This is for the white turn
					else if(!isBlackTurn && Piece.checkBlackOrWhite(clickedButton) == "WHITE") 
					{
						if(Piece.getPiece(clickedButton) == "PAWN")
							{
							Pawn.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "PAWN";
							Pawn.validateMove(x,y);
						}
						else if( Piece.getPiece(clickedButton) == "BISHOP")
						{ 
							Bishop.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "BISHOP";
							Bishop.validateMove(x,y);
						}
						else if( Piece.getPiece(clickedButton) == "KNIGHT")
						{ 
							Bishop.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "KNIGHT";
							Knight.validateMove(x, y);
						}
						else if( Piece.getPiece(clickedButton) == "ROOK")
						{ 
							Rook.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "ROOK";
							Rook.validateMove(x, y);
						}
						else if( Piece.getPiece(clickedButton) == "QUEEN")
						{ 
							Queen.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "QUEEN";
							Queen.validateMove(x, y);
						}
						else if( Piece.getPiece(clickedButton) == "KING")
						{ 
							King.getClickedPiece(x, y);
							oldX = x;
							oldY = y;
							toChange = "KING";
							King.validateMoveForKing(x, y);
						}
					}
				}	
				
				//if the user click the same button he can choose another piece to move.
				else if( Piece.isTheSameButton(x,y))
				{
					board.refresh();
				}
				
				//this is for normal move.
				else if(board.squares[x][y].getBackground() == Color.green)
				{
						if(toChange == "PAWN")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
								Pawn.moveTo(x,y);
								if(Pawn.Ispromotion(x,y) == true)
								{
									messageBoxForPormotion();
									Pawn.promotion(x,y);
								}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									//adding move history to the Stack String value
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Pawn.moveTo(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.Black King will be Under Attack!! Black Pawn (" + oldX + "," + oldY + ")");
										Pawn.moveBack(x,y,oldX, oldY);
									}else
									{
										if(Pawn.Ispromotion(x,y) == true)
										{
											messageBoxForPormotion();
											Pawn.promotion(x,y);
										}
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Pawn.moveTo(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.Black King will be Under Attack!! Black Pawn (" + oldX + "," + oldY + ")");
									Pawn.moveBack(x,y,oldX, oldY);
								}else
								{
									if(Pawn.Ispromotion(x,y) == true)
									{
										messageBoxForPormotion();
										Pawn.promotion(x,y);
									}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
								Pawn.moveTo(x,y);
								if(Pawn.Ispromotion(x,y) == true)
								{
									messageBoxForPormotion();
									Pawn.promotion(x,y);
								}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Pawn.moveTo(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Pawn (" + oldX + "," + oldY + ")");
										Pawn.moveBack(x,y,oldX, oldY);
									}
									else
									{
										if(Pawn.Ispromotion(x,y) == true)
										{
											messageBoxForPormotion();
											Pawn.promotion(x,y);
										}
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Pawn.moveTo(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Pawn (" + oldX + "," + oldY + ")");
									Pawn.moveBack(x,y,oldX, oldY);
								}
								else
								{
									if(Pawn.Ispromotion(x,y) == true)
									{
										messageBoxForPormotion();
										Pawn.promotion(x,y);
									}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
							}
						}
						else if(toChange == "BISHOP")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Bishop.moveTo(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Bishop.moveTo(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.Black King will be Under Attack!! Black Bishop (" + oldX + "," + oldY +")" );
										Bishop.moveBack(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Bishop.moveTo(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.Black King will be Under Attack!! Black Bishop (" + oldX + "," + oldY +")" );
									Bishop.moveBack(x,y,oldX, oldY);
								}else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									Bishop.moveTo(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Bishop.moveTo(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Bishop (" + oldX + "," + oldY +")" );
										Bishop.moveBack(x,y,oldX, oldY);
									}
									else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Bishop.moveTo(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Bishop (" + oldX + "," + oldY +")" );
									Bishop.moveBack(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							//displaying this is a possible CheckMate position. Need to move King or Need to block
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
								moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
								field.setText(moveHistory.toString());
							}
						}
						else if(toChange == "KNIGHT")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Knight.moveTo(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Knight.moveTo(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.Black King will be Under Attack!! Black Knight (" + oldX + "," + oldY +")" );
										Knight.moveBack(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
						}
						else if(isBlackTurn && !King.isCheck("BLACK"))
						{
							Knight.moveTo(x,y);
							if(King.isCheck("BLACK"))
							{
								field1.setText("You cannot move.Black King will be Under Attack!! Black Knight (" + oldX + "," + oldY +")" );
								Knight.moveBack(x,y,oldX, oldY);
							}else
							{
								if(isBlackTurn == true)
								{
									isBlackTurn = false;
								}
								else
								{
									isBlackTurn = true;
								}
								moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
								field.setText(moveHistory.toString());
							}
						}
						else if(!isBlackTurn && King.isCheck("WHITE"))
						{
							if(King.isCheckMate("WHITE"))
							{
								Knight.moveTo(x,y);
								if(isBlackTurn == true)
								{
									isBlackTurn = false;
								}
								else
								{
									isBlackTurn = true;
								}
								moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
								field.setText(moveHistory.toString());
							}
							else
							{
								Knight.moveTo(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Knight (" + oldX + "," + oldY +")" );
									Knight.moveBack(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
						}
						else if(!isBlackTurn && !King.isCheck("WHITE"))
						{
							Knight.moveTo(x,y);
							if(King.isCheck("WHITE"))
							{
								field1.setText("You cannot move.White King will be Under Attack!! White Knight (" + oldX + "," + oldY +")" );
								Knight.moveBack(x,y,oldX, oldY);
							}
							else
							{
								if(isBlackTurn == true)
								{
									isBlackTurn = false;
								}
								else
								{
									isBlackTurn = true;
								}
								moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
								field.setText(moveHistory.toString());
							}
						}
							//displaying this is a possible CheckMate position. Need to move King or Need to block
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
							}
						}
						else if(toChange == "ROOK")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Rook.moveTo(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Rook.moveTo(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.Black King will be Under Attack!! Black Rook (" + oldX + "," + oldY +")" );
										Rook.moveBack(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Rook.moveTo(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.Black King will be Under Attack!! Black Rook (" + oldX + "," + oldY +")" );
									Rook.moveBack(x,y,oldX, oldY);
								}else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("White"))
								{
									Rook.moveTo(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Rook.moveTo(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Rook (" + oldX + "," + oldY +")" );
										Rook.moveBack(x,y,oldX, oldY);
									}
									else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Rook.moveTo(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Rook (" + oldX + "," + oldY +")" );
									Rook.moveBack(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							//displaying this is a possible CheckMate position. Need to move King or Need to block
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
							}
						}
						else if(toChange == "QUEEN")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Queen.moveTo(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Queen.moveTo(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.Black King will be Under Attack!! Black Queen (" + oldX + "," + oldY +")" );
										Queen.moveBack(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}					
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Queen.moveTo(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.Black King will be Under Attack!! Black Queen (" + oldX + "," + oldY +")" );
									Queen.moveBack(x,y,oldX, oldY);
								}else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									Queen.moveTo(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Queen.moveTo(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Queen (" + oldX + "," + oldY +")" );
										Queen.moveBack(x,y,oldX, oldY);
									}
									else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Queen.moveTo(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Queen (" + oldX + "," + oldY +")" );
									Queen.moveBack(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								field1.setText("BLACK King is Under Attack!!");
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								System.out.println("WHITE King is Under Attack!!");
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
							}
						}
						else if(toChange == "KING")
						{
							King.moveTo(x,y);
							if(isBlackTurn == true)
							{
								isBlackTurn = false;
							}
							else
							{
								isBlackTurn = true;
							}
							moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
							field.setText(moveHistory.toString());
						}
				}
				//This part is for attacking pieces.
				else if(board.squares[x][y].getBackground() == Color.red)
				{
						if(toChange == "PAWN")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
								Pawn.attack(x,y);
								if(Pawn.Ispromotion(x,y) == true)
								{
									messageBoxForPormotion();
									Pawn.promotion(x,y);
								}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Pawn.attack(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.Black King will be Under Attack!! Black Pawn (" + oldX + "," + oldY + ")");
										Pawn.moveBackforAttacking(x,y,oldX, oldY);
									}else
									{
										if(Pawn.Ispromotion(x,y) == true)
										{
											messageBoxForPormotion();
											Pawn.promotion(x,y);
										}
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Pawn.attack(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.Black King will be Under Attack!! Black Pawn (" + oldX + "," + oldY + ")");
									Pawn.moveBackforAttacking(x,y,oldX, oldY);
								}else
								{
									if(Pawn.Ispromotion(x,y) == true)
									{
										messageBoxForPormotion();
										Pawn.promotion(x,y);
									}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
								Pawn.attack(x,y);
								if(Pawn.Ispromotion(x,y) == true)
								{
									messageBoxForPormotion();
									Pawn.promotion(x,y);
								}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Pawn.attack(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Pawn (" + oldX + "," + oldY + ")");
										Pawn.moveBackforAttacking(x,y,oldX, oldY);
									}
									else
									{
										if(Pawn.Ispromotion(x,y) == true)
										{
											messageBoxForPormotion();
											Pawn.promotion(x,y);
										}
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Pawn.attack(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Pawn (" + oldX + "," + oldY + ")");
									Pawn.moveBackforAttacking(x,y,oldX, oldY);
								}
								else
								{
									if(Pawn.Ispromotion(x,y) == true)
									{
										messageBoxForPormotion();
										Pawn.promotion(x,y);
									}
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							
							if(Piece.eliminatedKing == true)
							{
								int response = JOptionPane.showConfirmDialog(null, "Game Ends!" 
																			+ Piece.playerWin +" wins the game! Rematch - Yes. Quit the Game -No", "Question", JOptionPane.YES_NO_OPTION);
						        Game.gameHistory(player1Name + " VS " + player2Name + "\t" + Piece.playerWin + " Win the Game.");
								if (response == JOptionPane.YES_OPTION) {
						        	dispose();
						        	new Game();
						        } 
						        else
						        {
						        	dispose();
						        }
							}
							
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
							}
						}
						else if(toChange == "BISHOP")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Bishop.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Bishop.attack(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.Black King will be Under Attack!! Black Bishop (" + oldX + "," + oldY +")" );
										Bishop.moveBackforAttacking(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
								
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Bishop.attack(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.Black King will be Under Attack!! Black Bishop (" + oldX + "," + oldY +")" );
									Bishop.moveBackforAttacking(x,y,oldX, oldY);
								}else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									Bishop.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Bishop.attack(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Bishop (" + oldX + "," + oldY +")" );
										Bishop.moveBackforAttacking(x,y,oldX, oldY);
									}
									else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Bishop.attack(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Bishop (" + oldX + "," + oldY +")" );
									Bishop.moveBackforAttacking(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							
							//If one of the King is eliminated Game will end and ask the player to re-match or quit the game
							if(Piece.eliminatedKing == true)
							{
								int response = JOptionPane.showConfirmDialog(null, "Game Ends!" 
																			+ Piece.playerWin +" wins the game! Rematch - Yes. Quit the Game -No", "Question", JOptionPane.YES_NO_OPTION);
						        Game.gameHistory(player1Name + " VS " + player2Name + "\t" + Piece.playerWin + " Win the Game.");
								if (response == JOptionPane.YES_OPTION) {
						        	dispose();
						        	new Game();
						        } 
						        else
						        {
						        	dispose();
						        }
							}
							//displaying this is a possible CheckMate position. Need to move King or Need to block
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								field1.setText("BLACK King is Under Attack from Bishop!!! King needs to be moved or block the path!!!");
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								field1.setText("WHITE King is Under Attack!! from Bishop!!! King needs to be moved or block the path!!!");
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
							}
						}
						else if(toChange == "KNIGHT")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Knight.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Knight.attack(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.BLACK King will be Under Attack!! Black Knight (" + oldX + "," + oldY +")" );
										Knight.moveBackforAttacking(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Knight.attack(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.BLACK King will be Under Attack!! Black Knight (" + oldX + "," + oldY +")" );
									Knight.moveBackforAttacking(x,y,oldX, oldY);
								}else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									Knight.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Knight.attack(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Knight (" + oldX + "," + oldY +")" );
										Knight.moveBackforAttacking(x,y,oldX, oldY);
									}
									else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Knight.attack(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Knight (" + oldX + "," + oldY +")" );
									Knight.moveBackforAttacking(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}

							//If one of the King is eliminated Game will end and ask the player to re-match or quit the game
							if(Piece.eliminatedKing == true)
							{
								int response = JOptionPane.showConfirmDialog(null, "Game Ends!" 
										+ Piece.playerWin +" wins the game! Rematch - Yes. Quit the Game -No", "Question", JOptionPane.YES_NO_OPTION);
								Game.gameHistory(player1Name + " VS " + player2Name + "\t" + Piece.playerWin + " Win the Game.");
								if (response == JOptionPane.YES_OPTION) {
									dispose();
									new Game();
								} 
								else
								{
									dispose();
								}
							}
							//displaying this is a possible CheckMate position. Need to move King or Need to block
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								field1.setText("BLACK King is Under Attack from Knight!!! King needs to be moved or block the path!!!");
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								field1.setText("WHITE King is Under Attack from the Knight!!! King needs to be moved or block the path!!!");
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!!");
								}
							}
						}
						else if(toChange == "ROOK")																																											
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Rook.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Rook.attack(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.BLACK King will be Under Attack!! Black Rook (" + oldX + "," + oldY +")" );
										Rook.moveBackforAttacking(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Rook.attack(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.BLACK King will be Under Attack!! Black Rook (" + oldX + "," + oldY +")" );
									Rook.moveBackforAttacking(x,y,oldX, oldY);
								}else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									Rook.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Rook.attack(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Rook (" + oldX + "," + oldY +")" );
										Rook.moveBackforAttacking(x,y,oldX, oldY);
									}
									else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Rook.attack(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Rook (" + oldX + "," + oldY +")" );
									Rook.moveBackforAttacking(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								//If one of the King is eliminated Game will end and ask the player to re-match or quit the game
								if(Piece.eliminatedKing == true)
								{
									int response = JOptionPane.showConfirmDialog(null, "Game Ends!" 
																				+ Piece.playerWin +" wins the game! Rematch - Yes. Quit the Game -No", "Question", JOptionPane.YES_NO_OPTION);
									Game.gameHistory(player1Name + " VS " + player2Name + "\t" + Piece.playerWin + " Win the Game.");
							        if (response == JOptionPane.YES_OPTION) {
							        	dispose();
							        	new Game();
							        } 
							        else
							        {
							        	dispose();
							        }
								}
								
								//displaying this is a possible CheckMate position. Need to move King or Need to block
								if(isBlackTurn && King.isCheck("BLACK"))
								{
									field1.setText("BLACK King is Under Attack from Rook!!! King needs to be moved or block the path!!!");
									if(King.isCheckMate("BLACK"))
									{
										field1.setText("BLACK King is in possible CheckMate position!!");
									}
								}
								else if(!isBlackTurn && King.isCheck("WHITE"))
								{
									field1.setText("WHITE King is Under Attack from Rook!!! King needs to be moved or block the path!!!");
									if(King.isCheckMate("WHITE"))
									{
										field1.setText("WHITE King is in possible CheckMate position!!");
									}
								}
							}
						}
						else if(toChange == "QUEEN")
						{
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								if(King.isCheckMate("BLACK"))
								{
									Queen.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Queen.attack(x,y);
									if(King.isCheck("BLACK"))
									{
										field1.setText("You cannot move.BLACK King will be Under Attack!! Black Queen (" + oldX + "," + oldY +")" );
										Queen.moveBackforAttacking(x,y,oldX, oldY);
									}else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(isBlackTurn && !King.isCheck("BLACK"))
							{
								Queen.attack(x,y);
								if(King.isCheck("BLACK"))
								{
									field1.setText("You cannot move.BLACK King will be Under Attack!! Black Queen (" + oldX + "," + oldY +")" );
									Queen.moveBackforAttacking(x,y,oldX, oldY);
								}else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								if(King.isCheckMate("WHITE"))
								{
									Queen.attack(x,y);
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
								else
								{
									Queen.attack(x,y);
									if(King.isCheck("WHITE"))
									{
										field1.setText("You cannot move.White King will be Under Attack!! White Queen (" + oldX + "," + oldY +")" );
										Queen.moveBackforAttacking(x,y,oldX, oldY);
									}
									else
									{
										if(isBlackTurn == true)
										{
											isBlackTurn = false;
										}
										else
										{
											isBlackTurn = true;
										}
										moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
										field.setText(moveHistory.toString());
									}
								}
							}
							else if(!isBlackTurn && !King.isCheck("WHITE"))
							{
								Queen.attack(x,y);
								if(King.isCheck("WHITE"))
								{
									field1.setText("You cannot move.White King will be Under Attack!! White Queen (" + oldX + "," + oldY +")" );
									Queen.moveBackforAttacking(x,y,oldX, oldY);
								}
								else
								{
									if(isBlackTurn == true)
									{
										isBlackTurn = false;
									}
									else
									{
										isBlackTurn = true;
									}
									moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
									field.setText(moveHistory.toString());
								}
							}

							//If one of the King is eliminated Game will end and ask the player to re-match or quit the game
							if(Piece.eliminatedKing == true)
							{
								int response = JOptionPane.showConfirmDialog(null, "Game Ends!" 
																			+ Piece.playerWin +" wins the game! Rematch - Yes. Quit the Game -No", "Question", JOptionPane.YES_NO_OPTION);
								Game.gameHistory(player1Name + " VS " + player2Name + "\t" + Piece.playerWin + " Win the Game.");
						        if (response == JOptionPane.YES_OPTION) {
						        	dispose();
						        	new Game();
						        } 
						        else
						        {
						        	dispose();
						        }
							}
							
							//displaying this is a possible CheckMate position. Need to move King or Need to block
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								field1.setText("BLACK King is Under Attack from Queen!!! King needs to be moved or block the path!!!");
								if(King.isCheckMate("BLACK"))
								{
									field1.setText("BLACK King is in possible CheckMate position!!");
								}
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								field1.setText("WHITE King is Under Attack from Queen!!! King needs to be moved or block the path!!!");
								if(King.isCheckMate("WHITE"))
								{
									field1.setText("WHITE King is in possible CheckMate position!!");
								}
							}
						}
						else if(toChange == "KING")
						{
							King.moveTo(x,y);
							if(isBlackTurn == true)
							{
								isBlackTurn = false;
							}
							else
							{
								isBlackTurn = true;
							} 
							moveHistory.push(Piece.checkBlackOrWhite(squares[x][y]) + " " + Piece.getPiece(squares[x][y]) +"(" +oldX + "," + oldY + ") >>> (" + x + "," + y + ")\n");
							field.setText(moveHistory.toString());
							
							if(isBlackTurn && King.isCheck("BLACK"))
							{
								field1.setText("BLACK King is Under Attack!!");
							}
							else if(!isBlackTurn && King.isCheck("WHITE"))
							{
								field1.setText("WHITE King is Under Attack!!");
							}
						}
						
					}
			}
				

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
	    }
}
	
