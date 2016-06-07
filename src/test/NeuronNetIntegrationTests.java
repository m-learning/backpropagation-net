package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.NeuronNet;

public class NeuronNetIntegrationTests {

	public static void makeDecission_trainNeuron() {
		List<Integer> list = new ArrayList<Integer>();

		list.add(40);
		list.add(40);
		list.add(1);

		NeuronNet net = new NeuronNet(list, 3);

		List<Double> x1 = new ArrayList<Double>(Arrays.asList(0.4d, 0.1d, 0.2d));
		List<Double> d1 = new ArrayList<>(Arrays.asList(0.21d));

		List<Double> x2=new ArrayList<Double>(Arrays.asList(0.2d, 0.5d, 0.7d));
		List<Double> d2 = new ArrayList<>(Arrays.asList(0.42));
		
		List<Double> x3 = new ArrayList<Double>(Arrays.asList(1d, 1d, 1d));
		List<Double> d3 = new ArrayList<>(Arrays.asList(0.9d));
		
		List<Double> x4 = new ArrayList<Double>(Arrays.asList(0d, 0d, 0d));
		List<Double> d4 = new ArrayList<>(Arrays.asList(0d));
		
		List<Double> x5 = new ArrayList<Double>(Arrays.asList(0.1d, 0.2d, 0.1d));
		List<Double> d5 = new ArrayList<>(Arrays.asList(0.12d));

		for (int i = 0; i < 1000; i++) {
			//net.trainNet(x1, d1);
			net.trainNet(x2, d2);
			net.trainNet(x3, d3);
			net.trainNet(x4, d4);
			net.trainNet(x5, d5);
		}

		
		
		List<Double> results = net.makeDecision(x1);
		System.out.println(results.get(0));

		results = net.makeDecision(x2);
		System.out.println(results.get(0));
		
		results = net.makeDecision(x3);
		System.out.println(results.get(0));
		
		results = net.makeDecision(x4);
		System.out.println(results.get(0));

		results = net.makeDecision(x5);
		System.out.println(results.get(0));
		//		System.out.println(net.toString());

	}

	public static void main(String[] args) {
		makeDecission_trainNeuron();
	}

}
