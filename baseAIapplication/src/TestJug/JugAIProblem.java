/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestJug;

/**
 *
 * @author darven
 */
public class JugAIProblem extends AIproblemSolver.AIProblem<JugState> {
    private final int BUCKET_A_MAX, BUCKET_B_MAX;
    private final int TARGET;

    public JugAIProblem(int bucketAMax, int bucketBMax, int target) {
        super(new JugState(bucketAMax, bucketBMax, target));
        this.BUCKET_A_MAX = bucketAMax;
        this.BUCKET_B_MAX = bucketBMax;
        this.TARGET = target;
        for (int i = 0; i < JugAction.POSSIBLE_ACTIONS.length; i ++){
            super.addAction(new JugAction(i));
        }
    }
    
    public JugAIProblem(int bucketAMax, int bucketBMax, int target, String addMode) {
        super(new JugState(bucketAMax, bucketBMax, target), addMode);
        this.BUCKET_A_MAX = bucketAMax;
        this.BUCKET_B_MAX = bucketBMax;
        this.TARGET = target;
        for (int i = 0; i < JugAction.POSSIBLE_ACTIONS.length; i ++){
            super.addAction(new JugAction(i));
        }
    }
    
    /**
     * @return the targetContent
     */
    public int getTargetContent() {
        return TARGET;
    }
    
    @Override
    public String toString(){
        return ("Jug problem:\n" + super.toString() + "\nTarget volume: " + this.TARGET);
    }
}
