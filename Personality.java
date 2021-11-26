import java.util.*;
import java.io.*;

public class Personality {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        intro();
        System.out.print("input file name? ");
        String inputFile = console.nextLine();
        Scanner input = new Scanner(new File(inputFile));

        System.out.print("output file name? ");
        String outputFile = console.nextLine();
        PrintStream output = new PrintStream(new File(outputFile));
        
        while(input.hasNextLine()){
            String name = input.nextLine();
            String testResponse = input.nextLine();
            int[] bArr = new int[4]; 
            int [] aArr = new int [4];
            countSingleUser(testResponse, aArr, bArr);
            int[] bPercent= bPercentage(aArr, bArr);
            char [] personalityType = personality(bPercent);
            output.print(name + ": " + Arrays.toString(bPercent) + " = ");
            output.print(personalityType);
            output.println();
        }
    }

    public static void intro(){
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    public static void countSingleUser(String testResponse, int[] aArr, int [] bArr){
        for (int i = 0; i < testResponse.length(); i++) {
            char response = testResponse.charAt(i);
            if (i % 7 == 0){
                if (response == 'B'|| response == 'b') {
                    bArr[0]++;
                } else if (response == 'A'|| response == 'a') {
                    aArr[0]++; 
                } 
            } else if (i % 7 == 1 || i % 7 == 2){
                if (response == 'B'|| response == 'b') {
                    bArr[1]++;
                } else if (response == 'A'|| response == 'a') {
                    aArr[1]++; 
                }
            } else if (i % 7 == 3 || i % 7 == 4){
                if (response == 'B'|| response == 'b') {
                    bArr[2]++;
                } else if (response == 'A'|| response == 'a') {
                    aArr[2]++; 
                }
            } else if (i % 7 == 5 || i % 7 == 6){
                if (response == 'B'|| response == 'b') {
                    bArr[3]++;
                } else if (response == 'A'|| response == 'a') {
                    aArr[3]++; 
                }
            }
        }
    }

    public static int[] bPercentage(int [] aArr, int [] bArr){
        int [] bPercent = new int [4];
        for (int i = 0; i < bArr.length; i++) {
            double total = (100.0 * bArr[i])/(bArr[i] + aArr[i]);
            bPercent[i] = (int)Math.round(total);
        }
        return bPercent;
    }

    public static char[] personality(int [] bPercent) {
        char [] left = {'E', 'S', 'T', 'J'};
        char [] right = {'I', 'N', 'F', 'P'};
        char [] personality = new char [4];
        for (int i = 0; i < bPercent.length; i++){
            if (bPercent[i] > 50){
                personality[i] = right[i];
            } else if (bPercent[i] < 50) {
                personality[i] = left[i];
            } else {
                personality[i] = 'X';
            }
        }
        return personality;
    }
}