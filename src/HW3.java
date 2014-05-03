import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <code>LRLTree</code> test runner.
 *
 * @custom.assign Binary Trees: LRL
 * @author Chad Condon
 * @author Aaron Stephens
 * @version 0.1
 *
 * @custom.instruct John Mayer, Ph.D.
 * @custom.course TCSS 342 Data Structures Spring 2014
 * @custom.due 2014-05-06
 */
public class HW3 {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String filename;

        while (true) {
            System.out.print("Enter file name: ");
            filename = scanner.nextLine();
            try {
                LRLTree tree = new LRLTree(new File(filename));
                System.out.println(tree.toLRL());
                tree.evaluate();
                System.out.println(tree.toJava());
                break;
            } catch (FileNotFoundException exception) {
                System.out.println(filename + " is not a valid file.");
            }
        }
    }
}