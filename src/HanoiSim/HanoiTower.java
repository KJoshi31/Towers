package HanoiSim;

import java.util.ArrayList;
import java.util.Arrays;

class HanoiTower extends HanoiObject{
    private String[] towerPosition = {"Left","Middle","Right"};
    private static int towerCount;
    private String towerName;
    private int disksOnTower;
    ArrayList<HanoiDisk> diskSet = new ArrayList<HanoiDisk>();

    public HanoiTower(){
        if(towerCount <= HanoiObject.TOTALNUMTOWER){
            towerName = towerPosition[towerCount];
            towerCount++;
            HanoiObject.increaseHanoiObjects();
        }else{
            /*
            * fill with exception
            * stating that only 3 hanoi tower objects
            * can exist at one time
            * **/
        }
    }

    String getTowerName(){
        return this.towerName;
    }


    int getDisksOnTower(){
        return diskSet.size();
    }

    static int getTowerCount(){
        return towerCount;
    }

    @Override
    public void display() {
        System.out.println("Tower Name: "+this.getTowerName());
    }
}
