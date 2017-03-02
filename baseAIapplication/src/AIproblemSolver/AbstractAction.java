/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIproblemSolver;

/**
 * Action used to change a state in a AI solving problem
 * @author darven
 * @param <TState>
 *      State defined for the problem, to which this action will be applied
 */
public abstract class AbstractAction <TState extends AbstractState>{

    /**
     * apply this action designed by code to the inputed state, if this action 
     *      can not be applied, doesn't change the state
     * @param state
     *      state to which apply this action
     * @return 
     *      node with this action applied and the new state, which is a copy of
     *      the old state to which this action is applied
     */
    abstract public Node apply(TState state);
    
    @Override
    abstract public boolean equals(Object obj);
    
    @Override
    abstract public String toString();
}
