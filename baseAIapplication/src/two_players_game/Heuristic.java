/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two_players_game;

import AIproblemSolver.AbstractState;

/**
 *
 * @author mg
 */
public interface Heuristic {
    public double heuristic(AbstractState state);
}
