
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two_players_game;

import java.util.ArrayList;

import AIproblemSolver.AbstractAction;
import AIproblemSolver.AbstractState;

/**
 *
 * @author mg
 */
public abstract class MinMax implements Heuristic {
    private ArrayList<AbstractAction> actions = new ArrayList();// list of all actions
    private final double              MIN;                      // min possible value
    private final double              MAX;                      // max possible value

    /**
     * constructor with min and max at infinity and -infinty value 
     * @param actions 
     */
    public MinMax(ArrayList actions) {

        this.actions = actions;
        MIN          = Double.POSITIVE_INFINITY;
        MAX          = Double.NEGATIVE_INFINITY;
    }

    /**
     * constructor with min and max define with parameters
     * @param actions
     * @param max
     * @param min 
     */
    public MinMax(ArrayList actions, double max, double min) {
        this.actions = actions;
        MIN          = min;
        MAX          = max;
    }

    /**
     * apply the best action according to the heuristic method
     * @param game
     * @param depth
     */
    public void ai(Game game, int depth) {

        // init
        double         currentMax = this.MIN;
        AbstractAction bestAction = null;
        double         max;

        // try to apply all the actions
        for (AbstractAction action : actions) {

            // case where it is possible
            if (game.isPossibleMove(game.getCurrentState(), action)) {

                game.applyMove(action);

                max = max(game, depth - 1);

                // case where max in this branch is superior
                if (max > currentMax) {
                    currentMax = max;
                    bestAction = action;
                }

                game.disableLastMove();
            }
        }

        game.applyMove(bestAction);
    }

    /**
     * find the max value
     * @param game
     * @param depth
     * @return the max value
     */
    public double max(Game game, int depth) {

        // init
        Double max;
        double currentMax = this.MIN;

        // case where the game is over or the node is in the max depth
        if (game.isOver() || (depth == 0)) {
            return heuristic(game.getCurrentState());
        }
        else {

            // try to apply all the actions
            for (AbstractAction action : actions) {

                // case where it is possible

                if (game.isPossibleMove(game.getCurrentState(), action)) {
                    game.applyMove(action);

                    max = min(game, depth - 1);

                    // case where max in this branch is superior
                    if (max > currentMax) {
                        currentMax = max;
                    }

                    game.disableLastMove();
                }
            }

            return currentMax;
        }
    }

    /**
     * find the min value
     * @param game
     * @param depth
     * @return the min value
     */
    public double min(Game game, int depth) {

        // init
        Double min;
        double currentMin = this.MAX;

        // case where the game is over or the node is in the min depth
        if (game.isOver() || (depth == 0)) {
            return heuristic(game.getCurrentState());
        }
        else {

            // try to apply all the actions
            for (AbstractAction action : actions) {

                // case where it is possible

                if (game.isPossibleMove(game.getCurrentState(), action)) {
                    game.applyMove(action);

                    min = max(game, depth - 1);

                    // case where min in this branch is superior
                    if (min < currentMin) {
                        currentMin = min;
                    }

                    game.disableLastMove();
                }
            }

            return currentMin;
        }

    }
}


//~ Formatted by Jindent --- http://www.jindent.com
