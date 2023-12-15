import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class that describe the frame of the WelcomePage in which 
 * the program will ask the player name. Then there will be 2 buttons.
 * 
 * @WilliamTong
 */
public class WelcomePage extends JFrame
{
    private JPanel mainPanel;
    JTextField textField1;
    JTextField textField2;
    JButton ruleButton,startTheGameButton,gameHistoryButton;
    JLabel Welcome;
    public static void main(String[] args)
    {
    	new WelcomePage();
    }
    /**
     * This is the constructor of  the class
     */
    public WelcomePage()
    {
    	setLayout(new BorderLayout());
    	
    	Path path = Paths.get("chessLogo2.jpeg");
    	
    	// Create the main panel with background image
    	mainPanel = new ImagePanel(path.getFileName().toString());

    	// Set layout manager for main panel
    	mainPanel.setLayout(new GridBagLayout());

    	// Create GridBagConstraints to center components
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.weightx = 1.0;
    	gbc.weighty = 1.0;
    	gbc.anchor = GridBagConstraints.CENTER;
    	
    	Welcome = new JLabel("Welcome to Chess Game");
    	Welcome.setForeground(Color.WHITE);
    	Welcome.setFont(new Font("Arial", Font.BOLD, 20)); // Adjust font and size as needed

    	// Add the welcome label to the main panel
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = 2; // Span two columns
    	gbc.insets = new Insets(0, 0, 20, 0); // Add 20 pixels of vertical space between components
    	mainPanel.add(Welcome, gbc);

    	
    	// Create a JPanel for text fields
    	JPanel textFieldsPanel = new JPanel(new GridBagLayout());
    	
    	Insets insets = new Insets(0, 0, 10, 0); // Add 10 pixels of vertical space between components
    	
    	// Set a transparent background color for the panel
    	textFieldsPanel.setOpaque(false);
    	JLabel labelPlayer1 = new JLabel("Player 1 Name:");
    	labelPlayer1.setForeground(Color.WHITE);
    	textFieldsPanel.add(labelPlayer1, gbc);
    	
    	// Add the first text field with adjusted size
    	gbc.gridx = 1; // Move to the next column
    	textField1 = new JTextField("player1");
    	gbc.insets = insets; 
    	textField1.setPreferredSize(new Dimension(150, 30)); // Adjust size here
    	textFieldsPanel.add(textField1, gbc);
    	// Reset grid coordinates
    	gbc.gridx = 0;
    	gbc.gridy = 1; // Move to the next row

    	// Add Player 2 Name label
    	JLabel labelPlayer2 = new JLabel("Player 2 Name:");
    	labelPlayer2.setForeground(Color.WHITE);
    	textFieldsPanel.add(labelPlayer2, gbc);

    	// Add the second text field with adjusted size
    	gbc.gridx = 1; // Move to the next column
    	textField2 = new JTextField("player2");
    	textField2.setPreferredSize(new Dimension(150, 30)); // Adjust size here
    	textFieldsPanel.add(textField2, gbc);

    	// Add the textFieldsPanel to the main panel
    	mainPanel.add(textFieldsPanel, gbc);
    	
    	// Add the main panel to the frame
    	add(mainPanel, BorderLayout.CENTER);

    	// Create a JPanel for buttons at the bottom
    	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	buttonPanel.setOpaque(false);

    	// Add three buttons
    	ruleButton = new JButton("Rules for the Game");
    	startTheGameButton = new JButton("Strat The Game");
    	gameHistoryButton = new JButton("View History");
    	ruleButton.addActionListener(new ButtonListener());
    	startTheGameButton.addActionListener(new ButtonListener());
    	gameHistoryButton.addActionListener(new ButtonListener());
      	buttonPanel.add(ruleButton);
      	buttonPanel.add(gameHistoryButton);
      	buttonPanel.add(startTheGameButton);
      	// Add the buttonPanel to the main panel at the bottom
      	gbc.gridy = 2; // Move to the bottom
      	mainPanel.add(buttonPanel, gbc);

      	// Add the main panel to the frame
      	add(mainPanel, BorderLayout.CENTER);
      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	setSize(800, 600);
      	setLocationRelativeTo(null);
      	setVisible(true);
    }
    
    // Custom panel for background image
    private static class ImagePanel extends JPanel {
    	
    	private BufferedImage backgroundImage;
    	
    	//constructor of the class
    	public ImagePanel(String imagePath)
    	{
    		try
    		{
    			backgroundImage = ImageIO.read(new File(imagePath));
    		} 	
    		catch (IOException e) 
    		{
    			e.printStackTrace();
    		}
    	}
    	@Override
    	protected void paintComponent(Graphics g)
    	{
    		super.paintComponent(g);
    		if (backgroundImage != null)
    		{
    			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    		}
    	}
    } 
  public class ButtonListener implements ActionListener
  {
      /**
       * Method to decide what will happen next when the user click the
       * buttons
       */
      public void actionPerformed(ActionEvent e)
      {
          if(e.getSource() ==  ruleButton)
          {
        	  new RulesForTheGame();
              dispose();
          }
          else if(e.getSource() == gameHistoryButton)
          {
        	  new GameHistory();
          }
          else if(e.getSource() == startTheGameButton)
          {
        	  Board.player1Name = textField1.getText();
        	  Board.player2Name = textField2.getText();
              new Game();
              dispose();
          }
      }
 }
}