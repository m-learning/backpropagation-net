package main;

/**
 * @author irakli on 5/29/2015.
 */
public interface ActivationFunction {

    Double f(Double parameter);

    Double derivative(Double parameter);
}
