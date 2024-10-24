package com.impact.pokemon;

//class for utility functions
public class Utility {

    //method to calculate the effectiveness modifier
    public double getEffectivenessModifier(String typeA,String typeB) {
        double em = 1.0;
        if(typeA.equals("Fire")) {
            if(typeB.equals("Grass")){
                em = 2.0;
            }else if(typeB.equals(("Water"))){
                em=0.5;
            }
        }
        else if(typeA.equals("Water")) {
            if(typeB.equals("Fire")){
                em = 2.0;
            }else if(typeB.equals(("Electric"))){
                em=0.5;
            }
        }
        else if(typeA.equals("Grass")) {
            if(typeB.equals("Electric")){
                em = 2.0;
            }else if(typeB.equals(("Fire"))){
                em=0.5;
            }
        }else {
            if(typeB.equals("Water")){
                em = 2.0;
            }else if(typeB.equals(("Grass"))){
                em=0.5;
            }
        }
        return em;
    }

}
