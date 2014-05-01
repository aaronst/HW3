import java.io.BufferedReader;
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
        return toLRL(root);
    }

    /**
     * Generates Java code corresponding to the operations described in
     * the tree.
     * @return A Java code.
     */
    public String toJava() {
        String java = LRLConstants.JAVA_HEAD;

        // TODO: write variable declarations

        // TODO: write Java code

        return java;
    }

    /**
     * Executes the operations contained in the given <code>Node</code> and
     * returns the evaluated value.
     * @return The evaluated value of the <code>Node</code>. 
     */
    private int evaluate(Node node) {
        
        if (node.isInteger()) {
            return Integer.parseInt(node.getData());
        } else if (node.isVariable()) {
            // TODO: return variable value
        }
        
        switch (node.getData()) {
        case "+":
            return evaluate(node.getLeft()) + evaluate(node.getRight());
        case "-":
            return evaluate(node.getLeft()) - evaluate(node.getRight());
        case "*":
            return evaluate(node.getLeft()) * evaluate(node.getRight());
        case "/":
            return evaluate(node.getLeft()) / evaluate(node.getRight());
        case "==":
            if (evaluate(node.getLeft()) == evaluate(node.getRight())) {
                return 1;
            } else {
                return 0;
            }
        case "<":
            if (evaluate(node.getLeft()) < evaluate(node.getRight())) {
                return 1;
            } else {
                return 0;
            }
        case "=":
            /* TODO assign variable
            assign(node.getLeft().getData(),
                    evaluate(node.getRight()));
            */
            return 0;
        case "if":
            if (evaluate(node.getLeft()) == 1) {
                evaluate(node.getRight());
            }
            return 0;
        case "while":
            while (evaluate(node.getLeft()) == 1) {
                evaluate(node.getRight());
            }
            return 0;
        case "block":
            evaluate(node.getLeft());
            evaluate(node.getRight());
            return 0;
        case "print":
            System.out.println(evaluate(node.getLeft()));
            return 0;
        default:
            return 0;
        }
    }

    /**
     * Creates a string of LRL code representing the operations described
     * in the <code>Node</code>
     * @param node The <code>Node</code> to be converted.
     * @return The LRL code.
     */
    private String toLRL(Node node) {
        String lrl;

        if (node.isOperation()) {
            lrl = "( " + node.getData() + " " + toLRL(node.getLeft()) + " ";
            if (!node.isPrint())
                lrl += toLRL(node.getRight());
            lrl += " )";
        } else {
            lrl = node.getData();
        }

        return lrl;
    }
}
