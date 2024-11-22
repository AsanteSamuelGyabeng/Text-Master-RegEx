package com.example.learnregex.controllers;
import java.util.regex.* ;

public class Master {


    public static String operator(String input, String pattern){

        Matcher mt = Pattern.compile(pattern).matcher(input);

        if(mt.find()){
//            mt.group()
            if(mt.groupCount() > 0){
                return mt.group() + "Appears" + mt.groupCount() + "times" ;
            }
            return "true : the word" +" ("+mt.group()+")" + " \n is in the given sentence " + input + "\n starting at the index " + mt.start() + " and ends at " + mt.end() + "\n";

        }

        return "No match found";
    }

    public static void replaceWords(String mainInput, String replaceInput) {
        String text = mainInput;
        String theOutput = mainInput.replaceAll("\\bboy\\b", replaceInput);
        System.out.println(theOutput);
    }


}
