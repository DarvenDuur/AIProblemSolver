package two_players_game;

import AIproblemSolver.AbstractState;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mg
 */
public abstract class BotPLayer extends AbstractPlayer {
    private MinMax minMax;

    public BotPLayer( String NAME) {
        super(NAME);
    }
    @Override
    public AbstractState play() {
        throw new UnsupportedOperationException("Not supported yet."); 
        //apply the minMax
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
    
}
