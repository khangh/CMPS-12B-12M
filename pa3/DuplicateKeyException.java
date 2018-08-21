/**
 * File: DuplicateKeyException.java
 * Name: Sophia Tacderas, Maricris Bonzo
 * Due: 11/4/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa3
 * Purpose: Throws exception if inserting a duplicate key.
 * Borrowed from Examples\Lecture\IntegerListADT\ArrayWithExceptionsInterface\ListFullException.java,
 * subclass of RuntimeException
 */
public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException(String s) {
        super(s);
    }
}
