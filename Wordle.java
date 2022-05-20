/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */

import edu.willamette.cs1.wordle.WordleDictionary;
import edu.willamette.cs1.wordle.WordleGWindow;
import java.util.ArrayList;


public class Wordle {
    private int count = 0;
    private String answer = WordleDictionary.FIVE_LETTER_WORDS[(int)(Math.random()*WordleDictionary.FIVE_LETTER_WORDS.length)+1];
    private int[] x = new int [5];
    //private String answerCap = answer.toUpperCase();
    private static String answerCap = "BEETS";
    private int temp = -1;
    private static ArrayList <Integer> u = new ArrayList<Integer>();

    public void run() {
        gw = new WordleGWindow();
        answer = WordleDictionary.FIVE_LETTER_WORDS[(int)Math.random()*WordleDictionary.FIVE_LETTER_WORDS.length+1];
        
        gw.addEnterListener((s) -> enterAction(s));
        
    }

/*
 * Called when the user hits the RETURN key or clicks the ENTER button,
 * passing in the string of characters on the current row.
 */

    public void enterAction(String s){
            boolean v = false;
            for(String n : WordleDictionary.FIVE_LETTER_WORDS){
                if(s.toLowerCase().equals(n)){
                    v=true;
                }
                
            }
            if(!v){
                gw.showMessage("Not a valid word");
                
            }
            else{
            for(int m = 0;m<s.length();m++){
                gw.setSquareLetter(gw.getCurrentRow(), m, s.substring(m,m+1));
            }
        
            for(int j = 0; j<5;j++){
                
                if(answerCap.substring(j,j+1).equals(gw.getSquareLetter(gw.getCurrentRow(), j))){
                    x[j] = 1;
                    temp = j;
                    
                }
                else if((answerCap.contains(gw.getSquareLetter(gw.getCurrentRow(), j)))
                 && ((temp == -1)||(!gw.getSquareLetter(gw.getCurrentRow(), j).equals(s.substring(temp,temp+1))))){ // need to check again for double yellow on words we already yellowed
                    
                    x[j] = 0;

                    
                }
                
                else{
                    x[j] = -1;
                }
            
                
            }
            temp = -1;
        
        for (int z = 0; z<x.length;z++){
            if (x[z] == 0){
                if(!gw.getKeyColor(s.substring(z,z+1)).equals(WordleGWindow.CORRECT_COLOR)){
                gw.setKeyColor(s.substring(z,z+1), WordleGWindow.PRESENT_COLOR);
                }
                gw.setSquareColor(count, z, WordleGWindow.PRESENT_COLOR);
            }
            if (x[z] == 1){
                gw.setKeyColor(s.substring(z,z+1), WordleGWindow.CORRECT_COLOR);
                gw.setSquareColor(count, z, WordleGWindow.CORRECT_COLOR);
            }
            if (x[z] == -1){
                if(!gw.getKeyColor(s.substring(z,z+1)).equals(WordleGWindow.PRESENT_COLOR)){
                gw.setKeyColor(s.substring(z,z+1), WordleGWindow.MISSING_COLOR);
                }
                gw.setSquareColor(count, z, WordleGWindow.MISSING_COLOR);
            }
            

        }
        
        if (count < WordleGWindow.N_ROWS-1) {
        count++;
        if(isAllGreen(gw.getCurrentRow())){
            gw.showMessage("Fantastic");
        }
        else{gw.setCurrentRow(count);
        gw.addEnterListener((r) -> enterAction(r));
        }
        }
        
        
        else{
            if(isAllGreen(count)){
                gw.showMessage("Good job you won!");
            }
            else{gw.showMessage("The answer was " + answerCap.toLowerCase());}
        }
    }
    }
    public boolean isAllGreen(int z){
        boolean x = true;
        for(int i = 0; i<5; i++){
            if(!gw.getSquareColor(z, i).equals(WordleGWindow.CORRECT_COLOR)){
                x= false;
            }
        }
        return x;
    }
    public static int countEachLetter(String letter){
        int count = 0;
        for(int i = 0;i<answerCap.length();i++){
            if(answerCap.substring(i,i+1).equals(letter)){
                count ++;
            }
        }
        return count;
    }
    public static void createLetterArray(){
        int count = 0;
        while(count<5){
            count += countEachLetter(answerCap.substring(count,count+1));
            u.add(count);
        }
    }
    

/* Startup code */

    public static void main(String[] args) {
        new Wordle().run();
    }

/* Private instance variables */

    private WordleGWindow gw;

}

