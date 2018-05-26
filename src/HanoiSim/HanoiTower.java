package HanoiSim;

import java.util.ArrayList;

public class HanoiTower extends HanoiObject{
    private String[] towerPosition = {"Left","Middle","Right"};
    private static int localTowerCount;
    private String towerName;
    private static int globalTowerCount;
    ArrayList<HanoiDisk> diskSet = new ArrayList<HanoiDisk>();
    private int disksOnTower = diskSet.size();

    public HanoiTower(){
        if(localTowerCount ==3){
            localTowerCount = 0;
        }

            towerName = towerPosition[localTowerCount];
            localTowerCount++;
            globalTowerCount++;
            HanoiObject.increaseHanoiObjects();

            /*
            * fill with exception
            * stating that only 3 hanoi tower objects
            * can exist at one time for each simulation.
            * will have to do some logic with number of
            * simulation objects created and tower objects
            * **/

    }

    public String getTowerName(){
        return this.towerName+" Tower";
    }

    public static int getLocalTowerCount(){
        return localTowerCount;
    }

    public static int getGlobalTowerCount() { return globalTowerCount; }

    public static void resetGlobalTowerCount() {globalTowerCount = 0;}

    public static void resetLocalTowerCount() {localTowerCount = 0;}

    public int getDisksOnTower(){return disksOnTower;}

    @Override
    String display(){
        return ("Tower Name: "+this.getTowerName()+"\n");
    }

    public String displayTowerContents() {
        StringBuilder contents = new StringBuilder();
        contents.append("Tower Name: "+this.getTowerName()+"\n");
        for(HanoiDisk disk : this.diskSet){
            contents.append("\t");
            contents.append(disk.display());

        }
        return contents.toString();
    }
}
