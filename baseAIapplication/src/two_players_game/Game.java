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
 * describe an abstract game
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
    
   /**
     * switch the current player with the other.
     */
   public void switchCurrentPlayer(){
        this.currentPlayer=(this.currentPlayer.equals(playerOne))?
                this.playerTwo:
                this.playerOne;
    }
    
   /**
     * define the player who begin
     */
   public void randomFirstPlayer(){
        Random ra=new Random();
        this.currentPlayer=(ra.nextBoolean())?this.playerOne:this.playerTwo;
    }
    
   /**
     * to display the game
     */
   public abstract void display();
  
   /**
    * return true if it is a possible move
    * @param state
    * @param action
    * @return a boolean, true if the move is possible
    */
   public abstract boolean isPossibleMove(AbstractState state,AbstractAction action);
   
   /**
    * cancel the last move
    */
   public abstract void disableLastMove();
   
   /**
    * return true if the game is over
    * @return 
    */
   public abstract boolean isOver();
   
   /**
    * apply an action
    * @param action 
    */
   public abstract void applyMove(AbstractAction action);
   
   /**
    * progess of a party
    */
   public void play(){
       
       while(!this.currentState.isFinal()){
           this.display();
           this.currentState=this.currentPlayer.play();
           this.switchCurrentPlayer();
           
       }
       //winner
   }

    /**
     * @return the currentState
     */
    public AbstractState getCurrentState() {
        return currentState;
    }
    
    
    
}
