package dev;

/**
 * Validatable interface to determine whether
 * the invariant is valid at any state
 */
public interface IValidator {

    /**
     * @return validity or correctness
     */
    boolean validate();

}
