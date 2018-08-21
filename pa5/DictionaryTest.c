/**
 * File: DictionaryTest.c
 * Name: Sophia Tacderas
 * ID: 1465379
 * Due: 12/2/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa5
 * Purpose: Tests Dictionary.c (which implements Dictionary.h but with a hash table).
 * Based off of my DictionaryTest.c file from lab 5 and pa5's DictionaryClient.c.
 */

#include <stdio.h>
#include "Dictionary.h"

int main(void) {
    // New constructor
    Dictionary myDict = newDictionary();

    printf("isEmpty() = %d\n", isEmpty(myDict)); // should be 1 bc true
    printf("Number of items: %d\n\n", size(myDict)); // should be 0
    printf("Print empty Dictionary:\n\n");
    printDictionary(stdout, myDict);

    insert(myDict, "1", "First");
    printf("Inserted first item.\n");
    printf("isEmpty() = %d\n", isEmpty(myDict)); // should be 0
    printf("Number of items: %d\n\n", size(myDict)); // should be 1

    insert(myDict, "2", "Second");
    printf("Inserted second item.\n");
    printf("isEmpty() = %d\n", isEmpty(myDict)); // should be 0
    printf("Number of items: %d\n\n", size(myDict)); // should be 2

    // printf("Inserting third item (2, Third) (should error).");
    // insert(myDict, "2", "Third"); // should error bc duplicate key

    insert(myDict, "3", "Third");
    printf("Inserted third item.\n");
    printf("isEmpty() = %d\n", isEmpty(myDict)); // should be 0
    printf("Number of items: %d\n\n", size(myDict)); // should be 3

    // printf("Inserting item with key 4 (should error).\n");
    // delete(myDict, "4"); // should error bc key not found

    delete(myDict, "3");
    printf("Deleted third item.\n");
    printf("isEmpty() = %d\n", isEmpty(myDict)); // should be 0
    printf("Number of items: %d\n\n", size(myDict)); // should be 2

    printf("Value associated with key 1: %s\n", lookup(myDict, "1")); // should be "First"
    printf("Value associated with key 2: %s\n", lookup(myDict, "2")); // should be "Second"
    printf("Value associated with key 3: %s\n\n", lookup(myDict, "3")); // should show null

    printf("Printing out dictionary:\n");
    printDictionary(stdout, myDict);
    printf("\n");
    // Should display: 2 Second
    //                 1 First
    // Order is based on hash key generated.

    makeEmpty(myDict);
    printf("makeEmpty() was called.\n");
    printf("Number of items: %d\n", size(myDict)); // should be 0
    printf("isEmpty() = %d\n", isEmpty(myDict)); // should be 1

    freeDictionary(&myDict); // free ptr
    return 0;
}
