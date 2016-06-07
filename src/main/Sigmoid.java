package main; /**
 * @author irakli on 5/29/2015.
 */
import main.ActivationFunction;

import java.util.TreeMap;

public class Sigmoid implements ActivationFunction {

    TreeMap<Double, Double> map = new TreeMap<Double, Double>();

    @Override
    public Double f(Double x) {
        if (map.containsKey(x)) {
            return map.get(x);
        }
        Double result = 1 / (1 + Math.exp(-x));

        if (map.size() > 5) {
            map.remove(map.lastKey());
        }
        map.put(x, result);
        return result;
    }

    @Override
    public Double derivative(Double x) {
        return f(x) * (1 - f(x));
    }

}

