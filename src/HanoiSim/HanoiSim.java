package HanoiSim;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
        tower.diskSet = new ArrayList<HanoiDisk>(Arrays.asList(diskParam));
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

    void resetSimulation(HanoiTower a, HanoiTower b,  HanoiTower c){
        a.diskSet = (ArrayList<HanoiDisk>) c.diskSet.clone();
        c.diskSet.clear();
        b.diskSet.clear();
    }

    static void menuOptions(){
        System.out.println("Please select an option:");
        System.out.println("\t#1-Show Hanoi Objects");
        System.out.println("\t#2-Show Hanoi Disks");
        System.out.println("\t#3-Show Hanoi Towers");
        System.out.println("\t#4-Start Simulation");
        System.out.println("\t#5-Resize Disks");
        System.out.println("\t#6-Exit");
        System.out.print("Your Choice: ");
    }


    public static void main(String[] args){
        boolean guiRun = true;
        boolean subGui;
        int[] menuOptionsArray = {1,2,3,4,5,6};
        HanoiTower one = new HanoiTower();      //from
        HanoiTower two = new HanoiTower();      //aux
        HanoiTower three = new HanoiTower();    //to
        int simRun = 0;

        System.out.println("Welcome to the Tower's of Hanoi Simulation Program");

        while(guiRun==true){
            System.out.println("Please select the amount of disks:");
            Scanner scan = new Scanner(System.in);
            int diskAmt = scan.nextInt();
            subGui = true;

            while(subGui == true){
                menuOptions();

                int option = scan.nextInt();

                if(option == menuOptionsArray[0]){
                    //show hanoi objects


                }else if(option == menuOptionsArray[1]){
                    //show hanoi disks

                }else if(option == menuOptionsArray[2]){
                    //show hanoi towers

                }else if(option == menuOptionsArray[3]){
                    //start simulation
                    HanoiSim simOne = new HanoiSim(diskAmt);
                    HanoiDisk[] disks= simOne.createDisks();

                    HanoiSim.initializeDiskSet(one, disks);
                    simOne.towerSimulation(diskAmt, one, three, two);
                    three.displayTowerContents();
                    simOne.resetSimulation(one,two,three);

                }else if(option == menuOptionsArray[4]){
                    //resize disks

                    subGui = false;
                }else if(option == menuOptionsArray[5]){
                    //exit

                    subGui = false;
                    guiRun = false;
                }else{
                    System.out.println("Sorry, please select a valid option");
                }
            }

        }

//        HanoiSim simOne = new HanoiSim(2);
//        HanoiDisk[] disks= simOne.createDisks();
//
//        HanoiSim.initializeDiskSet(one, disks);
//
//        simOne.towerSimulation(one.diskSet.size(), one, three, two);
//        simOne.resetSimulation(one,two,three);

    }
}
