/**
 * Represents a node in the directory tree
 * @author Boren Wang
 *   Email: Boren.Wang@stonybrook.edu
 *   SBU ID: 111385010
 */
public class DirectoryNode {
    private DirectoryNode left;
    private DirectoryNode middle;
    private DirectoryNode right;
    private DirectoryNode parent;
    private String name;
    private boolean isFile;

    /**
     * Constructors for the DirectoryNode
     */
    public DirectoryNode() {

    }

    /**
     * Getter for the left node
     * @return
     *   the left node
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /**
     * Getter for the middle node
     * @return
     *   the middle node
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /**
     * Getter for the right node
     * @return
     *   the right node
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     * Getter for the parent node
     * @return
     *   the parent node
     */
    public DirectoryNode getParent() {
        return parent;
    }

    /**
     * Getter for the name of the node
     * @return
     *   the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Checks whether the node is file or not
     * @return
     *   true if it is a file, false otherwise
     */
    public boolean isFile() {
        return isFile;
    }

    /**
     * Setter for the left node
     * @param left
     *   left node to be set
     */
    public void setLeft(DirectoryNode left) {
        this.left = left;
    }

    /**
     * Setter for the middle node
     * @param middle
     *   left node to be set
     */
    public void setMiddle(DirectoryNode middle) {
        this.middle = middle;
    }

    /**
     * Setter for the right node
     * @param right
     *   right node to be set
     */
    public void setRight(DirectoryNode right) {
        this.right = right;
    }

    /**
     * Setter for the parent node
     * @param parent
     *   parent node to be set
     */
    public void setParent(DirectoryNode parent) {
        this.parent = parent;
    }

    /**
     * Setter for the name of the node
     * @param name
     *   name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for isFile data field
     * @param isFile
     *   stores whether the node is file or not
     */
    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    /**
     * Adds a new child to the node
     * @param newChild
     *   new child to be added
     * @throws FullDirectoryException
     *   indicates the directory is full
     * @throws NotADirectoryException
     *   indicates the node is not a directory
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
        if(this.isFile==true){
            throw new NotADirectoryException("This node is a file and it is not allowed to have children.");
        }
        if(this.left!=null&&this.middle!=null&&this.right!=null){
            throw new FullDirectoryException("The directory is full.");
        }
        if(this.left==null){
            left=newChild;
        } else if(this.middle==null){
            middle=newChild;
        } else{
            right=newChild;
        }
        newChild.setParent(this);
    }




}
