import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A binary tree structure to hold a series of reverse polish notation
 * operations as described in LRL. Provides methods for:
 * - Creating a tree from an LRL file.
 * - Executing the operations held in the tree and evaluating its value.
 * - Generating the tree's equivalent LRL code.
 * - Generating the tree's equivalent Java code.
 *
 * @author Chad Condon
 * @author Aaron Stephens
 * @version 0.1
 * @custom.assign Binary Trees: LRL
 * @custom.instruct John Mayer, Ph.D.
 * @custom.course TCSS 342 Data Structures Spring 2014
 * @custom.due 2014-05-06
 */
public class LRLTree {

    /**
     * The beginning of the generated Java code.
     */
    private static final String JAVA_HEAD = "public class JavaCode {\n"
            + "public static void main(String[] args) {\n";

    private static final String INT_DECLARE = "int ";

    /**
     * The root <code>Node</code> of the binary tree.
     */
    private final Node root;

    /**
     * The <code>Map</code> containing all of the variables their values.
     */
    private final Map<String, Integer> environment;

    /**
     * Creates a binary tree to hold a series of reverse polish notation
     * operations as described in LRL.
     *
     * @param source plain text file containing the LRL source code
     * @throws FileNotFoundException
     */
    public LRLTree(final File source) throws FileNotFoundException {
        root = new Node();
        environment = new HashMap<>();
        List<String> code = loadCode(source);

        loadNode(root, code);
        loadEnvironment(root);
    }

    /**
     * Executes the operations contained in the tree and returns the
     * evaluated value.
     */
    public void evaluate() {
        resetEnvironment();
        evaluate(root);
    }

    /**
     * Generates the LRL code corresponding to the operations described in
     * the tree.
     *
     * @return LRL code
     */
    public String toLRL() {
        return root.toLRL();
    }

    /**
     * Generates Java code corresponding to the operations described in
     * the tree.
     *
     * @return Java code
     */
    public String toJava() {
        String java = JAVA_HEAD;

        java += javaDeclarations();
        java += root.toJava() + "}\n}";

        return java;
    }

    /**
     * Creates a <code>HashMap</code> from the Binary Tree that contains
     * all the variables and their values.
     */
    private void loadEnvironment(Node current) {
        if (current.getData().equals(Node.ASSIGN)) {
            environment.put(current.getLeft().getData(), 0);
            loadEnvironment(current.getRight());
        } else if (current.isOperation()) {
            loadEnvironment(current.getLeft());
            if (!current.isPrint()) {
                loadEnvironment(current.getRight());
            }
        }
    }

    /**
     * Executes the operations contained in the given <code>Node</code> and
     * returns the evaluated value.
     *
     * @return evaluated value of the <code>Node</code>
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
                setVariable(node.getLeft().getData(),
                        evaluate(node.getRight()));
                return 0;
            case Node.IF:
                if (evaluate(node.getLeft()) != 0) {
                    evaluate(node.getRight());
                }
                return 0;
            case Node.WHILE:
                while (evaluate(node.getLeft()) != 0) {
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
            return getVariable(node.getData());
        }
    }

    /**
     * Sets the value of the given LRL variable.
     *
     * @param name  The name of the variable.
     * @param value The value to be set,
     */
    private void setVariable(final String name, final int value) {
        environment.put(name, value);
    }

    /**
     * Gets the value of the given LRL variable.
     *
     * @param name The name of the variable.
     * @return variable value
     */
    private int getVariable(final String name) {
        return environment.get(name);
    }

    /**
     * Resets the value of each variable in the environment to zero.
     */
    private void resetEnvironment() {
        for (String key : environment.keySet()) {
            setVariable(key, 0);
        }
    }

    /**
     * Creates a <code>List</code> of LRL commands contained in a source
     * <code>File</code>.
     *
     * @param file LRL source
     * @return <code>List</code> of LRL commands
     * @throws FileNotFoundException
     */
    private List<String> loadCode(final File file)
            throws FileNotFoundException {
        List<String> code = new LinkedList<>();
        final Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            code.add(scanner.next());
        }
        scanner.close();

        return code;
    }

    /**
     * Populates a <code>Node</code> with the expressions described in LRL code.
     *
     * @param node <code>Node</code> to be populated
     * @param code <code>List</code> of LRL commands
     */
    private void loadNode(Node node, List<String> code) {
        int splitIndex;

        if (code.get(0).equals("(")) {
            code.remove(0);
        }
        node.setData(code.get(0));
        code.remove(0);
        splitIndex = getSplitIndex(code);
        if (node.isOperation()) {
            node.setLeft(new Node());
            loadNode(node.getLeft(), new LinkedList<>(code.subList(0,
                    splitIndex)));
            if (!node.isPrint()) {
                node.setRight(new Node());
                loadNode(
                        node.getRight(),
                        new LinkedList<>(code.subList(splitIndex,
                                code.size() - 1))
                        );
            }
        }
    }

    /**
     * Finds the index of the division between two subexpressions in a
     * <code>List</code> of LRL commands.
     *
     * @param code <code>List</code> of LRL commands
     * @return index of the division between two subexpressions
     */
    private int getSplitIndex(List<String> code) {
        int index = 0;
        int openParentheses = 0;

        for (String element : code) {

            if (element.equals("(")) {
                openParentheses++;
            } else if (element.equals(")")) {
                openParentheses--;
            }

            if (openParentheses == 0)
                break;
            index++;
        }

        return index + 1;
    }

    /**
     * Generates the Java code to declare all variables in the LRL code.
     *
     * @return Java declarations
     */
    private String javaDeclarations() {
        String declarations = "";

        if (!environment.isEmpty()) {
            for (String variable : environment.keySet()) {
                declarations += INT_DECLARE + variable + ";\n";
            }
        }

        return declarations;
    }
}