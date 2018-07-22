package com.code.challenge.transactions.model;

public class Statistics {

    private Double sum;
    private Double avg;
    private Double max;
    private Double min;
    private Long count;

    public Statistics() {
        this.sum = 0.0;
        this.avg = 0.0;
        this.max = 0.0;
        this.min = 0.0;
        this.count = 0L;
    }

    public Statistics(Double sum, Double avg, Double max, Double min, Long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Long getCount() {
        return count;
    }

}
