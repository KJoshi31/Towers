package IO_Utils;

import HanoiSim.HanoiSim;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HanoiFileLogic {

    public static void exportSimConfig(List<Integer> configList, File filePath){

        Thread saveSimConfigThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try(ObjectOutputStream outfile = new ObjectOutputStream(
                        new FileOutputStream(filePath)
                );){
                    for (int i = 0; i<configList.size(); i++){
                        outfile.writeObject(configList.get(i));
                    }

                }catch (IOException ex){

                }
            }
        });

        saveSimConfigThread.start();

        //System.out.println("export done");

    }

    public static int[] importSimConfig(File filepath){
        //String fileName = "simConfig.dat";
        //String fullPath = fileName;

        ArrayList<Integer> configList = new ArrayList<>();

        try(ObjectInputStream infile= new ObjectInputStream(
                new FileInputStream(filepath));){

            while(true){
                configList.add((Integer) infile.readObject());
            }

        }catch (EOFException ex) {
            System.out.println("Finished Reading");

        }catch(IOException ex){
            System.out.println("IO Exception");

        }catch (ClassNotFoundException ex){

        }


        int[] configArray = new int[configList.size()];

        for(int j = 0; j< configArray.length; j++){
            configArray[j] =configList.get(j);
        }

        return configArray;
    }

    public static String readSteps(File fileParam){

        return readStepFile(fileParam).toString();
    }

    private static StringBuilder readStepFile(File fileParam){
        StringBuilder stepsString = new StringBuilder();

        try (DataInputStream infile = new DataInputStream(new FileInputStream(fileParam));)
        {

            while(true){
                stepsString.append(infile.readUTF());
            }

        }catch (EOFException ex){
            System.out.println("End of file");
        }catch (IOException ex){
            System.out.println("IO Exceoption");
        }
        return stepsString;
    }

    public static void saveSteps(String stepsParam, File filepath){

        saveStepFile(stepsParam, filepath);
    }

    private static void saveStepFile(String steps, File filepath){

        Thread saveStepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (DataOutputStream  outfile = new DataOutputStream (new FileOutputStream(filepath));)
                {
                    outfile.writeUTF(steps.trim());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveStepThread.start();
    }

    public String analyzeFileData(String fileStringParam) {
        Stream<String> wordArray = Stream.of(fileStringParam.split("\\s+"));
        int fileStepCounter = 0;

        List<String> moveList;

        Predicate<String> move = s -> s.equals("move") || s.equals("Move");

        moveList = wordArray.filter(move)
                .collect(Collectors.toList());

        fileStepCounter = moveList.size();

        int projectNumberOfDisks = getProjectedDisks(fileStepCounter);
        //System.out.println("Number of projected disks: "+projectNumberOfDisks);

        HanoiSim analysis = new HanoiSim(projectNumberOfDisks);
        analysis.runSimulation();

        //System.out.println(analysis.getSteps().equals(fileStringParam));

        String returnString;

        if(analysis.getSteps().equals(fileStringParam)) {
            returnString = ("Number of Disks: " + projectNumberOfDisks + "\n" +
                    "Step Iterations: " + fileStepCounter);
        }else{
            returnString = ("Mismatch with steps & disks in data\n")+
                    ("Please upload a file with valid steps/data");
        }

        return returnString;
    }

    private int getProjectedDisks(int projectedStepCount){
        int numDisks = 1;
        for(int disks =1; true; disks++){
            if((Math.pow(2,disks))-1==projectedStepCount){
                numDisks = disks;
                break;
            }
        }
        return numDisks;
    }

}
