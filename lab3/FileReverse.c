// -----------------------------------------------------------------------------
// File: FileReverse.c
// Name: Sophia Tacderas
// Due: 10/21/16, 10 pm
// Class: CMPS 12M
// Assignment: lab 3
// Purpose: Reads input file and prints each word backwards on a separate line of
// the output file.
// Borrows/modifies code from: FileIO.c, pseudocode from lab manual
// ------------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void stringReverse(char* string) {
    int left = 0;
    int right = strlen(string) - 1;
    for (left = 0; left < right; left++) {
        char temp = string[left];
        string[left] = string[right];
        string[right] = temp;
        right--;
    }
}

int main(int argc, char * argv[]){
    FILE *inFile, *outFile;   // handles for input and output files
    char word[256];   // char array to store words from input file

    // check command line for correct number of arguments
    if( argc != 3 ){
        printf("Usage: %s <input file> <output file>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    // open input file for reading
    inFile = fopen(argv[1], "r");
    if( inFile==NULL ){
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }

    // open output file for writing
    outFile = fopen(argv[2], "w");
    if( outFile==NULL ){
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    // read words from input file, print on separate lines to output file
    while(fscanf(inFile, " %s", word)!=EOF){
        stringReverse(word);
        fprintf(outFile, "%s\n", word);
    }

    // close input and output files
    fclose(inFile);
    fclose(outFile);

    return(EXIT_SUCCESS);
}
