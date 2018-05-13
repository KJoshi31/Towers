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
        return this.towerName+" Tower";
    }


    int getDisksOnTower(){
        return diskSet.size();
    }

    static int getTowerCount(){
        return towerCount;
    }

    @Override
    void display(){
        System.out.print("Tower Name: "+this.getTowerName()+"\n");
    }

    public void displayTowerContents() {

        System.out.print("Tower Name: "+this.getTowerName()+"\n");
        for(HanoiDisk disk : this.diskSet){
            System.out.print("\t");
            disk.display();

        }
    }
}
