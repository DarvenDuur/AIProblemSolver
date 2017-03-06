/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIproblemSolver;

import java.util.ArrayList;

/**
 * Path listing all states and actions used to reach the current state
 * @author darven
 */
public class LinkedPath implements Cloneable, Comparable<LinkedPath> {
    //parent path, containing all previous nodes
    protected final LinkedPath PARENT;
    
    //current node
    protected Node current;

    /**
     * CONSTRUCTOR
     *      create a path without parent (this.PARENT = null)
     */
    public LinkedPath() {
        this.PARENT = null;
        this.current = null;
    }
    
    /**
     * CONSTRUCTOR
     *      if parameter is missing, will create a path without parent 
     *      (this.PARENT = null)
     * @param parent
     *      path that will be used as parent
     */
    public LinkedPath(LinkedPath parent) {
        this.PARENT = parent;
        this.current = null;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        LinkedPath clone = (LinkedPath) super.clone();
        return clone;
    }
    
    /**
     * @return 
     *      current state (first state of the path)
     */
    public Node getCurrent(){
        return this.current;
    }
    
    /**
     * returns the size of the path
     * @return 
     *      size of the path (number of nodes)
     */
    public int size(){
        int size = this.current == null ? 0 : 1;
        if (this.PARENT == null){
            return size;
        } else {
            return this.PARENT.size() + size;
        }
    }
    
    @Override
    public String toString(){
        return "Path length (action number): " + (this.size()) + "\nHead: " +
                this.getCurrent().toString();
    }
    
    public String toString(boolean extension){
        if (!extension){
            return this.toString();
        }else{
            //text that will be returned
            String text = "";
            
            //nodes of the path in form of list
            ArrayList<Node> listedPath = this.toList();
            
            //index that will be displayed before the node
            //max value: listedPath.size() - 1
            //used to dispaly the number of actions used
            int i = 0;
            
            //display all nodes
            for (Node node : listedPath){
                text += String.format("\nNode %s: %s", i++, node.toString());
            }
            
            return "Path length (action number): " + (i) + text;
        }
    }

    @Override
    public int compareTo(LinkedPath path) {
        if (path instanceof LinkedPath){
            return this.getCurrent().compareTo(((LinkedPath) path).getCurrent());
        }else{
            return 0;
        }
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Node current) {
        this.current = current;
    }

    /**
     * returns list of all nodes, using a recursive browsing of the path
     * @return 
     *      list of nodes, first is ultimate ancestor, last is current node
     */
    public ArrayList<Node> toList() {
        //create a list of nodes composed of only the current one
        if (this.PARENT == null){
            ArrayList<Node> list = new ArrayList<>();
            list.add(this.current);
            return list;
            
        //get the list of previous nodes, and add current one
        } else {
            ArrayList<Node> list = this.PARENT.toList();
            list.add(this.current);
            return list;
        }
    }
}
