/**
 * File: Dictionary.c
 * Name: Sophia Tacderas
 * ID: 1465379
 * Due: 12/2/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa5
 * Purpose: Implements Dictionary.h with using a hash table.
 * Borrows/modifies code from: Dictionary.c, lab5
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

const int tableSize = 101; // constant table size

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
    /*
     * Several methods for resolving collisions will be discussed in class. The method that we will use in this
     * project is called chaining, and is perhaps the simplest collision resolution technique. In chaining, we put
     * all the pairs that hash to the same slot into a linked list.
     *
     * Thus the hash table T[0…(m −1)] is an array of
     * linked lists. More precisely, T[ j] is a pointer to the head of a linked list storing all pairs that hash to slot
     * j. If there are no such elements, T[ j] will be NULL.
     */
    Node* hashTable;
    int numItems;
} DictionaryObj;


// public functions -----------------------------------------------------------

/*
 * Functions rotate_left() and pre_hash() turn a string into an unsigned int, and function
 * hash() converts that number into an int in the range 0 to tableSize −1.
 */
// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while (*input) {
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
    Dictionary myDict = malloc(sizeof(DictionaryObj));
    assert(myDict!=NULL);
    myDict->hashTable = calloc(tableSize, sizeof(Node));
    assert(myDict->hashTable != NULL);
    myDict->numItems = 0;
    return myDict;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if( pD!=NULL && *pD!=NULL ){
        if( !isEmpty(*pD) ){
            makeEmpty(*pD);
        }
        free((*pD)->hashTable); // free hash table
        free(*pD); // free DictionaryObj
        *pD = NULL;
    }
}

// findKey()
// Returns a reference to the Node containing its argument key from the hash table.
// Otherwise, returns NULL if that Node does not exist.
Node findKey(Dictionary D, char* key) {
    int intKey = hash(key); // get int representation of argument key
    Node N = D->hashTable[intKey]; // find Node for equivalent key in hash table
    /*
    * To lookup a given key k, we do a linear search of the list T[h(k)], and return the corresponding value if
    * found, or NULL if not found.
    */
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

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    // use findKey() to find the Node
    Node N = findKey(D, k);
    if (N != NULL) {
        return N->value;
    } else {
        return NULL;
    }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    if(lookup(D, k) == NULL) { // arguments are not duplicate
        // hash argument key
        int intKey = hash(k);

        // find Node for equivalent key in hash table
        Node N = D->hashTable[intKey];

        if (N == NULL) {
            // table is empty so this is first item
            /*
            * To insert a pair (k, v) into the Dictionary,
            * we create a new Node storing this pair, then insert that Node at the head of the linked list T[h(k)].
            */
            Node insertNode = newNode(k, v);
            insertNode->next = D->hashTable[intKey];
            D->hashTable[intKey] = insertNode;
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
    if( lookup(D, k)==NULL ){
        fprintf(stderr, "Dictionary Error: delete() cannot delete non-existent key.\n");
        exit(EXIT_FAILURE);
    }

    // hash argument key
    int intKey = hash(k);

    // find Node for equivalent key in hash table
    Node N = D->hashTable[intKey];

    /*
    * To delete the pair with key k, simply splice the corresponding Node out of the list headed by T[h(k)].
    */
    if (strcmp(N->key, k) == 0) {
        D->hashTable[intKey] = N->next;
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
    int ctr = 0;
    for (ctr = 0; ctr < tableSize; ctr++) {
        Node N = D->hashTable[ctr];
        if (N != NULL) {
            Node temp = N->next;
            D->hashTable[ctr] = temp;
            freeNode(&N);
        }
    }
    D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    /*
     * Your printDictionary() function in this project should
     * just print out pairs in the order that they appear in the table, i.e. print out list T[0] in order
     * (i.e. head to tail), then list T[1], …, then end by printing list T[m −1].
     */
    if( D==NULL ){
        fprintf(stderr,
                "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    int ctr = 0;
    for (ctr = 0; ctr < tableSize; ctr++) {
        Node N = D->hashTable[ctr];
        if (N != NULL) {
            fprintf(out, "%s %s\n", N->key, N->value);
        }
    }
}
