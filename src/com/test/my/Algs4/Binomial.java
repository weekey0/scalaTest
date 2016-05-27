package com.test.my.Algs4;

import com.test.my.Algs4.stblib.StdDraw3D;
import com.test.my.Algs4.stblib.StdOut;

import java.awt.*;

/**
 * Created by wangzihe on 2016/4/20.
 */
public class Binomial {
    public static double binomial1(int N,int k,double p){
        if (N == 0 && k == 0) return 1.0;
        if (N<0 || k <0 ) return 0.0;
        return (1.0 - p )*binomial1(N-1,k,p)+p*binomial1(N-1,k-1,p);
    }
    public static void main(String[] args){
        // Sets the scale
        StdDraw3D.setScale(-1, 1);
        // Turns off the default info HUD display.
        StdDraw3D.setInfoDisplay(false);
        // Draws the white square border.
        StdDraw3D.setPenColor(StdDraw3D.WHITE);
        StdDraw3D.overlaySquare(0, 0, 0.98);
        long s = System.currentTimeMillis();
        StdOut.println("s "+s);
        double sum = 0.0;
        for (double k=0.4;k<0.7;k+=0.1) {
            Color color = StdDraw3D.randomColor();
            for (int i=0;i<11;i++) {
//                final double v = binomial1(20, i,k);

//                for(int ii = 0; ii < binom_N + 1; ++ii)
//                   for(int jj = 0; jj < binom_k + 1; ++jj)
//                       binom[ii][jj] = -1.0;
//                final double v = binomial2(20, i,k);
                final double v = binomial3(20, i,k);
                StdOut.println(v);
                sum+=v;
                // Draws point
                StdDraw3D.setPenRadius(0.01);
                StdDraw3D.setPenColor(color, 220);
                StdDraw3D.overlayPoint(-0.5+0.1*i,v);
            }
        }

        StdOut.println("Final : "+sum);
        StdOut.println("expense: "+(System.currentTimeMillis()-s));

        // Draws the information text.
        StdDraw3D.setPenColor(StdDraw3D.WHITE);
        StdDraw3D.overlayText(0, 0.91, "Standard Draw 3D - Test Program");
        StdDraw3D.overlayText(0, -0.95, "You should see rotating text. Drag the mouse to orbit.");

        // Creates the 3D text object and centers it.
        StdDraw3D.setPenColor(StdDraw3D.YELLOW);
        StdDraw3D.setFont(new Font("Arial", Font.BOLD, 16));
        StdDraw3D.Shape text = StdDraw3D.text3D(0, 0, 0, "StdDraw3D");
        text.scale(3.5);
        text.move(-0.7, -0.1, 0);
        text = StdDraw3D.combine(text);

        while (true) {

            // Rotates the 3D text by 1.2 degrees along the y-axis.
            text.rotate(0, 1.2, 0);

            // Shows the frame for 20 milliseconds.
            StdDraw3D.show(20);
        }

    }
    private static int binom_N = 100;
    private static int binom_k = 50;
    private static double[][] binom = new double[binom_N+1][binom_k+1];
    private static double binomial2(int N, int k, double p){
        if (N<0||k<0){
            return 0.0;
        }else if (N ==0 && k == 0){
            if (binom[N][k] == -1.0)
                binom[N][k] = 1.0;
        }else {
            if (binom[N][k] == -1.0)
                binom[N][k] = (1.0-p)*binomial1(N -1,k,p)+p*binomial1(N-1,k-1,p);
        }
        return binom[N][k];
    }
    //memoization
    public static double binomial3(int N,int k,double p){
        double[][] b = new double[N+1][k+1];
        //base cases
        for(int i =0;i<N;i++)
            b[i][0] = Math.pow(1.0-p,i);
        b[0][0] = 1.0;
        //reclusive formula
        for (int i=1;i<=N;i++){
            for(int j=1;j<=k;j++){
                b[i][j] = p *b[i-1][j-1] + (1.0-p)*b[i-1][j];
            }
        }
        return b[N][k];
    }
}
