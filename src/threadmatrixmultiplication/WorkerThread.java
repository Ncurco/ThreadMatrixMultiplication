/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadmatrixmultiplication;

/**
 *
 * @author Nicolas Curco
 */
public class WorkerThread implements Runnable{
    private int rowIni;
    private int colunmIni;
    private int rowFin;
    private int colunmFin;
    private int [][] a;
    private int [][] b;
    private int [][] c;
    
    public WorkerThread(int rowIni, int colunmIni, int rowFin, int colunmFin, int [][] a, int [][] b, int [][] c ){
        this.rowIni = rowIni;
        this.colunmIni = colunmIni;
        this.rowFin = rowFin;
        this.colunmFin = colunmFin;
        this.a = a;
        this.b = b;
        this.c = c;      
    };
    public int[][] getC()
    {
        return this.c;
    }
    
    @Override
    public void run() {
        for(int row = rowIni; row <= rowFin; row++){
            if(row == rowIni){
                for(int colunm = colunmIni; colunm <b.length; colunm++){
                c[row][colunm] = 0;
                    for (int k=0 ; k<b.length; k++) {
                        c[row][colunm] += a[row][k] * b[k][colunm];
                    }
                }
            }
            if(row == rowFin){
                for(int colunm = 0; colunm <=colunmFin; colunm++){
                c[row][colunm] = 0;
                    for (int k=0 ; k<b.length; k++) {
                        c[row][colunm] += a[row][k] * b[k][colunm];
                    }
                }
            }
            if(row != rowFin && row != rowIni){
                for(int colunm = 0; colunm <b.length; colunm++){
                c[row][colunm] = 0;
                    for (int k=0 ; k<b.length; k++) {
                        c[row][colunm] += a[row][k] * b[k][colunm];
                    }
                }
            }
        }
       
        
    }
    
}
