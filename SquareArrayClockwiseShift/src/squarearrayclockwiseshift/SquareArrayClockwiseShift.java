/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package squarearrayclockwiseshift;

/**
 *
 * @author xiaolin
 */
import java.util.*;
public class SquareArrayClockwiseShift {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Integer> inputNums = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Trying square array clockwise-shifting");
        System.out.println("Pleas input the dimention of the square array:");
        inputNums.add(in.nextInt());
        System.out.println("Pleas input the numbers of the square array:");
        for (int i = 0; i<inputNums.get(0); i++){
            for (int j = 0; j<inputNums.get(0); j++){
                inputNums.add(in.nextInt());            
            }
        }

        try {
            int[][] nums;
            int[][] shiftedNums;
            nums = inputSquare(inputNums);
            shiftedNums = clockwiseShift(nums);
            System.out.println("The input square array:");
            outputSquare(nums);
            System.out.println("The clockwise-shifted square array:");
            outputSquare(shiftedNums);
        } catch (IllegalInputException e) {
            System.out.println(e);

        } finally {
            System.out.println("End!");
        }
    }

    private static int[][] inputSquare(ArrayList<Integer> nums) 
            throws IllegalInputException
    {
        int dimension;
        if (nums.size()<1)
            throw new IllegalInputException("Number of input nums can' be zero!");
        else {
            dimension = nums.get(0);
            if (nums.size() != dimension*dimension + 1)
                throw new IllegalInputException("Number of input nums is illegal!");
        } 
        
        int[][] res = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                res[i][j] = nums.get(i * dimension + j +1);
            }
        }
        return res;

    }
    
    private static int[][] clockwiseShift(int square[][])
    throws IllegalInputException{
        if (square.length == 0) {

            throw new IllegalInputException("Length of array can't be zero!");
        }
        else if (square[0].length == 0){
            throw new IllegalInputException("Length of array can't be zero!");
        
        } else if (square.length != square[0].length) {
            throw new IllegalInputException("Input array is illegal!");

        } else {
            int dimension = square.length;
            int[][] result = new int[dimension][dimension];
            int circle;
            int maxNumInSuchCircle;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    int circle1 = i;
                    int circle2 = j;
                    int circle3 = dimension - 1 - i;
                    int circle4 = dimension - 1 - j;
                    circle = circle1;
                    if (circle2 < circle) {
                        circle = circle2;
                    }
                    if (circle3 < circle) {
                        circle = circle3;
                    }
                    if (circle4 < circle) {
                        circle = circle4;
                    }
                    maxNumInSuchCircle = dimension - 1 - circle;
                    if ((i == circle) && (j > circle)) {
                        result[i][j] = square[i][j - 1];
                    } else if ((i > circle) && (j == maxNumInSuchCircle)) {
                        result[i][j] = square[i - 1][j];
                    } else if ((i == maxNumInSuchCircle) && (j < maxNumInSuchCircle)) {
                        result[i][j] = square[i][j + 1];
                    } else if ((i < maxNumInSuchCircle) && (j == circle)) {
                        result[i][j] = square[i + 1][j];
                    } else {
                        result[i][j] = square[i][j];
                    }
                }
            }

            return result;      
        
        }   
    }

    private static void outputSquare(int square[][])
            throws IllegalInputException {
        if (square.length == 0) {

            throw new IllegalInputException("Length of array can't be zero!");
        }
        else if (square[0].length == 0){
            throw new IllegalInputException("Length of array can't be zero!");
        
        } else if (square.length != square[0].length) {
            throw new IllegalInputException("Input array is illegal!");

        } else {
            int dimension = square.length;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    System.out.print(square[i][j]);
                    if (j < dimension) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
