package IO_Utils;

import HanoiSim.HanoiSim;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class HanoiFileLogic {

    public static String readSteps(){
        Scanner readInput = new Scanner(System.in);
        System.out.println("Please Enter the directory where the file is located:");
        String directory = readInput.nextLine();
        System.out.println("Please Enter the file name:");
        String fileName = readInput.nextLine();

        return readStepFile(fileName,directory).toString().trim();
    }

    private static StringBuilder readStepFile(String fileNameParam, String directoryParam){
        String fullPath;
        StringBuilder stepsFromFile = new StringBuilder();

        if(directoryParam.length() == 0 || directoryParam.equals("")){
            fullPath = fileNameParam;
        }else{
            if(directoryParam.endsWith("\\")){
                fullPath = directoryParam+fileNameParam;
            }else{
                fullPath = directoryParam+"\\"+fileNameParam;
            }
        }

        Scanner stepsFile = null;

        try{
            stepsFile =  new Scanner(new File(fullPath));
        } catch (FileNotFoundException ex){
            System.out.println("File could not be opened!");
            System.out.println("Directory+File Name combo invalid.");
        }


            while(stepsFile.hasNext()){
                stepsFromFile.append(stepsFile.nextLine().trim()+"\n");
            }
            stepsFromFile.setLength(stepsFromFile.length()-1);

        stepsFile.close();
        return stepsFromFile;
    }

    public static void saveSteps(String stepsParam){
        Scanner saveInput = new Scanner(System.in);
        System.out.println("Please Enter the directory to save the file:");
        String directory = saveInput.nextLine();
        System.out.println("Please Enter the file name:");
        String fileName = saveInput.nextLine();

        saveStepFile(fileName,directory,stepsParam);
    }

    private static void saveStepFile(String fileNameParam, String directoryParam, String steps){

        Formatter output = null;

        String fullPath;

        if(directoryParam.length() == 0 || directoryParam.equals("")){
            fullPath = fileNameParam;
        }else{
            if(directoryParam.endsWith("\\")){
                fullPath = directoryParam+fileNameParam;
            }else{
                fullPath = directoryParam+"\\"+fileNameParam;
            }
        }

        try{
            output = new Formatter(fullPath);
        } catch (FileNotFoundException ex){
            System.out.println("Steps could not be saved!");
            System.out.println("Directory+File Name combo invalid.");
        }

        output.format(steps);
        output.close();
        System.out.println("Save Successful");

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
        int numDisks = 0;
        for(int disks =1; true; disks++){
            if((Math.pow(2,disks))-1==projectedStepCount){
                numDisks = disks;
                break;
            }
        }
        return numDisks;
    }

}
