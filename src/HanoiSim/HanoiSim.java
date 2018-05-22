package HanoiSim;

import java.util.*;

public class HanoiSim {
    private int diskParam;
    private StringBuilder steps = new StringBuilder();
    private ArrayList<HanoiTower> towers = new ArrayList<HanoiTower>();
    private HanoiDisk[] diskArray = {};

    public HanoiSim(int diskAmt){
        if(diskAmt > 0){
            this.diskParam = diskAmt;
            towers.add(new HanoiTower());
            towers.add(new HanoiTower());
            towers.add(new HanoiTower());

            diskArray = createDisks();

            initializeDiskSet(towers.get(0),diskArray);

        }else{
            /*
            throw exception that diskAmt has to be greater than 0
             */
        }
    }

    private HanoiDisk[] createDisks(){
        HanoiDisk[] set = new HanoiDisk[diskParam];
        for(int i = 0; i< diskParam; i++){
            set[i] = new HanoiDisk();
        }
        HanoiDisk.resetStartDiskNum();
        return set;
    }

    private static void initializeDiskSet(HanoiTower tower, HanoiDisk...diskParam){
        tower.diskSet = new ArrayList<HanoiDisk>(Arrays.asList(diskParam));
    }

    private void transferDisk(HanoiTower a, HanoiTower b){
        int lastElementA = a.diskSet.size()-1;
        b.diskSet.add(a.diskSet.remove(lastElementA));

    }

    private void towerSimulation(int diskNum, HanoiTower fromTower, HanoiTower toTower, HanoiTower auxTower){
        if(diskNum ==1){
            steps.append("Move ");
            //System.out.print("Move ");
            steps.append("Disk #1 ");
            //System.out.print("Disk #1 ");
            steps.append("from "+fromTower.getTowerName());
            //System.out.print("from "+fromTower.getTowerName());
            steps.append(" to "+toTower.getTowerName()+"\n");
            //System.out.print(" to "+toTower.getTowerName()+"\n");

            transferDisk(fromTower,toTower);

            return;
        }
        towerSimulation(diskNum-1, fromTower, auxTower, toTower);
        steps.append("Move Disk #"+diskNum+" ");
        //System.out.print("Move Disk #"+diskNum+" ");
        steps.append("from "+fromTower.getTowerName());
        //System.out.print("from "+fromTower.getTowerName());
        steps.append(" to "+toTower.getTowerName()+"\n");
        //System.out.print(" to "+toTower.getTowerName()+"\n");

        transferDisk(fromTower,toTower);

        towerSimulation(diskNum-1, auxTower, toTower, fromTower);
    }

    public void runSimulation(){
        this.towerSimulation(this.diskParam,
                       towers.get(0),
                       towers.get(2),
                       towers.get(1));
        steps.setLength(steps.length()-1);
    }

    public String getSteps(){
        return this.steps.toString();
    }

    public void displaySteps(){
        System.out.println(this.getSteps());
    }

    private void resetSimulation(HanoiTower a, HanoiTower b,  HanoiTower c){
        a.diskSet = (ArrayList<HanoiDisk>) c.diskSet.clone();
        c.diskSet.clear();
        b.diskSet.clear();
    }

    public void reset(){
        this.resetSimulation(this.towers.get(0),this.towers.get(1),this.towers.get(2));
    }

    public void displayDisks(){
        this.display(diskArray);
    }

    public void displayTowers(){
        this.display(towers.get(0),towers.get(1),towers.get(2));
    }

    private void display(HanoiObject...hObj){
        boolean typeCheck = false;

        for(int i = 0; i<hObj.length; i++){
            if(hObj[i] instanceof HanoiTower){
                typeCheck = true;
                break;
            }
        }

        if(typeCheck ==false){
            for(HanoiObject d: hObj){
                d.display();
            }
        }else{
            for(HanoiObject t: hObj){
                ((HanoiTower)t).displayTowerContents();
            }
        }

    }

    public void analyzeFileData(String fileStringParam) {
        String[] wordArray = fileStringParam.split("\\s+");
        int fileStepCounter = 0;

        for(String w: wordArray){
            if(w.equals("Move")||w.equals("move")){
                fileStepCounter++;
            }
        }

        int projectNumberOfDisks = getProjectedDisks(fileStepCounter);
        //System.out.println("Number of projected disks: "+projectNumberOfDisks);

        HanoiSim analysis = new HanoiSim(projectNumberOfDisks);
        analysis.runSimulation();

        //System.out.println(analysis.getSteps().equals(fileStringParam));

        if(analysis.getSteps().equals(fileStringParam)) {
            System.out.print("Number of Disks: " + projectNumberOfDisks + "\n" +
                    "Step Iterations: " + fileStepCounter);
        }else{
            System.out.println("Mismatch with steps & disks in data");
            System.out.println("Please upload a file with valid steps/data");
        }

    }

    private int getProjectedDisks(int projectedStepCount){
        if(projectedStepCount<=3){
            return (int) Math.log(projectedStepCount)+1;
        }
        return (int) Math.ceil(Math.log(projectedStepCount))+1;
    }


}
