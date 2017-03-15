/**
 * Created by 49738 on 2017/3/15.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int[] statisticData;
    public PercolationStats(int n, int trials) {
        this.statisticData = new int[n]; //to store the threshold of each trail
        for(int i=0;i<trials;i++){
            Percolation one = new Percolation(n);
            int records = 0;
            while (!one.percolates()){
                one.open(StdRandom.uniform(n),StdRandom.uniform(n));
                records += 1;
            }
            this.statisticData[i]=records;
        }

    }   // perform trials independent experiments on an n-by-n grid
    public double mean(){
        int sum=0;
        for (int v:this.statisticData){
            sum+=v;
        }
        return sum*1.0/this.statisticData.length;
    }                          // sample mean of percolation threshold
    public double stddev(){
        double mean = this.mean();
        double std2 =0;
        for(int t:this.statisticData){
            std2 += (t-mean)*(t-mean)/(this.statisticData.length-1.0);
        }
        return Math.sqrt(std2);
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo(){
        return this.mean()-1.96*this.stddev()/Math.sqrt(this.statisticData.length);
    }                  // low  endpoint of 95% confidence interval
    public double confidenceHi(){
        return this.mean()+1.96*this.stddev()/Math.sqrt(this.statisticData.length);
    }                  // high endpoint of 95% confidence interval

    public static void main(String[] args){

    }        // test client (described below)
}
