/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseaiapplication;

import TestJug.JugAIProblem;

/**
 *
 * @author darven & mgk
 */
public class BaseAIapplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        JugAIProblem pb = new JugAIProblem(3, 5, 4, AIproblemSolver.AIProblem.COST_SEARCH);
        
        System.out.println(pb.toString());
        System.out.println(pb.solve().toString(true));
    }
    
}
