/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two_players_game;

import AIproblemSolver.AbstractAction;
import AIproblemSolver.AbstractState;
import java.util.ArrayList;

/**
 *
 * @author mg
 */
public  abstract class MinMax implements Heuristic{
    
    private final double MIN=Double.POSITIVE_INFINITY;
    private final double MAX=Double.NEGATIVE_INFINITY;
    private ArrayList<AbstractAction> actions=new ArrayList();

    public MinMax(ArrayList actions) {
       
        this.actions=actions;
    }
    
    public void ai(Game game,int depth){
        double currentMax=this.MIN;
        AbstractAction bestAction=null;
        double max;
        for (AbstractAction action : actions) {
            if(game.isPossibleMove(game.getCurrentState(), action)){
                game.applyMove(action);
                max = max(game, depth-1);
                if(max>currentMax){
                    currentMax=max;
                    bestAction=action;
                }
                game.disableLastMove();
            }
        }
        //
        game.applyMove(bestAction);
        
    }
    public void min(){
        
    }
    public double max(Game game,int depth){
       Double max=this.MIN;
       if(game.isOver() ||depth==0){
           heuristic(game.getCurrentState());
       }
       else{
           
       }
        
    }
    @Override
    public abstract double heuristic(AbstractState state);{
        
    }


    
}
