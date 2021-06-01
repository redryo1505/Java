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
     ArrayList<Point> listCentroids=new ArrayList<Point>();
    public ArrayList<Point> generatePointsGrid(Segment segment, int n)//n=số hàng =số cột
    {
       double min=Math.ceil(segment.getWidth()*segment.getHeight()/(2*Math.pow(segment.getSensor().getDiscretePhenomenonSensingRange(), 2)));
        double max=0.25*segment.getSensor().getBandwidth()*Math.pow(10, 6)/(8*segment.getSensor().getPackageSize());
        if(n*n<min||n*n>max)
            return null;
        else
        {
            ArrayList<Point> listPoint=new ArrayList<Point>();
            double dx=segment.getWidth()/(n-1);
            double dy=segment.getHeight()/(n-1);
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                {
                    Point pointAdd=new Point(dx*i,dy*j);
                    listPoint.add(pointAdd);
                }
            return listPoint;
        }
    }
  
    public ArrayList<Point> generatePointsRand(int w, int h, int n)//n là số sensor
    {
        // sinh các điểm trên một đoạn

        ArrayList<Point> listPoint =new ArrayList<Point>();
        Random rand = new Random();
        for (int i =0; i < n; i++)  
        {
            int x = rand.nextInt(w);
            int y = rand.nextInt(h);
            Point pointAdd=new Point(x,y);
            listPoint.add(pointAdd);
        }
        return listPoint;
    }
    
    public ArrayList<ArrayList<Point>> getListCluster() {
        return listCluster;
    }

    public ArrayList<Point> getListCentroids() {
        return listCentroids;
    }
    
    public void kMean(ArrayList<Point> listPoint,int k){
       
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
//        ArrayList<Point> listCentroidsOld=listCentroids;
        ArrayList<Point> listCentroidsNew=listCentroids;
        boolean check=true;
        while(check){
            //generate k rows with each row being a cluster
            listCluster=new ArrayList<ArrayList<Point>>();
            for(int i=0;i<k;i++){
                ArrayList<Point> lt=new ArrayList<Point>();
                listCluster.add(lt);
            }
            //Insert points into the cluster by comparing the distance between them and the cluster center
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
             //update centroid
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
            //Algorithm stop condition
            check=false;
            for(int i=0;i<k;i++){
                if(listCentroidsNew.get(i).equals(listCentroids.get(i)))
                    continue;
                else
                {
                    check=true;
                    break;
                }
            }
            listCentroids=listCentroidsNew;     
    }   
    }
    public int findK(ArrayList<Point> listPoint,double radius){
        int k=1;
        boolean check=true;
        while(check){
            k++;
            check=false;
            kMean(listPoint, k);
            int cs=-1;
            kt:
            for(ArrayList<Point> i: listCluster){
                cs+=1;
                for(Point j:i){
                    if(listCentroids.get(cs).distance(j)>2*radius)//điều kiện các nút thuộc 1 cụm
                    {
                        check=true;
                        break kt;
                    }
                }
                
            }
        }
        return k;
    }
    public ArrayList<Point> optimalScoreDistribution(Segment segment){
        //Tìm số lượng sensor min và max
        double min=Math.ceil(segment.getWidth()*segment.getHeight()/(2*Math.pow(segment.getSensor().getDiscretePhenomenonSensingRange(), 2)));
        double max=0.25*segment.getSensor().getBandwidth()*Math.pow(10, 6)/(8*segment.getSensor().getPackageSize());
        
        ArrayList<ArrayList<Point>> listPointRandAndGrid=new ArrayList<ArrayList<Point>>();
        //đưa list point phân bố ngẫu nhiên vào danh sách
        for(int i=(int)min;i<max;i++){
            listPointRandAndGrid.add(generatePointsRand(segment.getWidth(),segment.getHeight(),i));
        }
        //đưa list point phân bố dạng lưới vào danh sách
        for(int i=1;i<4;i++)
        {
            if(generatePointsGrid(segment, i*5)!=null)
                listPointRandAndGrid.add(generatePointsGrid(segment, i*5));
        }
        ArrayList<Integer> k=new ArrayList<Integer>();
        for(ArrayList<Point> i:listPointRandAndGrid){
            k.add(findK(i, segment.getSensor().getTransmissionRange()));
       }
        //tìm số cụm ít nhất
        
        int kMin=Collections.min(k);
        int vt=k.indexOf(kMin);
        segment.setK(kMin);
        return listPointRandAndGrid.get(vt);
    } 
    
}
