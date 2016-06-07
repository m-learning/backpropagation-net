package main; /**
 * @author irakli on 5/29/2015.
 */

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private List<Double> x = new ArrayList<Double>();

    private List<Double> w = new ArrayList<Double>();

    private ActivationFunction activationFunction = new Sigmoid();

    private Double v;

    private Double y;

    private Double delta;

    public Neuron() {

    }

    public Neuron(ActivationFunction f) {
        activationFunction = f;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public Double makeDecision(List<Double> x) {
        this.x.clear();
        this.x.addAll(x);
        this.x.add(-1d);


        setV(0d);

        for (int i = 0; i < this.x.size(); i++) {
            setV(getV() + w.get(i) * this.x.get(i));
        }

        y = activationFunction.f(getV());

        return y;
    }

    public Integer getN() {
        return w.size();
    }

    public Double getY() {
        return y;
    }

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x.clear();
        this.x.addAll(x);
        this.x.add(-1d);
    }

    public List<Double> getW() {
        return w;
    }

    public void setW(List<Double> w) {
        this.w.clear();
        this.w.addAll(w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("x(");

        for (int i = 0; i < x.size(); i++) {
            sb.append(x.get(i));
            sb.append(", ");
        }

        sb.append(") ");

        sb.append("w(");

        for (int i = 0; i < w.size(); i++) {
            sb.append(w.get(i));
            sb.append(", ");
        }

        sb.append(") ");

        sb.append("y = ");
        sb.append(y);

        return sb.toString();
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }
}

