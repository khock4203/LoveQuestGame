import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Scoreboard
 * Creates functionality for a linked list to keep track of the
 * high scores in LoveQuest.
 * Contains methods to write the list to a txt file, get the list
 * from a txt file, and get the top five for the display.
 *
 * @author Cameron Cischke
 * class: cs1131
 * lab: L01
 */
public class Scoreboard extends SinglyLinkedList<Integer> {
    SinglyLinkedList<Integer> board = new SinglyLinkedList<>();
    File scoreboardFile = new File("scoreboard.txt");

    /**
     * Reads the contents of board.txt and creates a linked list using them.
     */
    public void getFileBoard() {
        int loc = 0;
        try {
            Scanner reader = new Scanner( scoreboardFile );
            while ( reader.hasNext() ) {
                String name = reader.next();
                //System.out.println( name );
                int value = reader.nextInt();
                //System.out.println( value );
                board.add(loc, value, name);
                //System.out.println( board.getName(0));
                //System.out.println( board.get(0));
                loc++;
            }
            reader.close();
            //System.out.println(board.size());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    /**
     * Takes the current linked list and writes it to board.txt
     * @param name
     * @param score
     */
    public void updateBoard( String name, int score) {
        int i = 0;
        try {
            PrintWriter writer = new PrintWriter(scoreboardFile);
            while (i < board.size() && !board.isEmpty()) {
                if (board.get(i) > score) {
                    //System.out.println(i);
                    i++;
                } else {
                    break;
                }
            }
            //System.out.println(i);
            board.add(i, score, name);
            for (int j = 0; j < board.size(); j++) {
                writer.print(board.getName(j) + " ");
                writer.println(board.get(j));
                }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Gets the top 5 scores from board.txt, returns them for display.
     * @return a string containing the formatted list o
     */
    public String getBoard() {
        String board = "";
        try {
            Scanner reader = new Scanner(scoreboardFile);
            int i = 0;
            while (reader.hasNext() && i < 10) {
                board += (reader.next() + " ");
                i++;
                if ( ( i % 2 ) == 0 ) {
                    board += "\n";
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return board;
    }
}

