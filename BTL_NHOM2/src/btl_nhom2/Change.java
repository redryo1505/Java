/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_nhom2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
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
    public void kMean(ArrayList<Point> listPoint,ArrayList<Point> listCentroids,ArrayList<ArrayList<Point>> listCluster,int k){
      //random Centroids
      LinkedHashSet<Point> lc=new LinkedHashSet<Point>();
        Random generator = new Random();
       int max=listPoint.size()-1;
        while(lc.size()<k){
            int i=generator.nextInt((max - 0) + max) + 0; 
            lc.add(listPoint.get(i));
        }
        
        listCentroids=new ArrayList<Point>(lc);
        boolean check=true;
        
        ArrayList<ArrayList<Point>> lcr=new ArrayList<ArrayList<Point>>();
        
        while(check){
            ArrayList<ArrayList<Point>> r=new ArrayList<ArrayList<Point>>();
            for(int i=0;i<k;i++){
            ArrayList<Point> lt=null;
            r.add(lt);
            }
            for(int i=0;i<listPoint.size();i++){
                ArrayList<Double> d=new ArrayList<Double>();
                for(int j=0;j<k;j++)
                {
                    double kc=listPoint.get(i).distance(listCentroids.get(j));
                    d.add(kc);
                }
                double min=Collections.min(d);
                int id=d.indexOf(min);
               r.get(id).add(listPoint.get(i));
            }
            int cs=-1;
            for(ArrayList<Point> i: r){
                cs+=1;
                double Tx=0,Ty=0;
                int count=0;
                for(Point j:i){
                    Tx+=j.getX();
                    Ty+=j.getY();
                    count+=1;
                }
                listCentroids.get(cs).setX(Tx/count);
                listCentroids.get(cs).setY(Ty/count);
            }
            lcr=r;
            check=false;
            for(int i=1;i<k;i++){
                if(listCentroids.get(0).equals(i))
                    continue;
                else
                {
                    check=true;
                    break;
                }
            }
    }
        listCluster=new ArrayList<ArrayList<Point>>(lcr);
    }
}
