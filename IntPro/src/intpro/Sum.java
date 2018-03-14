/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

/**
 *
 * @author Cédric
 */
public class Sum {
    public static void main(String[] args) {
        int computations = 0;
        int iterations = 75;
        
        for (int x = 1; x <= iterations; x++) {
            computations += x - 1;
        }
        System.out.println(computations);
    }
}
