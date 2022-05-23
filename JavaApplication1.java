/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import java.util.Vector;
import java.lang.Integer;

/**
 *
 * @author CATECH
 */
public class JavaApplication1 {

   
    public static boolean inversion(double[][] M, int m, int n, double[][] B) {      
        if (m != n)       	
        {    
            System.out.println("Matrice non carrée");
        }      	   
        return false;      	
                                    
//Pour stocker les lignes pour lesquels un pivot a déjà été trouvé      	
    Vector<Integer> I = new Vector<Integer>();  
    //Pour stocker les colonnes pour lesquels un pivot a déjà été trouvé      
    Vector<Integer> J = new Vector<Integer>();      
    //Pour calculer l'inverse de la matrice initiale      
    double[][] A = new double[m][n];             	
    //Copie de M dans A et Mise en forme de B:B=I      	
    for (int i=0; i<n; i++)      	
    {      	    
        for (int j=0; j<n; j++)      	    
        {      		
            A[i][j] = M[i][j];      		
            if (i==j)      		   
                B[i][j] = 1;      		
            else       		   
                B[i][j] = 0;      	  
        }     
    }             	
    //Paramètres permettant l'arrêt prématuré des boucles ci-dessous si calcul impossible	      
    boolean bk = true;      
    boolean bl = true;             
    //Paramètres de contrôle pour la recherche de pivot      	
    int cnt_row = 0;      	
    int cnt_col = 0;             	
    //paramètre de stockage de coefficients      	
    double a = 0, tmp;	
    int k, l  ;
    for ( k=0; k<n && bk; k++)       	
    {      	   
        if (!I.contains(k))       	 
        {      		
            I.addElement(k);
        }
    }      		
    cnt_row++;      		
    bl = true;      		
    for (l=0; l<n && bl; l++)       
    {      		    
        if (!J.contains(l))       		    
        {      			
            a = A[k][l];
        }
    } 			      			
    if (a != 0)       			
    {      			   
        J.addElement(l);
    }      			    
    cnt_col++;			       
    bl = false; //permet de sortir de la boucle car le pivot a été trouvé      		
    for (int p=0; p<n; p++)      			    
    {      				
        if (p != k)      				
        {      				   
            tmp = A[p][l];      				
            for (int q=0; q<n; q++)      				   
            {      					
                A[p][q] = A[p][q] - A[k][q]*(tmp/a);      			
                B[p][q] = B[p][q] - B[k][q]*(tmp/a);      				  
            }      				
        }	      			 
    }      			
    			      		        		
if (cnt_row != cnt_col)       		
{      		   
    //Matrix is singular";                         
    //Pas de pivot possible, donc pas d'inverse possible! On sort de la boucle      		   
        bk = false;      		  
        k = n;       		
}	             	              
if (!bk)      	
{      	   
//Le pivot n'a pas pu être trouve précédemment, ce qui a donne bk = false      	  
        System.out.println("Matrix is singular");      	   
        for (int i=0; i<n; i++) {      		
        for (int j=0; j<n; j++) {     
        B[j][i] = M[j][i];      		
        }      	 
        }      	 
        return false;    
        }      
        else{      
        //Réorganisation des colonnes de sorte que A=I et B=Inv(M). Méthode de Gauss-Jordan      	    
        for ( l=0; l<n; l++)      	    
        {      		
        for (k=0; k<n; k++)      		
        {      		    
        a = A[k][l];      		 
        if (a != 0)      		 
        {      			
        A[k][l] = 1;      			
        for (int p=0; p<n; p++)      			
        {      			   
        B[k][p] = B[k][p]/a;     
        }      			
        if (k != l) 
        {      			 
        exchange_row(A,k+1,l+1,n,n); 
        exchange_row(B,k+1,l+1,n,n);
        }      			
        k = n; //Pour sortir de la boucle car le coefficient non nul a été trouve      		
        }      	
        }      	   
        }	    	          	   
        return true;	   
        }	    
}      

       /*To exchange two rows in a matrix*/       
    public static void exchange_row(double[][] M, int k, int l, int m, int n) 
    {      	
        if (k<=0 || l<=0 || k>n || l>n || k==l)   
            return;      	
        double tmp;      
        for (int j=0; j<n; j++)      
        {      	   
            tmp = M[k-1][j];      
            M[k-1][j] = M[l-1][j];      
            M[l-1][j] = tmp;      	
        }	         
    }      
   
    
}
