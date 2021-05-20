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
    public ArrayList<ArrayList<Point>> kMean(ArrayList<Point> listPoint,ArrayList<Point> listCentroids,int k){
        ArrayList<ArrayList<Point>> listCluster=new ArrayList<ArrayList<Point>>();
      //random Centroids
        Random generator = new Random();
       int max=listPoint.size()-1;
        while(listCentroids.size()<k){
            int i=generator.nextInt((max - 0) + 1) + 0; 
            if(listCentroids.contains(listPoint.get(i)))
                continue;
            else
                listCentroids.add(listPoint.get(i));
        }
        ArrayList<Point> listCentroidsOld=listCentroids;
        ArrayList<Point> listCentroidsNew=listCentroids;
        boolean check=true;
        while(check){
            //tao
            listCluster=new ArrayList<ArrayList<Point>>();
            for(int i=0;i<k;i++){
                ArrayList<Point> lt=new ArrayList<Point>();
                listCluster.add(lt);
            }
            for(int i=0;i<listPoint.size();i++){
                ArrayList<Double> d=new ArrayList<Double>();
                for(int j=0;j<k;j++)
                {
                    double kc=listPoint.get(i).distance(listCentroidsNew.get(j));
                    d.add(kc);
                }
                double min=Collections.min(d);
                int id=d.indexOf(min);
               listCluster.get(id).add(listPoint.get(i));
            }
            listCentroidsNew=new ArrayList<Point>();
            int cs=-1;
            for(ArrayList<Point> i: listCluster){
                cs+=1;
                double Tx=0,Ty=0;
                int count=0;
                for(Point j:i){
                    Tx=Tx+j.getX();
                    Ty=Ty+j.getY();
                    count+=1;
                }
                Point ad=new Point(Tx/count,Ty/count);
                listCentroidsNew.add(ad);
                
            }
            
            check=false;
            for(int i=0;i<k;i++){
                if(listCentroidsNew.get(i).equals(listCentroidsOld.get(i)))
                    continue;
                else
                {
                    check=true;
                    break;
                }
            }
            listCentroidsOld=listCentroidsNew;
    }
        for(int i=0;i<k;i++)
        { 
        listCentroids.get(i).setX(listCentroidsOld.get(i).getX());
        listCentroids.get(i).setY(listCentroidsOld.get(i).getY());
        }
        return listCluster;
        
    }
}
