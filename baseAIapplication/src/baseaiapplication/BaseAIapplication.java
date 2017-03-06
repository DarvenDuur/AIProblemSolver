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
    public static void main(String[] args){
        JugAIProblem pb = new JugAIProblem(3, 5, 4, AIproblemSolver.AIProblem.WIDTH_SEARCH);
        
        System.out.println(pb.toString());
        try{
            System.out.println(pb.solve().toString(true));
        }catch (NullPointerException e){
            System.out.println("No solution found");
        }
    }
    
}
