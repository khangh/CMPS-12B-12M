/**
 * File: ListTest.java
 * Name: Sophia Tacderas
 * ID: 1465379
 * Due: 11/18/16, 10 pm
 * Class: CMPS 12M
 * Assignment: lab 6
 * Purpose: Tests the Java Generics Linked List implementation.
 * Borrows/modifies code from: IntegerListTest.java
 */
import java.lang.String;

public class ListTest {
    public static void main(String[] args){
        List<Integer> A = new List<Integer>();
        List<Integer> B = new List<Integer>();
        int i, j;

        for(i=1; i<=100; i++){
            j = i*i;
            A.add(i, j);
            B.add(i, (j+i)/2);
        }

        try{
            System.out.println(A.get(200));
        }catch(ListIndexOutOfBoundsException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption");
        }
        try{
            A.add(200, 8);
        }catch(ListIndexOutOfBoundsException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption");
        }
        try{
            A.remove(200);
        }catch(ListIndexOutOfBoundsException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption");
        }
        System.out.println();

        System.out.println(A);
        System.out.println(A.toString());
        System.out.println();
        System.out.println(B);
        System.out.println(B.toString());
        System.out.println();

        System.out.println("A = B? " + A.equals(B));
        System.out.println("Is A empty? " + A.isEmpty());
        System.out.println("Is B empty? " + B.isEmpty());
        System.out.println();

        System.out.println("Size of A = " + A.size());
        System.out.println();
        System.out.println("Size of B = " + B.size());
        System.out.println();

        for(i=1; i<=10; i++){
            A.remove(9*i);
            B.remove(8*i-3);
        }

        System.out.println("After remove, size of A = " + A.size());
        System.out.println();
        System.out.println("After remove, size of B = " + B.size());
        System.out.println();
        System.out.println("B.get(37) = " + B.get(37));
        System.out.println();
        System.out.println("A.get(20) = " + A.get(20));
        System.out.println();

        A.removeAll();
        B.removeAll();
        System.out.println("removeAll() called");
        System.out.println("A = B? " + A.equals(B));
        System.out.println("Is A empty? " + A.isEmpty());
        System.out.println("Is B empty? " + B.isEmpty());
        System.out.println();

        List<Character> C = new List<Character>();
        List<Character> D = new List<Character>();
        List<StringBuffer> E = new List<StringBuffer>();
        List<StringBuffer> F = new List<StringBuffer>();
        int k;
        char m, n;

        for(k=65; k<=90; k++){
            m = (char) k;
            n = (char) (k + 32);
            StringBuffer str1 = new StringBuffer();
            StringBuffer str2 = new StringBuffer();
            str1.append(m).append(m);
            str2.append(n).append(n);
            C.add(k-64, m);
            D.add(k-64, n);
            E.add(k-64, str1);
            F.add(k-64, str2);
        }

        System.out.println("Testing other generic types (char and StringBuffer).");
        System.out.println(C);
        System.out.println(D);
        System.out.println(E);
        System.out.println(F);
        System.out.println("End test");
    }
}
