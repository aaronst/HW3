/**
 * A node for the LRL binary tree structure.
 * Each node represents a (sub)expression within an LRL program.
 * 
 * @custom.assign 
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
     * Sets the data in the <code>Node</code>.
     * @param data This represents either an operator, a variable, or an
     * integer. 
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Gets the data in the <code>Node</code>.
     * @return The data in the <code>Node</code>.
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the first subexpression.
     * @param left The first subexpression.
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Returns the first subexpression.
     * @return The first subexpression.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the second subexpression.
     * @param right The second subexpression.
     */
    public void setRight(Node right) {
        this.right = right;
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
     * represents an integer constant.
     * @return <code>true</code> if the <code>Node</code> represents an
     * integer constant, otherwise <code>false</code>.
     */
    public boolean isInteger() {
        boolean result = false;

        try {
            Integer.parseInt(data);
            result = true;
        } catch (NumberFormatException exception) {}

        return result;
    }

    /**
     * Returns <code>true</code> if, and only if, the <code>Node</code>
     * represents a variable.
     * @return <code>true</code> if the <code>Node</code> represents a
     * variable, otherwise <code>false</code>.
     */
    public boolean isVariable() {
        return !isOperation() && Character.isLetter(data.charAt(0));
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
        default:
            return false;
        }
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
                    + getRight().toJava() + "\n}\n";
            break;

        case Node.BLOCK:

            java = getLeft().toJava() + getRight().toJava();
            break;

        case Node.PRINT:

            java = "System.out.println(" + getLeft().toJava() + ");";
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
            lrl = "( " + getData() + " "
                    + getLeft().toLRL() + " )";
        } else if (isOperation()) {
            lrl = "( " + getData() + " "
                    + getLeft().toLRL() + " "
                    + getRight().toLRL() + " )";
        } else {
            lrl = getData();
        }

        return lrl;
    }
}
