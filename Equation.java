/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author CATECH
 */
public class Equation {
    public static void main (String args[])  throws java.io.IOException
        {
        double a,b,c,delta,x1,x2;

        System.out.println("Bienvenue dans le resolveur d'équations JAVA du second Degre\n\n");
        System.out.println("Entrez les valeurs de a,b,c tel que ax\u00FD+bx+c=0\n\n");

        System.out.println("a = ");
        a = Double.valueOf(lecture()).doubleValue();

        System.out.println("\nb = ");
        b = Double.valueOf(lecture()).doubleValue();

        System.out.println("\nc = ");
        c = Double.valueOf(lecture()).doubleValue();

        delta = (b*b) - 4*a*c;
        if (delta<0)
        {
        System.out.println("\n\nIl n'y a pas de racines reelle a l'equation.");
        }
        else
        {
        x1 = (-b-Math.sqrt(delta))/(2*a);
        x2 = (-b+Math.sqrt(delta))/(2*a);
        System.out.println("\n\nLes racines sont x1 = " + x1 + " et x2 = " +x2);
        }

        }

	public static String lecture()   throws java.io.IOException
        {
        String chaine = "";
        char lettre;
        while ((lettre=(char)System.in.read()) != '\r')
        {
        chaine = chaine + lettre;
        }
        return chaine;
        }
}
