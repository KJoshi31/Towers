package IO_Utils;

import HanoiSim.HanoiDisk;
import HanoiSim.HanoiObject;
import HanoiSim.HanoiTower;

import java.util.List;

public class DisplayUtil<H extends HanoiObject> {

    private List<H> h;

    public void setH(H[] h) {
        this.h = List.of(h);
    }

    private List<H> getH(){
        return this.h;
    }

    public String displayTool(){
        return displayUtil(this.getH());
    }

    private String displayUtil(List<? extends HanoiObject> hList){

        StringBuilder displayString = new StringBuilder();
        Boolean allTowers = true;


        for (HanoiObject h: hList){
            if(h instanceof HanoiDisk){
                allTowers = false;
            }
        }

        if(allTowers == true){
            for (HanoiObject h: hList){
                //System.out.println("towers only");
                displayString.append( ((HanoiTower)h).displayTowerContents() );
            }
        }else{
            for(HanoiObject h: hList){
                //System.out.println("disks or disks and towers");
                if(h instanceof HanoiTower){
                    displayString.append(((HanoiTower) h).displayTowerContents());
                }else{
                    displayString.append(h.getDisplay());
                }

            }
        }



        return displayString.toString();
    }
}
