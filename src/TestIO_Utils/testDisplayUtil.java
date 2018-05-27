package TestIO_Utils;

import HanoiSim.HanoiDisk;
import HanoiSim.HanoiObject;
import HanoiSim.HanoiTower;
import IO_Utils.DisplayUtil;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class testDisplayUtil {

    int low = 1;
    int high = 1000;
    Random r = new Random();
    int randomNum;

    private ArrayList<HanoiObject> setUp(String type){
        HanoiTower.resetLocalTowerCount();
        HanoiDisk.resetStartDiskNum();

        randomNum = r.nextInt(high - low) + low;
        ArrayList<HanoiObject> hObj = new ArrayList<>();

        if(type.equals("Disk")){
            for (int i = 0; i < randomNum; i++) {
                hObj.add(new HanoiDisk());
            }
            return hObj;
        }else if(type.equals("Tower")) {
            for (int i = 0; i < randomNum; i++) {
                hObj.add(new HanoiTower());
            }
            return hObj;
        }

        for(int i = 0; i<randomNum; i++){
            if(i%2 == 0){
                hObj.add(new HanoiTower());
            }else {
                hObj.add(new HanoiDisk());
            }
        }

        return hObj;
    }

    @Test
    public void testDisplayDisks(){
        DisplayUtil<HanoiObject> a = new DisplayUtil<>();

        HanoiDisk[] disks = setUp("Disk").toArray(new HanoiDisk[randomNum]);

        a.setH(disks);
        String displayString = a.displayTool();

        StringBuilder diskString = new StringBuilder();
        for (int i = 1; i<= randomNum; i++){
            diskString.append("Disk #"+i+"\n");
        }
        assertEquals(diskString.toString(), displayString);
        HanoiDisk.resetStartDiskNum();

    }

    @Test
    public void testDisplayTowers(){
        DisplayUtil<HanoiObject> a = new DisplayUtil<>();
        HanoiTower[] towers = setUp("Tower").toArray(new HanoiTower[randomNum]);
        String[] towerNames = {"Left","Middle","Right"};

        a.setH(towers);

        String displayString = a.displayTool();

        StringBuilder towerString = new StringBuilder();

        for(int i = 0; i< randomNum; i++){
            towerString.append("Tower Name: "+ towerNames[i%3]+" Tower"+"\n");
        }
        assertEquals(towerString.toString(), displayString);
    }

    @Test
    public void testDisplayDiskAndTowers(){


        DisplayUtil<HanoiObject> a = new DisplayUtil<>();
        HanoiObject[] diskTowers = setUp("Both").toArray(new HanoiObject[randomNum]);
        String[] towerNames = {"Left","Middle","Right"};

        a.setH(diskTowers);

        String displayString = a.displayTool();

        StringBuilder diskTowerString = new StringBuilder();

        int counter = 1;
        int counter2 = 0;
        for(int i = 0; i< randomNum; i++){
            if(i %2 ==0){
                diskTowerString.append("Tower Name: "+ towerNames[counter2%3]+" Tower"+"\n");
                counter2++;
            }else{
                diskTowerString.append("Disk #"+counter+"\n");
                counter++;
            }

        }
        assertEquals(diskTowerString.toString(), displayString);
        //System.out.println(displayString);

    }
}
