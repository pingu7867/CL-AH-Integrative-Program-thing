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
        long starttimeN = System.nanoTime();
        long starttimeM = System.currentTimeMillis();
        long endtimeN = 0;
        long endtimeM = 0;
        
        /*for (int x = 1; x <= iterations; x++) {
            computations += x - 1;
        }
        System.out.println(computations);*/
        
        double accumulation = 0;
        
        long numberoftests = 1000000;
        
        for (long i = 0; i < numberoftests; i++) {
            accumulation += Math.sin(i);
        }
        
        endtimeN = System.nanoTime();
        endtimeM = System.currentTimeMillis();
        
        double avgN = ((double)(endtimeN - starttimeN)) / (double)numberoftests;
        double avgM = ((double)(endtimeM - starttimeM)) / (double)numberoftests;
        
        System.out.println(accumulation);
        
        
        System.out.println("took " + avgN + " nanoseconds per operation on average");
        System.out.println(avgN * 4000 + " nanos for 4000 operations");
        
        System.out.println("took " + avgM + " milliseconds per operation on average");
        System.out.println(avgM * 4000 + " millis for 4000 operations");
        
        int i = 0;
        starttimeN = System.nanoTime();
        
        while (i < 1000000) {
            if((System.nanoTime() - System.nanoTime()) / 1000 == 1) {
                
            }
            i++;
        }
        endtimeN = System.nanoTime();
        endtimeN = System.nanoTime();
        System.out.println("taken average " + ((endtimeN - starttimeN) / 1000000) + "nanos with boolean check");
        
        i = 0;
        starttimeN = System.nanoTime();
        endtimeN = System.nanoTime();
        
        while (i < 1000000) {
            i++;
        }
        endtimeN = System.nanoTime();
        
        System.out.println("taken average " + ((endtimeN - starttimeN) / 1000000) + "nanos without boolean check");
        
        starttimeN = System.nanoTime();
        long j = 0;
        while ((System.nanoTime() - starttimeN) <= 5000000000l) {
        }
        endtimeN = System.nanoTime();
        System.out.println("for 5 seconds, " + j + "iterations averaging " + ((endtimeN - starttimeN) / 5000000000l) + "nanos per");
    }
}
