package HanoiSim;

public class HanoiDisk extends HanoiObject {
    private static int numberOfDisks;
    private int diskNumber;
    private static int startDiskNum=1;

    public HanoiDisk(){
        numberOfDisks++;
        this.diskNumber = startDiskNum++;

        HanoiObject.increaseHanoiObjects();
    }

    public int getDiskNumber(){
        return diskNumber;
    }

    public static void resetStartDiskNum(){
        startDiskNum = 1;
    }

    public static int getTotalNumberOfDisks(){
        return numberOfDisks;
    }

    @Override
    String display() {
        return("Disk #"+this.getDiskNumber()+"\n");
    }
}
