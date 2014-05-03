/**
 * A node for the LRL binary tree structure.
 * Each node represents a (sub)expression within an LRL program.
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
public class Node {

    /**
     * The LRL addition operator.
     */
    public static final String ADD = "+";
    
    /**
     * The LRL subtraction operator.
     */
    public static final String SUBTRACT = "-";
    
    /**
     * The LRL multiplication operator.
     */
    public static final String MULTIPLY = "*";
    
    /**
     * The LRL division operator.
     */
    public static final String DIVIDE = "/";
    
    /**
     * The LRL equality operator.
     */
    public static final String EQUAL = "==";
    
    /**
     * The LRL inequality operator.
     */
    public static final String LESS_THAN = "<";
    
    /**
     * The LRL assignment operator.
     */
    public static final String ASSIGN = "=";
    
    /**
     * The LRL conditional operator.
     */
    public static final String IF = "if";
    
    /**
     * The LRL loop operator.
     */
    public static final String WHILE = "while";
    
    /**
     * The LRL block operator.
     */
    public static final String BLOCK = "block";
    
    /**
     * The LRL print operator.
     */
    public static final String PRINT = "print";

    /**
     * The data contained in the <code>Node</code>. This must represent
     * either an operator, a variable, or an integer. 
     */
    private String data;

    /**
     * The first subexpression.
     * May be <code>null</code> for integers or variables.
     */
    private Node left;

    /**
     * The second operand of the expression.
     * May be <code>null</code> for integers, variables, or print operators.
     */
    private Node right;

    /**
     * Creates a binary node representing an LRL expression.
     * @param data This must represent either an operator, a variable, or an
     * integer. 
     */
    public Node(String data) {
        setData(data);
        setLeft(null);
        setRight(null);
    }

    /**
     * Returns the first subexpression.
     * @return The first subexpression.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Returns the second subexpression.
     * @return The second subexpression.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Returns <code>true</code> if, and only if, the <code>Node</code>
     * represents an operation.
     * @return <code>true</code> if the <code>Node</code> represents an
     * operation, otherwise <code>false</code>.
     */
    public boolean isOperation() {
        switch(data) {
        case ADD:
        case SUBTRACT:
        case MULTIPLY:
        case DIVIDE:
        case EQUAL:
        case LESS_THAN:
        case ASSIGN:
        case IF:
        case WHILE:
        case BLOCK:
        case PRINT:
            return true;
        }

        return false;
    }

    /**
     * Returns <code>true</code> if, and only if, the <code>Node</code>
     * represents a print operation.
     * @return <code>true</code> if the <code>Node</code> represents a
     * print operation, otherwise <code>false</code>.
     */
    public boolean isPrint() {
        return data.equals(PRINT);
    }
    
    /**
     * Creates Java code corresponding the the operations described in the
     * </code>Node</code>
     * @return The Java code.
     */
    public String toJava() {
        String java;

        switch (getData()) {
        case Node.ADD:
        case Node.SUBTRACT:
        case Node.MULTIPLY:
        case Node.DIVIDE:
            java = "(" + getLeft().toJava() + " "
                    + getData() + " "
                    + getRight().toJava() + ")";
            break;
        case Node.ASSIGN:
            java = getLeft().toJava() + " "
                    + getData() + " "
                    + getRight().toJava() + ";\n";
            break;
        case Node.EQUAL:
        case Node.LESS_THAN:
            java = getLeft().toJava() + " "
                    + getData() + " "
                    + getRight().toJava();
            break;
        case Node.IF:
        case Node.WHILE:
            java = getData() + " ("
                    + getLeft().toJava() + ") {\n"
                    + getRight().toJava() + "}\n";
            break;
        case Node.BLOCK:
            java = getLeft().toJava() + getRight().toJava();
            break;
        case Node.PRINT:
            java = "System.out.println(" + getLeft().toJava() + ");\n";
            break;
        default:    // constant or variable
            java = getData();
        }

        return java;
    }
    
    /**
     * Creates a string of LRL code representing the operations described
     * in the <code>Node</code>.
     * @return The LRL code.
     */
    public String toLRL() {
        String lrl;

        if (isPrint()) {
            lrl = "( " + getData() + " " + getLeft().toLRL() + " )";
        } else if (isOperation()) {
            lrl = "( " + getData() + " " + getLeft().toLRL()
                    + " " + getRight().toLRL() + " )";
        } else {
            lrl = getData();
        }

        return lrl;
    }

    /**
     * Sets the data in the <code>Node</code>.
     *
     * @param data This represents either an operator, a variable, or an
     *             integer.
     */
    void setData(final String data) {
        this.data = data;
    }

    /**
     * Gets the data in the <code>Node</code>.
     *
     * @return The data in the <code>Node</code>.
     */
    String getData() {
        return data;
    }

    /**
     * Sets the first subexpression.
     *
     * @param leftNode The first subexpression.
     */
    void setLeft(Node leftNode) {
        this.left = leftNode;
    }

    /**
     * Sets the second subexpression.
     *
     * @param rightNode The second subexpression.
     */
    void setRight(Node rightNode) {
        this.right = rightNode;
    }

}