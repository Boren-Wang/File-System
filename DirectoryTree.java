/**
 * Represents the directory tree
 * @author Boren Wang
 *   Email: Boren.Wang@stonybrook.edu
 *   SBU ID: 111385010
 */
public class DirectoryTree {
    DirectoryNode root;
    DirectoryNode cursor;

    /**
     * Constructor for the directory tree
     */
    public DirectoryTree(){
        root = new DirectoryNode();
        root.setName("root");
        cursor = root;
    }

    /**
     * Moves the cursor to the root node of the tree.
     */
    public void resetCursor(){
        cursor = root;
    }

    /**
     * Moves the cursor to the directory with the name indicated by name.
     * @precondition
     *   'name' references a valid directory ('name' cannot reference a file).
     * @param name
     *   the name of the directory where the cursor will be changed to
     * @throws NotADirectoryException
     *   Thrown if the node with the indicated name is a file, as files cannot be selected by the cursor, or cannot be found.
     */
    public void changeDirectory(String name) throws NotADirectoryException {
        if (cursor.getLeft()!=null){
            if(cursor.getLeft().getName().equals(name)) {
                if (cursor.getLeft().isFile() == true) {
                    throw new NotADirectoryException("Cannot change directory into file.");
                } else {
                    cursor = cursor.getLeft();
                    return;
                }
            }
        }
        if (cursor.getMiddle()!=null) {
            if (cursor.getMiddle().getName().equals(name)) {
                if (cursor.getMiddle().isFile() == true) {
                    throw new NotADirectoryException("Cannot change directory into file.");
                } else {
                    cursor = cursor.getMiddle();
                    return;
                }
            }
        }
        if (cursor.getRight()!=null) {
            if (cursor.getRight().getName().equals(name)) {
                if (cursor.getRight().isFile() == true) {
                    throw new NotADirectoryException("Cannot change directory into file.");
                } else {
                    cursor = cursor.getRight();
                    return;
                }
            }
        }
        System.out.println("No such directory named '"+name+"'.");
    }

    /**
     * Returns a String containing the path of directory names from the root node of the tree to the cursor
     * @return
     *   the String of the path of the working directory
     */
    public String presentWorkingDirectory(){
        if(cursor==root){
            return "root";
        }
        String result = "root/";
        DirectoryNode ptr = cursor;
        String[] names = new String[100];
        int i = 0;
        while(ptr!=root){
            names[i] = ptr.getName();
            i++;
            ptr=ptr.getParent();
        }
        i--;
        while (i >0) {
            result+=names[i]+"/";
            i--;
        }
        result+=names[0];
        return result;
    }

    /**
     * Returns the path of a node
     * @param node
     *   the node whose path will be returned
     * @return
     *   the path of the node
     */
    public String getPath(DirectoryNode node){
        if(node==root){
            return "root";
        }
        String result = "root/";
        DirectoryNode ptr = node;
        String[] names = new String[100];
        int i = 0;
        while(ptr!=root){
            names[i] = ptr.getName();
            i++;
            ptr=ptr.getParent();
        }
        i--;
        while (i >0) {
            result+=names[i]+"/";
            i--;
        }
        result+=names[0];
        return result;
    }

    /**
     * Returns a String containing a space-separated list of names of all the child directories or files of the cursor.
     * @return
     *   A formatted String of DirectoryNode names.
     */
    public String listDirectory(){
        String result="";
        if(cursor.getLeft()!=null){
            result+=cursor.getLeft().getName();
        }else{
            return result;
        }
        if(cursor.getMiddle()!=null){
            result+=" "+cursor.getMiddle().getName();
        }else{
            return result;
        }
        if(cursor.getRight()!=null){
            result+=" "+cursor.getRight().getName();
        }
        return result;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
     */
    public void printDirectoryTree(){
        printDirectoryTree(cursor,0);
    }
    /**
     * Recursive helper function for printDirectory()
     */
    public void printDirectoryTree(DirectoryNode node, int indents){
        for(int i=indents; i>0; i--){
            System.out.print("  ");
        }
        if(node.isFile()){
            System.out.println("-"+node.getName());
        }else{
            System.out.println("|-"+node.getName());
        }
        if(node.getLeft()!=null){
            printDirectoryTree(node.getLeft(),indents+1);
        }
        if(node.getMiddle()!=null){
            printDirectoryTree(node.getMiddle(),indents+1);
        }
        if(node.getRight()!=null){
            printDirectoryTree(node.getRight(),indents+1);
        }
    }

    /**
     * Creates a directory with the indicated name and adds it to the children of the cursor node.
     * @param name
     *   The name of the directory to add.
     * @precondition
     *   'name' is a legal argument (does not contain spaces " " or forward slashes "/").
     * @throws IllegalArgumentException
     *   Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException
     *   Thrown if all child references of this directory are occupied.
     * @throws NotADirectoryException
     *   Indicates addChild(Directory node) method takes on a node that is not a directory
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException{
        for(int i=0; i<name.length();i++){
            if(name.charAt(i)==' ' || name.charAt(i)=='/'){
                throw new IllegalArgumentException("Not a legal name.\nName containing spaces or forward slashes is not allowed.");
            }
        }
        if(cursor.getLeft()!=null){
            if(cursor.getLeft().getName().equals(name) && cursor.getLeft().isFile()==false){
                System.out.println("Directory named '"+name+"' already exists");
                return;
            }
        }
        if(cursor.getMiddle()!=null){
            if(cursor.getMiddle().getName().equals(name) && cursor.getMiddle().isFile()==false){
                System.out.println("Directory named '"+name+"' already exists");
                return;
            }
        }
        if(cursor.getRight()!=null){
            if(cursor.getRight().getName().equals(name) && cursor.getMiddle().isFile()==false){
                System.out.println("Directory named '"+name+"' already exists");
                return;
            }
        }
        DirectoryNode newChild = new DirectoryNode();
        newChild.setName(name);
        newChild.setParent(cursor);
        cursor.addChild(newChild);
    }

    /**
     * Creates a file with the indicated name and adds it to the children of the cursor node.
     * @param name
     *   The name of the file to add.
     * @precondition
     *   'name' is a legal argument (does not contain spaces " " or forward slashes "/").
     * @throws IllegalArgumentException
     *   Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException
     *   Thrown if all child references of this directory are occupied.
     * @throws NotADirectoryException
     *   Indicates addChild(Directory node) method takes on a node that is not a directory
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException{
        for(int i=0; i<name.length();i++){
            if(name.charAt(i)==' ' || name.charAt(i)=='/'){
                throw new IllegalArgumentException("Not a legal name.\nName containing spaces or forward slashes is not allowed.");
            }
        }
        if(cursor.getLeft()!=null){
            if(cursor.getLeft().getName().equals(name) && cursor.getLeft().isFile()==true){
                System.out.println("File named '"+name+"' already exists");
                return;
            }
        }
        if(cursor.getMiddle()!=null){
            if(cursor.getMiddle().getName().equals(name) && cursor.getMiddle().isFile()==true){
                System.out.println("File named '"+name+"' already exists");
                return;
            }
        }
        if(cursor.getRight()!=null){
            if(cursor.getRight().getName().equals(name) && cursor.getMiddle().isFile()==true){
                System.out.println("File named '"+name+"' already exists");
                return;
            }
        }
        DirectoryNode newChild = new DirectoryNode();
        newChild.setName(name);
        newChild.setIsFile(true);
        newChild.setParent(cursor);
        cursor.addChild(newChild);
    }

    /**
     * Finds the nodes with given name and prints their paths
     * @param name
     *   name of the nodes to find
     */
    public void findNode(String name){
        if(!findNode(name,root)){
            System.out.println("No such file exists.");
        }
    }
    public boolean findNode(String name, DirectoryNode node){
        boolean found = false;
        if(node.getName().equals(name)){
            System.out.println(getPath(node));
            found = true;
        }
        if(node.getLeft()!=null){
            if(findNode(name,node.getLeft())){
                found = true;
            }
        }
        if(node.getMiddle()!=null){
            if(findNode(name,node.getMiddle())){
                found = true;
            }
        }
        if(node.getRight()!=null){
            if(findNode(name,node.getRight())){
                found = true;
            }
        }
        return found;
    }

    /**
     * Moves the cursor to its parent
     */
    public void moveUp(){
        if(cursor!=root){
            cursor = cursor.getParent();
        }
    }

    /**
     * Moves the cursor to given path
     * @param path
     *   where the cursor should move to
     */
    public void moveCursor(String path){
        if(!moveCursor(path,root)){
            System.out.println("No such path exists.");
        }
    }
    public boolean moveCursor(String path,DirectoryNode node){
        if(getPath(node).equals(path)){
            cursor = node;
            return true;
        }
        if(node.getLeft()!=null){
            if(moveCursor(path,node.getLeft())){
                return true;
            }
        }
        if(node.getMiddle()!=null){
            if(moveCursor(path,node.getMiddle())){
                return true;
            }
        }
        if(node.getRight()!=null){
            if(moveCursor(path,node.getRight())){
                return true;
            }
        }
        return false;
    }



}
