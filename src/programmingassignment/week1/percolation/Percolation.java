package programmingassignment.week1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @Author george.liu on 16/1/28
 */
public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int top;
    private int bottom;
    private int[][] grids;
    private int N;
    private int blocked = 0;

    public Percolation(int N) {              // create N-by-N grid, with all sites blocked
        if (N <= 0) {
            throw new IllegalArgumentException("N必须大于1");
        }
        this.N = N;
        grids = new int[N][N];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grids[i][j] = blocked;
            }
        }
        top = N * N;
        bottom = N * N + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int i, int j) {         // open site (row i, column j) if it is not open already
        if (1 > i || i > N) throw new IllegalArgumentException("i 必须大于1小于" + N);
        if (j > i || j > N) throw new IllegalArgumentException("j 必须大于1小于" + N);

        int p = location(i, j);
        int q = 0;
        if (!isOpen(i, j)) {
            if (i == 1) {
                q = top;
                weightedQuickUnionUF.union(p, q);
            }
            if (i == N) {
                q = bottom;
                weightedQuickUnionUF.union(p, q);
            }
            grids[i][j] = (i - 1) * N + j;//open
            //上下左右,如果有是open则union
            //上
            if (i - 1 >= 1 && isOpen(i - 1, j)) {
                q = location(i - 1, j);
                weightedQuickUnionUF.union(p, q);
            }
            //下
            if (i + 1 <= N && isOpen(i + 1, j)) {
                q = location(i - 1, j);
                weightedQuickUnionUF.union(p, q);
            }
            //左
            if (j - 1 >= 1 && isOpen(i, j - 1)) {
                q = location(i, j - 1);
                weightedQuickUnionUF.union(p, q);
            }
            //右
            if (j + 1 <= N && isOpen(i, j + 1)) {
                q = location(i, j + 1);
                weightedQuickUnionUF.union(p, q);
            }
        }
    }

    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        if (1 > i || i > N) throw new IllegalArgumentException("i 必须大于1小于" + N);
        if (j > i || j > N) throw new IllegalArgumentException("j 必须大于1小于" + N);
        return grids[i][j] == blocked;
    }

    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        if (1 > i || i > N) throw new IllegalArgumentException("i 必须大于1小于" + N);
        if (j > i || j > N) throw new IllegalArgumentException("j 必须大于1小于" + N);
        int p = location(i, j);
        if (weightedQuickUnionUF.connected(p, top) && weightedQuickUnionUF.connected(p, bottom)) {
            return true;
        }
        return false;
    }

    public boolean percolates() {           // does the system percolate?
        return false;
    }


    private int location(int i, int j) {      // 2纬 -> 1纬
        return (i - 1) * N + j;
    }

    public static void main(String[] args) {
    }  // test client (optional)
}
