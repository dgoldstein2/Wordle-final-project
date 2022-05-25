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
    private static String answer = WordleDictionary.FIVE_LETTER_WORDS[(int)(Math.random()*WordleDictionary.FIVE_LETTER_WORDS.length)+1];
    private int[] x = new int [5];
    private String [] p = new String[5];
    private static String answerCap = "BEATS";
    //private static String answerCap = answer.toUpperCase();
    private int temp = -1;
    

    public void run() {
        gw = new WordleGWindow();
        answer = WordleDictionary.FIVE_LETTER_WORDS[(int)Math.random()*WordleDictionary.FIVE_LETTER_WORDS.length+1];
        assign();
        
        gw.addEnterListener((s) -> enterAction(s));
        
    }
    public void assign(){
        for(int i = 0;i<5;i++){
            p[i] = answerCap.substring(i,i+1);
            System.out.print(p[i] + " ");
        }
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
                p[m] = answerCap.substring(m,m+1);
            }
        
            for(int j = 0; j<5;j++){
                
                if(answerCap.substring(j,j+1).equals(gw.getSquareLetter(gw.getCurrentRow(), j))){
                    x[j] = 1;
                    println(p[j]);
                    p[j] = "";
                    println(p[j]);
                    
                    
                    
                }
                else if((answerCap.contains(gw.getSquareLetter(gw.getCurrentRow(), j))) && (find(s.substring(j,j+1))>-1)){
                    println(find(s.substring(j,j+1)));
                    x[j] = 0;

                    
                }
                
                else{
                    x[j] = -1;
                }
            
                
            }
            temp = -1;
            for(int l : x)
            System.out.println(l);
        
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
                for(int o=0;o<z;o++){
                    if((gw.getSquareLetter(gw.getCurrentRow(), z).equals(gw.getSquareLetter(gw.getCurrentRow(),o))) && (gw.getSquareColor(gw.getCurrentRow(), o).equals(WordleGWindow.PRESENT_COLOR))
                     && (find(gw.getSquareLetter(gw.getCurrentRow(),o))>0)){
                        gw.setSquareColor(gw.getCurrentRow(), o, WordleGWindow.MISSING_COLOR);
                        println(1);
                    }
                }
            }
            if (x[z] == -1){
                if(!gw.getKeyColor(s.substring(z,z+1)).equals(WordleGWindow.PRESENT_COLOR)){
                gw.setKeyColor(s.substring(z,z+1), WordleGWindow.MISSING_COLOR);
                }
                gw.setSquareColor(count, z, WordleGWindow.MISSING_COLOR);
            }
            

        }
        
        if (gw.getCurrentRow() < WordleGWindow.N_ROWS-1) {
        count++;
        
        if(isAllGreen(gw.getCurrentRow())){
            if(gw.getCurrentRow() == 0)
            gw.showMessage("Fantastic!");
            if(gw.getCurrentRow() == 1)
            gw.showMessage("Amazing!");
            if(gw.getCurrentRow() == 2)
            gw.showMessage("Wow!");
            if(gw.getCurrentRow() == 3)
            gw.showMessage("Good Job!");
            if(gw.getCurrentRow() == 4)
            gw.showMessage("Nice!");
            for(int i = gw.getCurrentRow()+1; i<WordleGWindow.N_ROWS;i++){
                for(int j=0;j<WordleGWindow.N_COLS;j++){
                    gw.setSquareColor(i, j, WordleGWindow.CORRECT_COLOR);
                }
            }
        }
        else{gw.setCurrentRow(count);
        gw.addEnterListener((r) -> enterAction(r));
        }
        }
        
        
        else{
            if(isAllGreen(count)){
                gw.showMessage("That was close!");
                
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
    public int find(String o){
        int temp = -1;
        for(int i = 0; i<p.length;i++){
            if(o.equals(p[i])){
                temp = i;
            }
        }
        return temp;
    }
    public static void print(Object ee){
        System.out.print(ee);
    }
    public static void println(Object ee){
        System.out.println(ee);
    }
    
    

/* Startup code */

    public static void main(String[] args) {
        new Wordle().run();
    }

/* Private instance variables */

    private WordleGWindow gw;

}

