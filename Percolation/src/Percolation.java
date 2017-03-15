import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean grid[][]; //false is blocked, true is opened
    private int getIndex(int row,int col){
        return row*this.grid.length+col+1;
    }
    public Percolation(int n) {
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n*n+2);//add a virtual top(0) and bottom(n*n+1) side
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
            this.weightedQuickUnionUF.union(0,i);
        }
        //union the last floor sides with the virtual bottom side
        for(int i=n*n-n+1;i<n*n+1;i++){
            this.weightedQuickUnionUF.union(n*n+1,i);
        }

    }     // create n-by-n grid, with all sites blocked
    public void open(int row, int col) {
        row = row -1;
        col = col -1;
        if( row >= this.grid.length || col >= this.grid.length || row < 0 ||col < 0){
            throw new IndexOutOfBoundsException();
        }
        if(!this.isOpen(row,col)){
            if(row>0 ){
                if(this.isOpen(row-1,col))
                    this.weightedQuickUnionUF.union(getIndex(row,col),getIndex(row-1,col));
            }
            if(col>0 ){
                if(this.isOpen(row,col-1))
                    this.weightedQuickUnionUF.union(getIndex(row,col),getIndex(row,col-1));
            }
            if(row<this.grid.length-1){
                if(this.isOpen(row+1,col))
                    this.weightedQuickUnionUF.union(getIndex(row,col),getIndex(row+1,col));
            }
            if(col<this.grid.length-1 ){
                if(this.isOpen(row,col+1))
                    this.weightedQuickUnionUF.union(getIndex(row,col),getIndex(row,col+1));
            }
            this.grid[row][col]=true;
        }
    }    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        row = row -1;
        col = col -1;
        if( row >= this.grid.length || col >= this.grid.length || row < 0 ||col < 0){
            throw new IndexOutOfBoundsException();
        }
        return this.grid[row][col];
    }  // is site (row, col) open?
    public boolean isFull(int row, int col) {
        row = row -1;
        col = col -1;
        if( row >= this.grid.length || col >= this.grid.length || row < 0 ||col < 0){
            throw new IndexOutOfBoundsException();
        }
        if(this.isOpen(row,col))
            return this.weightedQuickUnionUF.connected(0,this.getIndex(row,col));
        else
            return false;
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
        return this.weightedQuickUnionUF.connected(0,this.grid.length*this.grid.length+1);
    }              // does the system percolate?

    public static void main(String[] args){

    }   // test client (optional)
}
