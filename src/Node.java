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
     * The first operand of the expression.
     * May be <code>null</code> for integers or variables.
     */
    private Node left;
    
    /**
     * The second operand of the expression.
     * May be <code>null</code> for integers, variables, or print operators.
     */
    private Node right;
    
    public Node(String data) {
        this.data = data;
    }
    
    protected void setLeft(Node left) {
        this.left = left;
    }
    
    protected void setRight(Node right) {
        this.right = right;
    }
}
