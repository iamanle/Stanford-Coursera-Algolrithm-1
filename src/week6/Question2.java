package week6;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Algorithms: Design and Analysis, Part 1 
 * Programming Question - Week 6
 * @author Felix Garcia Lainez
 */
public class Question2 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<Integer> array = readIntegerArrayFromFile();
        
        int sum = 0;
        
        PriorityQueue<Integer> heapLow = new PriorityQueue<Integer>();
        PriorityQueue<Integer> heapHigh = new PriorityQueue<Integer>();
    
        for(Integer x_i : array)
        {
            if(heapLow.size() > 0)
            {
                if(x_i > -(heapLow.peek()))
                {
                    heapHigh.add(x_i);
                }
                else
                {
                    heapLow.add(-x_i);
                }
            }
            else
            {
                heapLow.add(-x_i);
            }

            if(heapLow.size() > heapHigh.size() + 1)
            {
                heapHigh.add(-heapLow.poll());
            }
            else if(heapHigh.size() > heapLow.size())
            {
                heapLow.add(-heapHigh.poll());
            }

            System.out.println(heapLow.peek());
            sum += -heapLow.peek();
        }
        
        System.out.println("*** RESULT => " + sum  + " ***");
    }
    
    /**
     * Reads the Integer array to be used as input for the assignment
     * @return A Long array 
     */
    private static ArrayList<Integer> readIntegerArrayFromFile()
    {
        ArrayList<Integer> integerArray = new ArrayList<Integer>();
            
        FileInputStream fstream = null;
        try 
        {
        	File file = new File("/Users/anle/Documents/workspace/AlgoI/src/week6/data/median.txt");
        	fstream = new FileInputStream(file);
            
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String line;
            while ((line = br.readLine()) != null){
                integerArray.add(Integer.valueOf(line));
            }
            
            br.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return integerArray;
    }
}
