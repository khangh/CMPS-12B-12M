/**
 * File: KeyNotFoundException.java
 * Name: Sophia Tacderas, Maricris Bonzo
 * Due: 11/4/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa3
 * Purpose: Throws exception if key is unable to be found.
 * Borrowed from Examples\Lecture\IntegerListADT\ArrayWithExceptionsInterface\ListFullException.java,
 * subclass of RuntimeException
 */
public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String s) {
        super(s);
    }
}
