import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class holding all constant values used in LRL.
 * 
 * @custom.assign Binary Trees: LRL
 * @author Chad Condon
 * @author Aaron Stephens
 * @version 0.1
 * 
 * @custom.instruct John Mayer, Ph.D.
 * @custom.course TCSS 342 Data Structures Spring 2014
 * @custom.due 2014-05-06 00:00
 */
public class LRLConstants {

    
    /**
     * A list of all valid LRL operators.
     */
    public static List<String> OPERATORS =
            new ArrayList<String>(Arrays.asList(new String[]{
                    "+", "-", "*", "/",
                    "==", "<",
                    "=",
                    "if", "while", "block",
                    "print"
            }));

    /**
     * The index of the addition operator.
     */
    public static int ADD = 0;
    
    /**
     * The index of the subtraction operator.
     */
    public static int SUBTRACT = 1;
    
    /**
     * The index of the multiplication operator.
     */
    public static int MULTIPLY = 2;
    
    /**
     * The index of the division operator.
     */
    public static int DIVIDE = 3;
    
    /**
     * The index of the equality comparator.
     */
    public static int EQUAL = 4;
    
    /**
     * The index of the less than comparator.
     */
    public static int LESS_THAN = 5;
    
    /**
     * The index of the assignment operator
     */
    public static int ASSIGN = 6;
    
    /**
     * The index of the if operator.
     */
    public static int IF = 7;
    
    /**
     * The index of the while operator.
     */
    public static int WHILE = 8;
    
    /**
     * The index of the block operator.
     */
    public static int BLOCK = 9;
    
    /**
     * The index of the print operator.
     */
    public static int PRINT = 10;
    
    /**
     * The beginning of the generated Java code.
     */
    public static String JAVA_HEAD = "public class JavaCode {\n"
            + "    public static void main(String[] args) {\n";
    
    /**
     * Private constructor to prevent instantiation.
     */
    private LRLConstants() {}

}
