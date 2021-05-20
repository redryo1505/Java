/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_nhom2;

import java.util.Random;

/**
 *
 * @author Hoàng
 */
public class Matrix {
    private int m,n;
    private int [][] a;

    public Matrix(int m, int n, int[][] a) {
        this.m = m;
        this.n = n;
        this.a = a;
    }
    public void randomMatrix(int t)//t là số sensor
    {
        a=new int[m][n];
        Random generator = new Random();
        
         input:
         for(int i=0;i<m;i++){
             
             for(int j=0;j<n;j++)
             {
                 if(t<1)
                     break input;
                 if(a[i][j]==1)
                     continue;
                 else{
                 a[i][j]= generator.nextInt((1 - 0) + 1) + 0; 
                 if(a[i][j]==1)
                     t=t-1;
                 
                 }
             }
         }
    }
    public void outputMatrix(){
        for(int i=0;i<m;i++){
             for(int j=0;j<n;j++){
                 if(a[i][j]==1)
                     System.out.print("*");
                 else
                     System.out.print(" ");
                 System.out.print("   ");
             }
                
             System.out.println("");
         }
    }
    public Matrix() {
    }
    
    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int[][] getA() {
        return a;
    }
    public int count(){
        int t=0;
        for(int i=0;i<m;i++)
             for(int j=0;j<n;j++)
             {
                 if(a[i][j]==1)
                    t++;
             }
        return t;
        
    }
}
