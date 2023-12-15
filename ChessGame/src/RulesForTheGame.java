import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.nio.file.*;
import java.util.Scanner;
import java.io.*;
/**
 * class that describe "How to play the game" page. This class is for
 * the player to understand the game details. And in this class there is 
 * 2 buttons- done button to dispose the page and start button to play 
 * the game.
 * 
 * @WilliamTong
 */
public class RulesForTheGame extends JFrame
{
    private int hWindowHeight = 600;
    private int hWindowWidth = 600;
    private JPanel pNorth;
    private JPanel pWest;
    private JPanel pCenter;
    private JPanel pEast;
    private JPanel pSouth;
    private JButton DONE_Button;
    private JTextArea labelGO;
    private JTextArea labelMC;
    private JTextArea labelA;
    static JButton doneButton;
    static JButton startButton;
    /**
     * This is the constructor of the class.
     */
    
    public static void main(String[] args)
    {
    	RulesForTheGame a = new RulesForTheGame();
    }
    public RulesForTheGame()
    {
        //setting title
        setTitle("How to play the Game");
        //setting layout
        setLayout(new BorderLayout());
        
        gameOver();
        movementControl();
        ThingsNeedToKnow();
        buttons();
        head();
        
        //adding JPanels to the JFrame
        add(pWest, BorderLayout.WEST);
        add(pCenter, BorderLayout.CENTER);
        add(pEast, BorderLayout.EAST);
        add(pSouth, BorderLayout.SOUTH);
        add(pNorth, BorderLayout.NORTH);
        //registering the event handler
        doneButton.addActionListener(new ButtonListener());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);       
    }
    /**
     * Method that create the JPanel and tells the player when the game
     * will be over.
     */
    public void gameOver()
    {
        pWest = new JPanel();
        pWest.setPreferredSize(new Dimension(hWindowWidth/3, 500));
        Path path = Paths.get("gameOver.txt");
        String filePath = path.getFileName().toString();
        File fR = new File(filePath);
        StringBuilder iG = new StringBuilder(); // Initialize StringBuilder
        try {
            Scanner s = new Scanner(fR);
            while (s.hasNext()) {
            	iG.append(s.nextLine()).append("\n"); // Append each word to the StringBuilder
            }
            s.close();
        } catch (IOException e) {
            // Handle the IOException
        }
        labelGO = new JTextArea(iG.toString());
        labelGO.setPreferredSize(new Dimension(hWindowWidth/3, 500));
        labelGO.setLineWrap(true);
        labelGO.setWrapStyleWord(true);
        labelGO.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        labelGO.setBorder(BorderFactory.createTitledBorder("When Game is Over?"));
        labelGO.setFont(new Font("Verdana", Font.PLAIN, 12));
        labelGO.setBackground(new Color(224,224,224));
        labelGO.setEditable(false);
        pWest.add(labelGO);
        
    }
    /**
     * method to create the JPanel that tells the player how to control the chess pieces
     */
    public void movementControl() {
        pCenter = new JPanel();
        pCenter.setPreferredSize(new Dimension(hWindowWidth/3, 500));
        Path path = Paths.get("PieceMovement.txt");
        String filePath = path.getFileName().toString();
        File fR = new File(filePath);
        StringBuilder iS = new StringBuilder(); // Initialize StringBuilder
        try {
            Scanner s = new Scanner(fR);
            while (s.hasNext()) {
            	iS.append(s.nextLine()).append("\n"); // Append each word to the StringBuilder
            }
            s.close();
        } catch (IOException e) {
            // Handle the IOException
        }
        labelMC = new JTextArea(iS.toString()); // Use iS.toString() to get the final string
        labelMC.setPreferredSize(new Dimension(hWindowWidth/3, 500));
        labelMC.setLineWrap(true);
        labelMC.setWrapStyleWord(true);
        labelMC.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        labelMC.setBorder(BorderFactory.createTitledBorder("Movement Control"));
        labelMC.setFont(new Font("Verdana", Font.PLAIN, 10));
        labelMC.setBackground(new Color(224, 224, 224));
        labelMC.setEditable(false);
        pCenter.add(labelMC);
    }

    /**
     * Method to create the JPanel that tells the player about the chess game
     */
    public void ThingsNeedToKnow()
    {
        pEast = new JPanel();
        pEast.setPreferredSize(new Dimension (hWindowWidth/3,500));
        Path path = Paths.get("thingsneedtoknow.txt");
        String filePath = path.getFileName().toString();
        File fR = new File(filePath);
        StringBuilder iT = new StringBuilder(); // Initialize StringBuilder
        try {
            Scanner s = new Scanner(fR);
            while (s.hasNext()) {
            	iT.append(s.nextLine()).append("\n"); // Append each word to the StringBuilder
            }
            s.close();
        } catch (IOException e) {
            // Handle the IOException
        }
        labelA = new JTextArea(iT.toString());
        labelA.setPreferredSize(new Dimension (hWindowWidth/3,500));
        labelA.setLineWrap(true);
        labelA.setWrapStyleWord(true);
        labelA.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        labelA.setBorder(BorderFactory.createTitledBorder("ThingsNeedToKnow"));
        labelA.setFont(new Font("Verdana", Font.PLAIN, 12));
        labelA.setEditable(false);
        labelA.setBackground(new Color(224,224,224));
        pEast.add(labelA);
        
    }
    /**
     * This method is creating the "Done" button and adding
     * this button to "pSouth" JPanel.
     */
    public void buttons()
    {
        pSouth = new JPanel();
        doneButton = new JButton("Done");
        pSouth.add(doneButton);
    }
    /**
     * This method is creating Header which is the JLAbel and add this
     * to a "pNorth" JPanel.
     */
    public void head()
    {
        pNorth = new JPanel();
        JLabel a = new JLabel("How to play the GAME.....");
        a.setFont(new Font("Verdana", Font.PLAIN, 20));
        pNorth.add(a);
    }
    
    /**
     * This class is for the implements the ActionListener interface.
     * and if the user clicks the Done button it will dispose the page will lead to the Menu page
     */
    public class ButtonListener implements ActionListener
    {
        /**
         * Method to decide what will happen next when the user click the
         * button
         */
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() ==  doneButton)
            {
                new WelcomePage();
                dispose();
            }
        }
   }
}