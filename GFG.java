/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

// Implementing Gauss Seidel Method in Java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
class GFG {
    // we set a max number of iterations to
    // prevent an infinite loop
    public static final int MAX_ITERATIONS = 100;
    private double[][] M;
    public GFG(double[][] matrix) { M = matrix; }
 
    public void print() // printing
    {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }
    // attempting to change a matrix to dominant
    // if proved that it is not
    public boolean transformToDominant(int r, boolean[] V,
                                       int[] R)
    {
        int n = M.length;
        if (r == M.length) {
            double[][] T = new double[n][n + 1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = M[R[i]][j];
            }
            M = T;
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (V[i])
                continue;
            double sum = 0;
            for (int j = 0; j < n; j++)
                sum += Math.abs(M[i][j]);
            if (2 * Math.abs(M[i][r]) > sum) {
                // diagonally dominant?
                V[i] = true;
                R[r] = i;
                if (transformToDominant(r + 1, V, R))
                    return true;
                V[i] = false;
            }
        }
        return false;
    }
    // method to check whether matrix is
    // diagonally dominant or not
    public boolean makeDominant()
    {
        boolean[] visited = new boolean[M.length];
        int[] rows = new int[M.length];
        Arrays.fill(visited, false);
        return transformToDominant(0, visited, rows);
    }
 
    // method to find the solution of the matrix
    // after all conditions are satisfied
    public void solve()
    {
        int iterations = 0;
        int n = M.length;
        double epsilon = 1e-15;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);
        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = M[i][n]; // b_n
                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * X[j];
                // Update xi to use in the next
                // row calculation
                X[i] = 1 / M[i][i] * sum;
            }
            System.out.print("X" + iterations + " = {");
            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");
            iterations++;
            if (iterations == 1)
                continue;
            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;
            if (stop || iterations == MAX_ITERATIONS)
                break;
            P = (double[])X.clone();
        }
    }
 
    public static void main(String[] args)
        throws IOException
    {
        PrintWriter writer
            = new PrintWriter(System.out, true);
        int n = 2, k = 1;
        double[][] M = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                M[i][j] = k++;
        }
        GFG gausSeidel = new GFG(M);
        if (!gausSeidel.makeDominant()) {
 
            // if it is found that a matrix cannot be
            // changed into diagonally dominant then we
            // return the message to the user
 
            writer.println(
                "The system isn't diagonally dominant: "
                + "The method cannot guarantee convergence.");
        }
        writer.println();
        gausSeidel.print();
        gausSeidel.solve();
    }
}
