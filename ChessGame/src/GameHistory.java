import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class is to show the gameHisotry of the 
 */
public class GameHistory {

	public GameHistory()
	{
        // Read content from the file
        String fileContent = readFromFile("gameHistory.txt");

        // Create a JTextArea with the file content
        JTextArea textArea = new JTextArea(fileContent);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Create a JScrollPane containing the JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        // Show a JOptionPane with the scrollable panel
        JOptionPane.showMessageDialog( null,  scrollPane,  "This is the Game History",  JOptionPane.PLAIN_MESSAGE);
    }

	/**
	 * This method is to read the File
	 * @param fileName name of the file
	 * @return string read string
	 */
    private static String readFromFile(String fileName) {
        Path path = Paths.get(fileName);
        File file = path.toFile();

        StringBuilder content = new StringBuilder();

        //using try with resources to close scanner when it's no longer needed
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file: " + e.getMessage();
        }

        return content.toString();
    }
}
