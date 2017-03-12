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
public abstract class AbstractPlayer {
    protected String NAME;

    public AbstractPlayer(String NAME) {
        this.NAME = NAME;
    }

    public String getName() {
        return NAME;
    }
    public abstract AbstractState play();
    
    
    @Override
    public abstract String toString();
    
    
   
}
