/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_nhom2;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 *
 * @author Hoàng
 */
public class Change {
    ArrayList<ArrayList<Point>> listCluster=new ArrayList<ArrayList<Point>>();
    public void addPoint(Matrix mt,ArrayList<Point> listPoint){
        for(int i=0;i<mt.getM();i++)
             for(int j=0;j<mt.getN();j++)
             {
                 if(mt.getA()[i][j]==1)
                 {
                     Point p=new Point(i,j);
                     listPoint.add(p);
                 }
             }
    }
    public void kMean(ArrayList<Point> listPoint,LinkedHashSet<Point> listCentroids,ArrayList<ArrayList<Point>> listCluster,int k){
      //random Centroids
        Random generator = new Random();
       int max=listPoint.size()-1;
        while(listCentroids.size()<k){
            int i=generator.nextInt((max - 0) + max) + 0; 
            listCentroids.add(listPoint.get(i));
        }
        
    }
}
