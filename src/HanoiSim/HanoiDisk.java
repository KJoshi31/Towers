package HanoiSim;

class HanoiDisk extends HanoiObject {
    private static int numberOfDisks;
    private int diskNumber;
    private static int startDiskNum=1;

    public HanoiDisk(){
        numberOfDisks++;
        this.diskNumber = startDiskNum++;

        HanoiObject.increaseHanoiObjects();
    }

    int getDiskNumber(){
        return diskNumber;
    }

    static void resetStartDiskNum(){
        startDiskNum = 1;
    }

    static int getTotalNumberOfDisks(){
        return numberOfDisks;
    }

    @Override
    void display() {
        System.out.print("Disk #"+this.getDiskNumber()+"\n");
    }
}
