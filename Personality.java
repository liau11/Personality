//Lily Au 
//11.23.2021
//CSE 142
//TA: Julia Ball
//Take-home Assessment #7
//
//Program will print out an introduction of its function, then prompt user for input file
//and output file. It processes the file and determines the personality type of each person
//by calculating the person's responses to 70 questions. The personality type is printed out
//to the output file that user requested. 
import java.util.*;
import java.io.*;

//Passed down to countSingleUser method:
    //testResponse (consists of 70 answers per person)
    //aArr(array for 4 elements corresponding to the amount of 'A'/'a' in one of the four 
        //dimensions of personality. Default values are 0.)
    //bArr(array for 4 elements corresponding to the amount of 'B'/'b' in one of the four 
        //dimensions of personality. Default values are 0.)
//Passed down to bPercentage method, which returns bPercent array
    //aArr and bArr have been updated to hold amount of A/Bs in their elements. They get 
    //passed down to calculate the percentage of B responses in the four personality 
    //dimensions. 
//bPercent array is passed down to personality method. It also returns an array of characters
    //called personalityType to store the results. 
//int 4 is set to class variable TYPES
public class Personality {
    public static final int TYPES = 4;
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
            int[] bArr = new int[TYPES]; 
            int [] aArr = new int [TYPES];
            countSingleUser(testResponse, aArr, bArr);
            int[] bPercent= bPercentage(aArr, bArr);
            char [] personalityType = personality(bPercent);
            output.print(name + ": " + Arrays.toString(bPercent) + " = ");
            output.println(personalityType);
        }
    }

//Prints out introduction of the program. 
    public static void intro(){
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

//Method goes through the list of 70 answers. Depending on each question's position and its
//response (A/B), the corresponding element to that specific array will increase by 1. 
//Passed down:
    //String testResponse - a string of answers to the 70 personality questions
    //int[] aArr - array to keep track of all the A/a responses
    //int[] bArr - array to keep track of all the B/b responses
    public static void countSingleUser(String testResponse, int[] aArr, int [] bArr){
        for (int i = 0; i < testResponse.length(); i++) {
            char response = testResponse.charAt(i);
            int mappingValue = (i % 7 + 1) / 2;
            if (response == 'B'|| response == 'b') {
                bArr[mappingValue]++;
            } else if (response == 'A'|| response == 'a') {
                aArr[mappingValue]++; 
            }
        }
    }

//int[] aArr and int[] bArr are both passed down to return bPercent array.
//Method calculates the percentage of B in each index/element and stores percentage
//to corresponding index/element in bPercent array. 
    public static int[] bPercentage(int [] aArr, int [] bArr){
        int [] bPercent = new int [TYPES];
        for (int i = 0; i < bArr.length; i++) {
            double total = (100.0 * bArr[i])/(bArr[i] + aArr[i]);
            bPercent[i] = (int)Math.round(total);
        }
        return bPercent;
    }

//Depending on the value of each index in bPercent, the corresponding index in 
//personality array will store the corresponding index of either the left or right array. 
//bPercent is passed down and personality array is returned. 
    public static char[] personality(int [] bPercent) {
        char [] left = {'E', 'S', 'T', 'J'};
        char [] right = {'I', 'N', 'F', 'P'};
        char [] personality = new char [TYPES];
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