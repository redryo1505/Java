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
        a.setM(15);
        a.setN(15);
        a.randomMatrix(100);
        a.outputMatrix();
        Change xl=new Change();
        xl.addPoint(a, listPoint);
        for(Point i: listPoint){
            System.out.println(i);
        }
           
    }
}
