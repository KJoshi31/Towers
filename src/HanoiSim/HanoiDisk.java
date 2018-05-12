package HanoiSim;

class HanoiDisk extends HanoiObject {
    private static int numberOfDisks;
    private int diskNumber;

    public HanoiDisk(){
        numberOfDisks++;
        this.diskNumber = numberOfDisks;
        HanoiObject.increaseHanoiObjects();
    }

    int getDiskNumber(){
        return diskNumber;
    }

    static int getNumberOfDisks(){
        return numberOfDisks;
    }

    @Override
    void display() {
        System.out.println("Disk #"+this.getDiskNumber());
    }
}
