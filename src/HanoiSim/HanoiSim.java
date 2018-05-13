package HanoiSim;

import java.util.ArrayList;
import java.util.Arrays;

public class HanoiSim {
    private int diskCount;
    private static boolean simInitialized;

    public HanoiSim(){
        //throw exception that diskCount has to be passed in
    }

    public HanoiSim(int diskAmt){
        if(diskAmt > 0){
            this.diskCount = diskAmt;
        }else{
            /*
            throw exception that diskAmt has to be greater than 0
             */
        }
    }

    public HanoiDisk[] createDisks(){
        HanoiDisk[] set = new HanoiDisk[diskCount];
        for(int i = 0; i<diskCount; i++){
            set[i] = new HanoiDisk();
        }
        return set;
    }

    static void initializeDiskSet(HanoiTower tower, HanoiDisk...diskParam){

        if(simInitialized == false){
            tower.diskSet = new ArrayList<HanoiDisk>(Arrays.asList(diskParam));
            //display from disk set
//            for(HanoiDisk a : tower.diskSet){
//                a.display();
//            }
            simInitialized = true;
        }else{
            /*
             *throw exception that this can only happen once
             * **/
        }

    }

    void transferDisk(HanoiTower a, HanoiTower b){
        int lastElementA = a.diskSet.size()-1;
        b.diskSet.add(a.diskSet.remove(lastElementA));

    }

    void towerSimulation(int diskNum, HanoiTower fromTower, HanoiTower toTower, HanoiTower auxTower){
        if(diskNum ==1){
            System.out.print("Move ");
            System.out.print("Disk #1 ");
            System.out.print("from "+fromTower.getTowerName());
            System.out.print(" to "+toTower.getTowerName()+"\n");

            transferDisk(fromTower,toTower);

            return;
        }
        towerSimulation(diskNum-1, fromTower, auxTower, toTower);
        System.out.print("Move Disk #"+diskNum+" ");

        System.out.print("from "+fromTower.getTowerName());

        System.out.print(" to "+toTower.getTowerName()+"\n");

        transferDisk(fromTower,toTower);

        towerSimulation(diskNum-1, auxTower, toTower, fromTower);

    }


    public static void main(String[] args){
        HanoiTower one = new HanoiTower();      //from
        HanoiTower two = new HanoiTower();      //aux
        HanoiTower three = new HanoiTower();    //to

        HanoiSim simOne = new HanoiSim(2);
        HanoiDisk[] disks= simOne.createDisks();

        HanoiSim.initializeDiskSet(one, disks);

        simOne.towerSimulation(one.diskSet.size(), one, three, two);

    }
}
