package main;

/**
 * @author irakli on 5/29/2015.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuronNet {
	private List<List<Neuron>> neurons;

	public static final double miu = 0.5;

	public NeuronNet(List<Integer> layerNeuronCounts, Integer inputCount) {
		neurons = new ArrayList<List<Neuron>>();

		for (int i = 0; i < layerNeuronCounts.size(); i++) {
			List<Neuron> list = new ArrayList<Neuron>();

			for (int j = 0; j < layerNeuronCounts.get(i); j++) {
				list.add(new Neuron());
			}

			neurons.add(list);
		}

		setRandomWeights(inputCount + 1);
	}

	private void setRandomWeights(int inputCount) {
		Random rand = new Random(20);

		for (int i = 0; i < neurons.size(); i++) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				Neuron currentNeuron = neurons.get(i).get(j);

				List<Double> weights = new ArrayList<Double>();

				for (int j2 = 0; j2 < inputCount; j2++) {
					weights.add(rand.nextDouble()*8 - 4);
				}

				currentNeuron.setW(weights);
			}

			inputCount = neurons.get(i).size() + 1;
		}
	}

	public void trainNet(List<Double> x, List<Double> d) {
		int lastLayerIndex = neurons.size() - 1;

		assert d.size() == neurons.get(lastLayerIndex).size(); // last layer
																// neurons count

		List<Double> inputs = new ArrayList<Double>();

		inputs.addAll(x);

		makeAndSaveDecision(inputs);

		calculateDeltas(d, lastLayerIndex);

		for (int i = 0; i < neurons.size(); i++) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				Neuron currentNeuron = neurons.get(i).get(j);

				List<Double> newW = new ArrayList<Double>();

				for (int k = 0; k < currentNeuron.getW().size(); k++) {
					newW.add(currentNeuron.getW().get(k) + miu
							* currentNeuron.getDelta()
							* currentNeuron.getX().get(k));
				}

				currentNeuron.setW(newW);
			}
		}

	}

	private void makeAndSaveDecision(List<Double> x) {
		List<Double> parameters = new ArrayList<Double>();

		parameters.addAll(x);

		List<Double> answers = new ArrayList<Double>();

		for (int i = 0; i < neurons.size(); i++) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				Double decision = neurons.get(i).get(j)
						.makeDecision(parameters);

				answers.add(decision);
			}

			parameters.clear();
			parameters.addAll(answers);
			answers.clear();
		}
	}

	public List<Double> makeDecision(List<Double> x) {
		assert x.size() % (neurons.get(0).size()) == 0;

		makeAndSaveDecision(x);

		List<Double> result = new ArrayList<Double>();

		for (int i = 0; i < neurons.get(neurons.size() - 1).size(); i++) {
			result.add(neurons.get(neurons.size() - 1).get(i).getY());
		}

		return result;
	}

	private void calculateDeltas(List<Double> d, int lastLayerIndex) {
		for (int i = 0; i < neurons.get(lastLayerIndex).size(); i++) {
			Neuron currentNeuron = neurons.get(lastLayerIndex).get(i);

			Double e = d.get(i) - currentNeuron.getY();

			Double delta = e * currentNeuron.getActivationFunction().derivative(currentNeuron.getV());

			currentNeuron.setDelta(delta);
		}

		for (int i = lastLayerIndex - 1; i >= 0; i--) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				Double previousResultsSum = 0d;

				for (int k = 0; k < neurons.get(i + 1).size(); k++) {
					Neuron previousLayerNeuron = neurons.get(i + 1).get(k);

					previousResultsSum += previousLayerNeuron.getDelta()
							* previousLayerNeuron.getW().get(j);
				}

				Neuron currentNeuron = neurons.get(i).get(j);

				Double delta = currentNeuron.getActivationFunction()
						.derivative(currentNeuron.getV()) * previousResultsSum;

				currentNeuron.setDelta(delta);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (List<Neuron> neuron : neurons) {
			for (Neuron neuron2 : neuron) {
				builder.append(neuron2.toString());
				builder.append(' ');
			}
			builder.append("\n");
		}

		return builder.toString();
	}
}
