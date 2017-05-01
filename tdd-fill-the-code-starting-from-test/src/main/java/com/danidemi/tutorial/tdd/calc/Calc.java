package com.danidemi.tutorial.tdd.calc;

public class Calc {

    String display;

    Calc(){
        display = "0";
    }

    public void press(char c) {

        if(display.length()==9) return;

        if(display.equals("0")){
            display = "" + c;
        }else{
            display = display + c;
        }
    }

    public String display() {
        return display;
    }
}
