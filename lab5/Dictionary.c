/**
 * File: Dictionary.c
 * Name: Sophia Tacderas, Maricris Bonzo
 * Due: 11/10/16, 10 pm
 * Class: CMPS 12M
 * Assignment: lab 5
 * Purpose: Implements Dictionary.h with the 7 ADT operations.
 * Borrows/modifies code from: IntegerStack.c, Dictionary.java
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->key = k;
    N->value = v;
    N->next = NULL;
    return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
    if( pN!=NULL && *pN!=NULL ){
        free(*pN);
        *pN = NULL;
    }
}

// DictionaryObj
typedef struct DictionaryObj{
    Node head;
    int numItems;
} DictionaryObj;


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
    Dictionary myDict = malloc(sizeof(DictionaryObj));
    assert(myDict!=NULL);
    myDict->head = NULL;
    myDict->numItems = 0;
    return myDict;
}

// findKey()
// helper function for insert(), delete(), lookup()
// Returns a reference to the Node containing its argument key.
// Otherwise, returns null if that Node does not exist.
Node findKey(Dictionary D, char* key) {
    Node N = D->head;
    if(N != NULL) {
        int ctr = 0;
        for (ctr = 1; ctr <= D->numItems; ctr++) {
            if (strcmp(N->key, key)==0){
                return N;
            } else {
                N = N->next;
            }
        }
    }
    return NULL;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if( pD!=NULL && *pD!=NULL ){
        if( !isEmpty(*pD) ){
            makeEmpty(*pD);
        }
        free(*pD);
        *pD = NULL;
    }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    if( D==NULL ){
        fprintf(stderr,
                "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D) {
    return D->numItems;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    if(lookup(D, k) == NULL) {

        // this means arguments are not duplicate
        Node N = D->head;

        if (N == NULL) {
            // list is empty so this is first item
            Node insertNode = newNode(k, v);
            insertNode->next = D->head;
            D->head = insertNode;
        } else {
            // insert item at the end of list
            int ctr = 0;
            for (ctr = 1; ctr < D->numItems; ctr++) {
                N = N->next;
            }
            N->next = newNode(k, v);
        }
        D->numItems++;

    } else {
        fprintf(stderr, "Calling cannot insert duplicate keys: %s\n", k);
        exit(EXIT_FAILURE);
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
    if( findKey(D, k)==NULL ){
        fprintf(stderr, "Dict. err: delete() cannot delete non-existent key.\n");
        exit(EXIT_FAILURE);
    }

    Node N = D->head;

    if (strcmp(N->key, k) == 0) {
        D->head = N->next;
        N->next = NULL;
        freeNode(&N);
    } else {
        Node prev = N;
        Node temp = N->next;

        while (temp->key != k) {
            temp = temp->next;
            prev = prev->next;
        }

        prev->next = temp->next;
        temp->next = NULL;

        freeNode(&temp);
    }
    D->numItems--;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    // use findKey() to find the Node
    Node N = findKey(D, k);

    // if returned value of findKey() is not null then it was found
    if (N != NULL) {
        int ctr = 0;
        for (ctr = 1; ctr <= D->numItems; ctr++) {
            if (strcmp(N->key, k)==0){
                // return value when found
                return N->value;
            } else {
                N = N->next;
            }
        }
    }

    // if no such pair exists in the Dictionary then return null
    return NULL;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
    if( D==NULL ){
        fprintf(stderr,
                "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( D->numItems==0 ){
        fprintf(stderr, "Dictionary Error: calling makeEmpty() on empty Dictionary\n");
        exit(EXIT_FAILURE);
    }
    D->head = NULL;
    D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    Node N;
    if( D==NULL ){
        fprintf(stderr,
                "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    for(N=D->head; N!=NULL; N=N->next) {
        fprintf(out, "%s %s\n", N->key, N->value);
    }
}
