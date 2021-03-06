
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package AIproblemSolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import static java.lang.Integer.max;

/**
 * AI problem solving class, use .solve to get the solution of the problem
 * @author darven
 * @param <TState>
 *      State defined for the problem, restrict actions and state inputs
 */
public class AIProblem<TState extends AbstractState> {

    // for debug use only, will display a lot more informations
    private static final boolean DEBUG = false;

    /*
     * SEARCH MODES
     * COST_SEARCH: search by cost, uses compareTo()
     * DEPTH_SEARCH
     * WIDTH_SEARCH
     * A_STAR_SEARCH: A* algorithm (https://en.wikipedia.org/wiki/A*_search_algorithm)
     */
    public static final String COST_SEARCH   = "costSearch";
    public static final String DEPTH_SEARCH  = "depth";
    public static final String WIDTH_SEARCH  = "width";
    public static final String A_STAR_SEARCH = "aStarSearch";

    // array of all the modes using speed over memory optimisation
    public static final String[] SPEED_OPTIMISATION_ARRAY = new String[] { WIDTH_SEARCH, COST_SEARCH, A_STAR_SEARCH };

    // actions: list of action
    private final LinkedList<AbstractAction<TState>> actions = new LinkedList<>();

    // INIT_STATE: initial state
    private final TState INIT_STATE;

    // mode used to add to the tree
    private final String ADD_MODE;

    // determines usage of speed over memory optimisation
    private final boolean SPEED_OPTIMISATION;

    /**
     * CONSTRUCTOR
     * @param initState
     *      initial state
     */
    public AIProblem(TState initState) {
        this.ADD_MODE           = "";
        this.INIT_STATE         = initState;
        this.SPEED_OPTIMISATION = (Arrays.binarySearch(SPEED_OPTIMISATION_ARRAY,
                                                       0,
                                                       SPEED_OPTIMISATION_ARRAY.length,
                                                       this.ADD_MODE) < 0);
    }

    /**
     * CONSTRUCTOR
     * @param actions
     *      actions available to solve the problem
     * @param initState
     *      initial state
     */
    public AIProblem(LinkedList<AbstractAction<TState>> actions, TState initState) {
        this.ADD_MODE = "default";
        this.actions.addAll(actions);
        this.INIT_STATE         = initState;
        this.SPEED_OPTIMISATION = (Arrays.binarySearch(SPEED_OPTIMISATION_ARRAY,
                                                       0,
                                                       SPEED_OPTIMISATION_ARRAY.length,
                                                       this.ADD_MODE) < 0);
    }

    /**
     * CONSTRUCTOR
     * @param initState
     *      initial state
     * @param addMode
     *      how to add the new states to the search tree
     */
    public AIProblem(TState initState, String addMode) {
        this.ADD_MODE           = addMode;
        this.INIT_STATE         = initState;
        this.SPEED_OPTIMISATION = (Arrays.binarySearch(SPEED_OPTIMISATION_ARRAY,
                                                       0,
                                                       SPEED_OPTIMISATION_ARRAY.length,
                                                       this.ADD_MODE) < 0);
    }

    /**
     * CONSTRUCTOR
     * @param actions
     *      actions available to solve the problem
     * @param initState
     *      initial state
     * @param addMode
     *      how to add the new states to the search tree
     */
    public AIProblem(LinkedList<AbstractAction<TState>> actions, TState initState, String addMode) {
        this.ADD_MODE = addMode;
        this.actions.addAll(actions);
        this.INIT_STATE         = initState;
        this.SPEED_OPTIMISATION = (Arrays.binarySearch(SPEED_OPTIMISATION_ARRAY,
                                                       0,
                                                       SPEED_OPTIMISATION_ARRAY.length,
                                                       this.ADD_MODE) < 0);
    }

    protected void addAction(AbstractAction<TState> action) {
        this.actions.add(action);
    }

    protected void addAllAction(LinkedList<AbstractAction<TState>> actions) {
        this.actions.addAll(actions);
    }

    /**
     * modifie l'arbre en live
     * @param tree
     *      linearised tree to which to add the new path
     * @param path
     *      path to add to the tree
     */
    private void addToTree(LinkedList<LinkedPath> tree, LinkedPath path) {
        switch (ADD_MODE) {
        default :
        case DEPTH_SEARCH :
            tree.addFirst(path);

            break;

        case WIDTH_SEARCH :
            tree.addLast(path);

            break;

        case COST_SEARCH :
            tree.add(path);
            Collections.sort(tree);

            break;

        case A_STAR_SEARCH :
            throw new UnsupportedOperationException("A* search not supported yet!");

        // break;
        }
    }

    /**
     * apply inputed action to the inputed state
     * @param state
     *      state to apply the action to
     * @param action
     *      action to apply to the state
     * @return
     *      node with the new state and the action used to reach it (inputed
     *      action), null if action not in the actions available in the problem
     */
    public Node applyAction(TState state, AbstractAction<TState> action) {
        boolean isInActions = false;

        for (AbstractAction tempAction : actions) {
            isInActions = isInActions || tempAction.equals(action);
        }

        if (isInActions) {
            return action.apply(state);
        } else {
            return null;
        }
    }

    /**
     * try to solve the problem with available actions
     * @return
     *      path used to reach the gaol state, null if no path existing to the
     *      goal
     * @throws java.lang.CloneNotSupportedException
     */
    public LinkedPath solve() {

        // number of children generated
        int childrenGenerated = 0;

        // length and max length of linearized tree
        int treeLength    = 0,
            maxTreeLenght = 0;

        // will contain solution path
        LinkedPath path;

        if (SPEED_OPTIMISATION) {
            path = new LinkedPath();
        } else {
            path = new LinkedIntPath();
        }

        path.setCurrent(new Node(null, INIT_STATE));

        // linearized version of the tree
        LinkedList<LinkedPath> tree = new LinkedList<>();

        tree.add(path);

        // exploredStates: set of already explored states
        HashSet<TState> exploredStates = new HashSet<>();
        boolean         found          = false;

        while (!found &&!tree.isEmpty()) {
            path          = tree.pop();
            treeLength    = tree.size();
            maxTreeLenght = max(maxTreeLenght, treeLength);

            if (DEBUG) {
                System.out.println(path);
            }

            TState currentState = (TState) path.getCurrent().getState();

            if (currentState.isFinal()) {
                found = true;

                // if state is not already explored
            } else if (exploredStates.add(currentState)) {
                for (AbstractAction action : actions) {
                    Node node = applyAction((TState) path.getCurrent().getState(), action);

                    childrenGenerated++;

                    LinkedPath tempPath;

                    if (SPEED_OPTIMISATION) {
                        tempPath = new LinkedPath(path);
                    } else {
                        tempPath = new LinkedIntPath(path);
                    }

                    tempPath.setCurrent(node);
                    this.addToTree(tree, tempPath);
                }
            }
        }

        if (DEBUG) {
            System.out.println("Number of chidren generated: " + childrenGenerated);
            System.out.println("Size of tree: " + treeLength);
            System.out.println("Max size of tree: " + maxTreeLenght);
        }

        if (tree.isEmpty()) {
            return null;
        } else {
            return path;
        }
    }

    @Override
    public String toString() {
        return ("AI problem:\n" + String.format(" * Actions: %s\n",
                                                this.actions.toString()) + String.format(" * Initial state: %s\n",
                                                                                         this.INIT_STATE.toString()) + String.format(
                                                                                             " * Search addition mode: %s",
                                                                                             this.ADD_MODE));
    }
}
