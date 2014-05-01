import java.io.File;

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
 * @custom.due 2014-05-06 00:00
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
     */
    public LRLTree(File source) {
        // TODO write constructor using a recursive loadNode() method
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
     * @return A Java code.
     */
    public String toJava() {
        String java = JAVA_HEAD;

        // TODO: write variable declarations

        java += root.toJava() + "}";

        return java;
    }

    /**
     * Executes the operations contained in the given <code>Node</code> and
     * returns the evaluated value.
     * @return The evaluated value of the <code>Node</code>. 
     */
    private int evaluate(Node node) {

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
    private void setValue(String name, int value) {
        // TODO assign variable value
    }

    /**
     * Gets the value of the given LRL variable.
     * @param name The name of the variable.
     * @return The value of the variable.
     */
    private int getValue(String name) {
        // TODO retrieve variable value
        return 0;
    }
}
