
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package AIproblemSolver;

/**
 *
 * @author darven
 */
public class LinkedIntPath extends LinkedPath {

    /*
     * store the length of PARENT in a variable, allowing recovery of lenght
     * without having to rebrowse the whole path
     */
    protected final int PARENT_SIZE;

    /**
     * CONSTRUCTOR
     *      create a path without parent (this.PARENT = null)
     */
    public LinkedIntPath() {
        super();
        PARENT_SIZE = 0;
    }

    /**
     * CONSTRUCTOR
     *      if parameter is missing, will create a path without parent
     *      (this.PARENT = null)
     * @param parent
     *      path that will be used as parent
     */
    public LinkedIntPath(LinkedPath parent) {
        super(parent);
        PARENT_SIZE = this.PARENT.size() + 1;
    }

    /**
     * returns the size of the path
     * @return
     *      size of the path (number of nodes)
     */
    @Override
    public int size() {
        int size = (this.current == null)
                   ? 0
                   : 1;

        return PARENT_SIZE + size;
    }
}
