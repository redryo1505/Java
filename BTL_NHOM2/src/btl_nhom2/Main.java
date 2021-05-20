/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_nhom2;

import btl_nhom2.Change;
import java.util.ArrayList;

/**
 *
 * @author Hoàng
 */
public class Main {
    public static void main(String[] args) {
        
        Matrix a=new Matrix();
        ArrayList<Point> listPoint=new ArrayList<Point>();
//        a.setM(15);
//        a.setN(15);
//        a.randomMatrix(100);
//        a.outputMatrix();
       Point A=new Point(1,1);
       Point B=new Point(2,1);
       Point C=new Point(4,3);
       Point D=new Point(5,4);
        Change xl=new Change();
//        xl.addPoint(, listPoint);
        listPoint.add(A);
        listPoint.add(B);
        listPoint.add(C);
        listPoint.add(D);
        for(Point i: listPoint){
            System.out.println(i);
        }
        ArrayList<ArrayList<Point>> lcr=new ArrayList<ArrayList<Point>>();
        ArrayList<Point> listCentroids=new ArrayList<Point>();
        xl.kMean(listPoint,listCentroids, lcr, 2);
        for(ArrayList<Point> i: lcr){
                for(Point j:i){
                    System.out.println(j);
                } 
            }
    }
}
