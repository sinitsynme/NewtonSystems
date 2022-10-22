package ru.sinitsynme.newtonsystems.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import ru.sinitsynme.newtonsystems.dto.EquationSystemRequestDto;
import ru.sinitsynme.newtonsystems.dto.EquationSystemResponseDto;
import ru.sinitsynme.newtonsystems.exception.ErrorRateException;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


@Service
public class SystemCalculationByNewtonService implements SystemCalculationService {

    private static final double INITIAL_X = 1;
    private static final double INITIAL_Y = 2;

    private final double dx = 1e-10;

    @Override
    public EquationSystemResponseDto solveSystem(EquationSystemRequestDto system) {
        Expression f1 = formFunction(system.getFunc1());
        Expression f2 = formFunction(system.getFunc2());

        double errorRate = system.getErrorRate();

        if (errorRate <= 0 || errorRate >= 0.1){
            throw new ErrorRateException("Error rate must be positive and less than 0.1!");
        }

        double[] xk;
        double[] xk1 = new double[]{INITIAL_X, INITIAL_Y};
        double[] delta;
        double norm;

        do{
            xk = xk1.clone();

            double f1xk = f1.setVariable("x", xk[0]).setVariable("y", xk[1]).evaluate();
            double f2xk = f2.setVariable("x", xk[0]).setVariable("y", xk[1]).evaluate();

            double[] fxkNegative = new double[]{-f1xk, -f2xk};

            double[][] jacobian = calculateJacobian(xk, new Expression[]{f1, f2});

            delta = gaussSolve(jacobian, fxkNegative);

            xk1[0] = xk[0] + delta[0];
            xk1[1] = xk[1] + delta[1];

            norm = sqrt(pow(xk1[0] - xk[0], 2) + pow(xk1[1] - xk[1], 2));

        } while (norm >= errorRate);

        return new EquationSystemResponseDto(xk1[0], xk1[1]);
    }


    private Expression formFunction(String func){
        try {
            return new ExpressionBuilder(func).variables("x", "y").build();
        }
        catch (Exception e){
            throw new IllegalArgumentException("Only variables X and Y are allowed!");
        }
    }

    private double[][] calculateJacobian(double[] x, Expression[] f){
        double[][] jacobian = new double[f.length][x.length];

        for (int i = 0; i < f.length; i++){
            for (int j = 0; j < x.length; j++){
                jacobian[i][j] = derivative(f[i], x, j);
            }
        }

        return jacobian;
    }

    private double derivative(Expression f, double[] x, int derivedVariable){

        double fx = f.setVariable("x", x[0]).setVariable("y", x[1]).evaluate();

        double fdx;

        if(derivedVariable == 0){
            fdx = f.setVariable("x", x[0] + dx).setVariable("y", x[1]).evaluate();
        }
        else{
            fdx = f.setVariable("x", x[0]).setVariable("y", x[1] + dx).evaluate();
        }

        return (fdx - fx) / dx;
    }

    private double[] gaussSolve(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (abs(A[i][p]) > abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            if (Math.abs(A[p][p]) <= dx) {
                throw new ArithmeticException("System is inconsistent!");
            }

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }




}
