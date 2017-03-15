/**
 * Created by 49738 on 2017/3/14.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Objects;


public class Percolation extends WeightedQuickUnionUF{
    private boolean grid[][]; //false is blocked, true is opened
    public Percolation(int n) throws IllegalArgumentException{
        super(n*n+2);//add a virtual top(0) and bottom(n*n+1) side
        if(n<=0){
            throw new IllegalArgumentException();
        }
        this.grid = new boolean[n][n];
        //init the value of each item in the grid to be blocked
        for(int i=0;i<this.grid.length;i++){
            for(int j=0;j<this.grid.length;j++){
                this.grid[i][j] = false;
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

    }     // create n-by-n grid, with all sites blocked
    public void open(int row, int col) throws IndexOutOfBoundsException{
        if( row >= this.grid.length || col >= this.grid.length){
            throw new IndexOutOfBoundsException();
        }
        if(!this.grid[row][col]){
            this.grid[row][col]=true;
        }
    }    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException{
        if( row >= this.grid.length || col >= this.grid.length){
            throw new IndexOutOfBoundsException();
        }
        return this.grid[row][col];
    }  // is site (row, col) open?
    public boolean isFull(int row, int col) throws IndexOutOfBoundsException{
        if( row >= this.grid.length || col >= this.grid.length){
            throw new IndexOutOfBoundsException();
        }
        return this.connected(0,row*this.grid.length+col+1);
    }  // is site (row, col) full?
    public int numberOfOpenSites(){
        int num=0;
        for(boolean[] row:this.grid){
            for(boolean item:row){
                if(item){
                    num+=1;
                }
            }
        }
        return num;
    }       // number of open sites
    public boolean percolates(){
        return this.connected(0,this.grid.length*this.grid.length+1);
    }              // does the system percolate?

    public static void main(String[] args){

    }   // test client (optional)
}
