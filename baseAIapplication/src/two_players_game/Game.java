/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two_players_game;

import AIproblemSolver.AbstractAction;
import AIproblemSolver.AbstractState;
import java.util.Random;

/**
 *
 * @author mg
 */
public abstract class Game {
    private AbstractPlayer currentPlayer;
    private AbstractState currentState;
    
    private AbstractPlayer playerOne;
    private AbstractPlayer playerTwo;
    
    

    public Game(AbstractState currentState, AbstractPlayer playerOne, AbstractPlayer playerTwo) {
        
        this.currentState = currentState;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.randomFirstPlayer();
    }
    
    public void switchCurrentPlayer(){
        this.currentPlayer=(this.currentPlayer.equals(playerOne))?
                this.playerTwo:
                this.playerOne;
    }
    
    public void randomFirstPlayer(){
        Random ra=new Random();
        this.currentPlayer=(ra.nextBoolean())?this.playerOne:this.playerTwo;
    }
    
    public abstract void display();

    public AbstractState getCurrentState() {
        return currentState;
    }
    
   public abstract boolean isPossibleMove(AbstractState state,AbstractAction action);
   
   public abstract void disableLastMove();
   
   public abstract boolean isOver();
   
   public abstract void applyMove(AbstractAction action);
  
   public void play(){
       
       while(!this.currentState.isFinal()){
           this.display();
           this.currentState=this.currentPlayer.play();
           this.switchCurrentPlayer();
           
       }
       //winner
   }
    
    
    
}
