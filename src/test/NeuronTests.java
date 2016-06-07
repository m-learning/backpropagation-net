package test;

/**
 * @author irakli on 5/29/2015.
 */

import main.Neuron;

import java.util.ArrayList;
import java.util.List;

public class NeuronTests {

	public static void makeDecision_NormalOperation_ReturnsHalf() {
		Neuron neuron = new Neuron();

		List<Double> w = new ArrayList<Double>();

		w.add(5d);
		w.add(5d);
		w.add(0d);

		neuron.setW(w);

		List<Double> x = new ArrayList<Double>();

		x.add(10d);
		x.add(-10d);

		System.out.println(Math.abs(1 / 2d - neuron.makeDecision(x)
				.doubleValue()) <= 1e-15);
	}

	public static void main() {
		makeDecision_NormalOperation_ReturnsHalf();
	}

}
