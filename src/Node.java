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
     * represents an operation.
     * @return <code>true</code> if the <code>Node</code> represents an
     * operation, otherwise <code>false</code>.
     */
    public boolean isOperation() {
        return LRLConstants.OPERATORS.contains(data);
    }
}
