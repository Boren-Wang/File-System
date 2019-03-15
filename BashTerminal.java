/**
 * Represents the BashTerminal
 * @author Boren Wang
 *   Email: Boren.Wang@stonybrook.edu
 *   SBU ID: 111385010
 */
import java.util.Scanner;
public class BashTerminal {
    /**
     * Main method of the BashTerminal
     * @param args
     *   none
     */
    public static void main(String [] args){
        DirectoryTree tree = new DirectoryTree();
        while(true){
            try{
                System.out.print("borwang@host: $ ");
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                if(input.equals("exit")){
                    System.out.println("Program terminating normally...");
                    break;
                }else if(input.equals("pwd")){
                    System.out.println(tree.presentWorkingDirectory());
                    continue;
                }else if(input.equals("ls")){
                    System.out.println(tree.listDirectory());
                    continue;
                }else if(input.equals("ls -R")){
                    tree.printDirectoryTree();
                    continue;
                }else if(input.equals("cd /")){
                    tree.resetCursor();
                    continue;
                }else if(input.equals("cd ...")){
                    tree.moveUp();
                    continue;
                }else if(input.equals("ls")){
                    tree.listDirectory();
                    continue;
                }
                if(input.length()>=3) {
                    if (input.substring(0, 3).equals("cd ")) {
                        boolean isPath = false;
                        for(int i=0; i<input.substring(3).length();i++){
                            if(input.substring(3).charAt(i)=='/'){ // cd {path}
                                String path = input.substring(3);
                                tree.moveCursor(path);
                                isPath = true;
                                break;
                            }
                        }
                        if(isPath){
                            continue;
                        }else{ // cd {name}
                            String name = input.substring(3);
                            tree.changeDirectory(name);
                            continue;
                        }

                    }
                }
                if(input.length()>=5){
                    if(input.substring(0,5).equals("find ")){ // find {name}
                        String name = input.substring(5);
                        tree.findNode(name);
                        continue;
                    }
                }
                if(input.length()>=6) {
                    if (input.substring(0, 6).equals("mkdir ")) {
                        String name = input.substring(6);
                        tree.makeDirectory(name);
                        continue;
                    } else if (input.substring(0, 6).equals("touch ")) {
                        String name = input.substring(6);
                        tree.makeFile(name);
                        continue;
                    }
                }
                System.out.println("Invalid input.\nBe careful with spaces.");
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
        }

    }
}
