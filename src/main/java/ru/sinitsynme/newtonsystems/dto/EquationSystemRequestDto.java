package ru.sinitsynme.newtonsystems.dto;

public class EquationSystemRequestDto {

    private String func1;
    private String func2;
    private double errorRate;

    public EquationSystemRequestDto() {
    }

    public EquationSystemRequestDto(String func1, String func2, double errorRate) {
        this.func1 = func1;
        this.func2 = func2;
        this.errorRate = errorRate;
    }

    public String getFunc1() {
        return func1;
    }

    public void setFunc1(String func1) {
        this.func1 = func1;
    }

    public String getFunc2() {
        return func2;
    }

    public void setFunc2(String func2) {
        this.func2 = func2;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }

    @Override
    public String toString() {
        return "EquationSystemRequestDto{" +
                "func1='" + func1 + '\'' +
                ", func2='" + func2 + '\'' +
                ", errorRate=" + errorRate +
                '}';
    }
}
