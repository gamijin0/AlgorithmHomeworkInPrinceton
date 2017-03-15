import edu.princeton.cs.algs4.StdRandom;
import java.lang.Integer;

public class PercolationStats {
    private double[] statisticData;
    public PercolationStats(int n, int trials) {
        if(n<=0 || trials<=0){
            throw new IllegalArgumentException();
        }
        this.statisticData = new double[trials]; //to store the threshold of each trail
        for(int i=0;i<trials;i++){
            Percolation one = new Percolation(n);
            int records = 0;
            while (!one.percolates()){
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                while (one.isOpen(row,col)){
                    row = StdRandom.uniform(n);
                    col = StdRandom.uniform(n);
                }
                one.open(row,col);
                    records += 1;
            }
            this.statisticData[i]=records/(n*n*1.0);
        }

    }   // perform trials independent experiments on an n-by-n grid
    public double mean(){
        double sum=0;
        for (double v:this.statisticData){
            sum+=v;
        }
        return sum*1.0/this.statisticData.length;
    }   // sample mean of percolation threshold
    public double stddev(){
        double mean = this.mean();
        double std2 =0;
        for(double t:this.statisticData){
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
        PercolationStats one =new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.printf("mean                     = %f\n",one.mean());
        System.out.printf("stddev                   = %f\n",one.stddev());
        System.out.printf("95%% confidence interval  = [%f,%f]\n",one.confidenceLo(),one.confidenceHi());
    }        // test client (described below)
}
