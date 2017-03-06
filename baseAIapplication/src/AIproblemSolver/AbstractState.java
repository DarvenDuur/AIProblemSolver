
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package AIproblemSolver;

/**
 * State used in a AI solving problem
 *      .isFinal() will be used to determine if the state fits the goal
 *      conditions of the algorythm
 * @author darven
 */
public abstract class AbstractState implements Comparable<AbstractState> {

    /**
     * compare state with other state, in terms of cost
     * @param state
     *      state to compare to
     * @return
     *      0 if state and this have equal cost, or can not be compared;
     *      negative if this costs more than input, positive if this costs less
     */
    @Override
    public int compareTo(AbstractState state) {
        return 0;
    }

    @Override
    abstract public boolean equals(Object o);

    @Override
    abstract public String toString();

    /**
     * returns true if the state is af goal state of the problem
     * @return
     *      true if state is a final state
     */
    abstract public boolean isFinal();
}
