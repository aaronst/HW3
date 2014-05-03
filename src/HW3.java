import java.io.File;
import java.io.FileNotFoundException;

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
 * @custom.due 2014-05-07
 */
public class HW3 {
    
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Aaron Stephens & Chad Condon - Homework 3");
        // The file chosen can be changed out with any of the others
        LRLTree tree = new LRLTree(new File("nested2.txt"));

        System.out.println("\n====The LRL Code====\n");
        System.out.println(tree.toLRL());

        System.out.println("\n====The Java Code====\n");
        System.out.println(tree.toJava());

        System.out.println("\n====The Evaluation====\n");
        tree.evaluate();
    }
}
