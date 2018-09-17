/**
 * Matrix multiplication using Threads 
 */
package threadmatrixmultiplication;
/** 
 * @author Nicolas Curço
 */
public class ThreadMatrixMultiplication {

    // Size of matrices
    public static final int size = 1000;
    
    // Concurrent data structure
    public static int[][] matrixA;
    public static int[][] matrixB;
    public static int[][] matrixC;
    
    // Array of threads
    public static Thread workers [];
    
    public static void main(String[] args) {
        
        // Variable initialization
        matrixA = new int[size][size];
        matrixB = new int[size][size];
        matrixC = new int[size][size];
        workers = new Thread[1];
        int threads = 1;
        // Filling the matrix A
        int k=1;
        for(int i=0 ; i<size; i++){
            for(int j=0 ; j<size; j++){
                if (k%2==0)
                   matrixA[i][j] = -k;
                else
                   matrixA[i][j] = k;
            }
            k++;
        }
        
        // Filling the matrix B
        k=1;
        for(int j=0 ; j<size; j++){
            for (int i=0 ; i<size; i++){
                if (k%2==0)
                   matrixB[i][j] = -k;
                else
                   matrixB[i][j] = k;
            }
            k++;
        } 

        // Creates all THREADS
        int count = 0;
        int positionsWorkers = 0;
        int division = (size*size)/threads;
        int rowIni = 0;
        int colunmIni = 0;
        for (int row = 0; row< size; row++){
            for(int colunm =0; colunm<size; colunm++){
               if(count == 0){
                   rowIni = row;
                   colunmIni = colunm;
               }
               if(count == division-1){
                   workers[positionsWorkers++] = new Thread ( new WorkerThread(rowIni,colunmIni,row,colunm,matrixA,matrixB,matrixC));
               }
               count ++;
               if(count == division){
                   count = 0;
               }
            }
        }
        
        // Prepare to measure time
        long inicio = System.nanoTime();
        
        // Starts all threads
        for (Thread worker : workers) {
            if(worker != null)
            {
               worker.start();
            }
            
        }
        
        // Wait all threads to end
        for (Thread worker : workers) {
            try {
                  if(worker != null)
            {
               worker.join();
            }
            }catch(InterruptedException ie){}  
        }

           
        // Get the time
        long fim = System.nanoTime();
        double total = (fim-inicio)/1000000000.0;
        
        // Check if results are correct
        for(int i=0; i<size; i++){
            k = size*(i+1);
            for(int j=0; j<size; j++){
                int k_col = k*(j+1);
                if(i % 2 ==0){
                   if(j % 2 == 0){
                      if(matrixC[i][j]!=k_col)
                         System.exit(1);
                   }
                   else{
                      if(matrixC[i][j]!=-k_col)
                         System.exit(1);
                   }
                }
                else{
                   if(j % 2 == 0){
                      if (matrixC[i][j]!=-k_col)
                         System.exit(1);
                   }
                   else{
                      if(matrixC[i][j]!=k_col)
                         System.exit(1);
                   }
                }
            } 
        }
        /*
        /           for(int row = 0; row<size; row++){
            for(int colunm =0; colunm<size; colunm++){
                if(matrixC[row][colunm] > 0)
                {
                  System.out.print("  " + matrixC[row][colunm] + "  ");
                }
                else{
                  System.out.print(" " +  matrixC[row][colunm] + " ");
                }
               
            }
            System.out.println();
        }
           */
        // Shows the execution time
        System.out.printf("Tempo de Execução: %f \n",total);
    }
}
