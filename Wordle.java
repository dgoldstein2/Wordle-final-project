/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */

import edu.willamette.cs1.wordle.WordleDictionary;
import edu.willamette.cs1.wordle.WordleGWindow;


public class Wordle {
    private int count = 0;
    private String answer = WordleDictionary.FIVE_LETTER_WORDS[(int)Math.random()*WordleDictionary.FIVE_LETTER_WORDS.length+1];
    private int[] x = new int [5];

    public void run() {
        gw = new WordleGWindow();
        gw.addEnterListener((s) -> enterAction(s));
        answer = WordleDictionary.FIVE_LETTER_WORDS[(int)Math.random()*WordleDictionary.FIVE_LETTER_WORDS.length+1];
    }

/*
 * Called when the user hits the RETURN key or clicks the ENTER button,
 * passing in the string of characters on the current row.
 */

    public void enterAction(String s) {
        
        for(int i = 0;i<answer.length();i++){
            for(int j = 0; j<s.length();j++){
                if (s.substring(j,j+1).equals(answer.substring(i,i+1))){
                    x[i] = 0; // 1 =  green, 0 = yellow, -1 = grey
                }
                if(s.substring(i,i+1).equals(answer.substring(i,i+1))){
                    x[i] = 1;
                }
                else{
                    x[i] = -1;
                }
            }
        }
        for (int z = 0; z<x.length;z++){
            if (x[z] == 0){
                gw.setSquareColor(count, z, WordleGWindow.PRESENT_COLOR);
            }
            if (x[z] == 1){
                gw.setSquareColor(count, z, WordleGWindow.CORRECT_COLOR);
            }
            if (x[z] == -1){
                gw.setSquareColor(count, z, WordleGWindow.MISSING_COLOR);
            }
            

        }
        if (count < WordleGWindow.N_ROWS-1) {
        count++;
        gw.setCurrentRow(count);
        gw.addEnterListener((r) -> enterAction(r));
        }
        else{
            gw.showMessage("The answer was " + answer);
        }
    }

/* Startup code */

    public static void main(String[] args) {
        new Wordle().run();
    }

/* Private instance variables */

    private WordleGWindow gw;

}

