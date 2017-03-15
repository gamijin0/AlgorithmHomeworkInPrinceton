/**
 * Created by 49738 on 2017/3/14.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation extends WeightedQuickUnionUF{
    private int grid[][]; //0 is blocked, 1 is opened, 2 is full
    private int virtual_top;
    private int virtual_bottom;
    public Percolation(int n) throws IllegalArgumentException{
        super(n*n+2);
        if(n<=0){
            throw new IllegalArgumentException();
        }
        this.grid = new int[n][n];
        //init the value of each item in the grid to be blocked
        for(int i=0;i<this.grid.length;i++){
            for(int j=0;j<this.grid.length;j++){
                this.grid[i][j]=0;
            }
        }
        //union the first floor sides with the virtual top side
        for(int i=1;i<n+1;i++){
            this.union(0,i);
        }
        //union the last floor sides with the virtual bottom side
        for(int i=n*n-n+1;i<n*n+1;i++){
            this.union(n*n+1,i);
        }

    }            // create n-by-n grid, with all sites blocked
    public void open(int row, int col) throws IndexOutOfBoundsException{
        if( row >= this.grid.length || col >= this.grid.length){
            throw new IndexOutOfBoundsException();
        }
            this.grid[row][col]=1;
    }    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException{
        if( row >= this.grid.length || col >= this.grid.length){
            throw new IndexOutOfBoundsException();
        }
        return this.grid[row][col]==1;
    }  // is site (row, col) open?
    public boolean isFull(int row, int col) throws IndexOutOfBoundsException{
        if( row >= this.grid.length || col >= this.grid.length){
            throw new IndexOutOfBoundsException();
        }
        return this.grid[row][col]==2;
    }  // is site (row, col) full?
    public     int numberOfOpenSites(){
        return 0;
    }       // number of open sites
    public boolean percolates(){
        return true;
    }              // does the system percolate?

    public static void main(String[] args){

    }   // test client (optional)
}
