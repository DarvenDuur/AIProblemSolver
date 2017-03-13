/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two_players_game;

import AIproblemSolver.AbstractState;

/**
 * contract to define the evaluation method , the heuristic.
 * @author mg
 */
public interface Heuristic {
    
    /**
     * evaluation method
     * @param state
     * @return a double who definie the value of a state
     */
    public double heuristic(AbstractState state);
}
