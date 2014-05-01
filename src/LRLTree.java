import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * A binary tree structure to hold a series of reverse polish notation
 * operations as described in LRL. Provides methods for:
 *   Creating a tree from an LRL file.
 *   Executing the operations held in the tree and evaluating its value.
 *   Generating the tree's equivalent LRL code.
 *   Generating the tree's equivalent Java code.
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
public class LRLTree {

    /**
     * The beginning of the generated Java code.
     */
    private static String JAVA_HEAD = "public class JavaCode {\n"
            + "    public static void main(String[] args) {\n";

    /**
     * The root <code>Node</code> of the binary tree.
     */
    private Node root;

    /**
     * Creates a binary tree to hold a series of reverse polish notation
     * operations as described in LRL.
     * @param source The plain text file containing the LRL source code.
     * @throws FileNotFoundException 
     */
    public LRLTree(final File source) throws FileNotFoundException {
        List<String> code = loadCode(source);
        loadNode(root, code);
    }

    /**
     * Executes the operations contained in the tree and returns the
     * evaluated value.
     * @return The evaluated value of the tree. 
     */
    public int evaluate() {
        return evaluate(root);
    }

    /**
     * Generates the LRL code corresponding to the operations described in
     * the tree.
     * @return The LRL code.
     */
    public String toLRL() {
        return root.toLRL();
    }

    /**
     * Generates Java code corresponding to the operations described in
     * the tree.
     * @return Java code.
     */
    public String toJava() {
        String java = JAVA_HEAD;

        // TODO write variable declarations

        java += root.toJava() + "}";

        return java;
    }

    /**
     * Executes the operations contained in the given <code>Node</code> and
     * returns the evaluated value.
     * @return The evaluated value of the <code>Node</code>. 
     */
    private int evaluate(final Node node) {

        switch (node.getData()) {
        case Node.ADD:

            return evaluate(node.getLeft()) + evaluate(node.getRight());

        case Node.SUBTRACT:

            return evaluate(node.getLeft()) - evaluate(node.getRight());

        case Node.MULTIPLY:

            return evaluate(node.getLeft()) * evaluate(node.getRight());
        case Node.DIVIDE:

            return evaluate(node.getLeft()) / evaluate(node.getRight());

        case Node.EQUAL:

            if (evaluate(node.getLeft()) == evaluate(node.getRight())) {
                return 1;
            }
            return 0;

        case Node.LESS_THAN:

            if (evaluate(node.getLeft()) < evaluate(node.getRight())) {
                return 1;
            }
            return 0;

        case Node.ASSIGN:

            setValue(node.getLeft().getData(),
                    evaluate(node.getRight()));
            return 0;

        case Node.IF:

            if (evaluate(node.getLeft()) == 1) {
                evaluate(node.getRight());
            }
            return 0;

        case Node.WHILE:

            while (evaluate(node.getLeft()) == 1) {
                evaluate(node.getRight());
            }
            return 0;

        case Node.BLOCK:

            evaluate(node.getLeft());
            evaluate(node.getRight());
            return 0;

        case Node.PRINT:

            System.out.println(evaluate(node.getLeft()));
            return 0;
        }

        try {
            return Integer.parseInt(node.getData());
        } catch (NumberFormatException exception) {
            return getValue(node.getData());
        }
    }

    /**
     * Sets the value of the given LRL variable.
     * @param name The name of the variable.
     * @param value The value to be set, 
     */
    private void setValue(final String name, final int value) {
        // TODO assign variable value
    }

    /**
     * Gets the value of the given LRL variable.
     * @param name The name of the variable.
     * @return The value of the variable.
     */
    private int getValue(final String name) {
        // TODO retrieve variable value
        return 0;
    }

    /**
     * Creates a <code>List</code> of LRL commands contained in a source
     * <code>File</code>.
     * @param file The LRL source.
     * @return A <code>List</code> of LRL commands.
     * @throws FileNotFoundException 
     */
    private List<String> loadCode(final File file)
            throws FileNotFoundException {
        List<String> code = new LinkedList<String>();
        final Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            code.add(scanner.next());
        }

        scanner.close();

        return code;
    }

    /**
     * Populates a <code>Node</code> with the expressions described in LRL code.
     * @param node The <code>Node</code> to be populated.
     * @param code A <code>List</code> of LRL commands.
     */
    private void loadNode(Node node, List<String> code) {
        int splitIndex = 0;

        if (code.get(0).equals("(")) {
            code.remove(0);
        }

        node.setData(code.get(0));
        code.remove(0);

        splitIndex = getSplitIndex(code);

        if (node.isOperation()) {
            loadNode(node.getLeft(),code.subList(0, splitIndex));
            if (!node.isPrint()) {
                loadNode(node.getRight(),
                        code.subList(splitIndex, code.size() - 1));
            }
        }
    }
    
    /**
     * Finds the index of the division between two subexpressions in a
     * <code>List</code> of LRL commands.
     * @param code A <code>List</code> of LRL commands.
     * @return The index of the division between two subexpressions
     */
    private int getSplitIndex(List<String> code) {
        int index = 0;
        int openParens = 0;
        
        for (String element : code) {

            if (element.equals("(")) {
                openParens++;
            } else if (element.equals(")")) {
                openParens--;
            }

            if (openParens == 0)
                break;
            index++;
        }
        
        return index;
    }
}
