package IO_Utils;

import HanoiSim.HanoiSim;

public class MethodTimer {

    public static long timeSimulation(HanoiSim sim){

        long startTime = System.nanoTime();

        sim.runSimulation();

        long endTime = System.nanoTime();

        long calc = endTime - startTime;

        return calc/1000000;
    }

    public static String timeSimulation(HanoiSim sim, char a){

        long startTime = System.nanoTime();

        sim.runSimulation();

        long endTime = System.nanoTime();

        long calc = endTime - startTime;

        return "Elapsed time in milliseconds: "+ calc/1000000;
    }

}
