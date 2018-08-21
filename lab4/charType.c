// -----------------------------------------------------------------------------------
// File: charType.c
// Name: Sophia Tacderas
// ID: 1465379
// Due: 10/28/16, 10 pm
// Class: CMPS 12M
// Assignment: lab 4
// Purpose: Reads every line of input file and for each line, displays the number
// of characters of each character type and displays these character types in strings.
// Borrows/modifies code from: alphaNum.c
// -----------------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

// function prototype
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments
int main(int argc, char* argv[]){
   FILE* inFile;        // handle for input file
   FILE* outFile;       // handle for output file
   char* lineString;    // string holding input line
   char* alpha;         // string holding all alphabet chars
   char* num;           // string holding all numerical chars
   char* punct;         // string holding all punctuation chars
   char* space;         // string holding all whitespaces


   // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading
   if( (inFile=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing
   if( (outFile=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and all strings on the heap
   lineString = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   num = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punct = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   space = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   // check if callocs for all strings succeeded using assert()
   assert( lineString!=NULL && alpha!=NULL && num!=NULL && punct!=NULL && space!=NULL );

   int lineCtr = 1; // counts each line in input file

   // read each line in input file, extract characters based on type
   while( fgets(lineString, MAX_STRING_LENGTH, inFile) != NULL ){
      extract_chars(lineString, alpha, num, punct, space);

      /*
        Example output:
        line 1 contains:
        12 alphabetic characters: abchuryhqxbv
        3 numeric characters: 638
        8 punctuation characters: -)(*&!~`
        5 whitespace characters:
      */

      fprintf(outFile, "line %d contains:\n", lineCtr);

      int alphaLen = strlen(alpha);
      if (alphaLen == 1) {
          fprintf(outFile, "%d alphabetic character: %s\n", alphaLen, alpha);
      } else {
          fprintf(outFile, "%d alphabetic characters: %s\n", alphaLen, alpha);
      }

      int numLen = strlen(num);
      if (numLen == 1) {
          fprintf(outFile, "%d numeric character: %s\n", numLen, num);
      } else {
          fprintf(outFile, "%d numeric characters: %s\n", numLen, num);
      }

      int punctLen = strlen(punct);
      if (punctLen == 1) {
          fprintf(outFile, "%d punctuation character: %s\n", punctLen, punct);
      } else {
          fprintf(outFile, "%d punctuation characters: %s\n", punctLen, punct);
      }

      int spaceLen = strlen(space);
      if (spaceLen == 1) {
          fprintf(outFile, "%d whitespace character: %s\n", spaceLen, space);
      } else {
          fprintf(outFile, "%d whitespace characters: %s\n", spaceLen, space);
      }

      lineCtr++; // increment line counter
   }

   // free heap memory
   free(lineString);
   free(alpha);
   free(num);
   free(punct);
   free(space);

   // close input and output files
   fclose(inFile);
   fclose(outFile);

   return EXIT_SUCCESS;
}


// function definition
void extract_chars(char* s, char* a, char* d, char* p, char* w) {
    // s = line
    // a = alpha
    // d = num
    // p = punct
    // w = space

    int linePtr=0, alphaPtr=0, numPtr=0, punctPtr=0, spacePtr=0;
    while(s[linePtr]!='\0' && linePtr<MAX_STRING_LENGTH){
        // Use isalpha(), isdigit(), ispunct(), and isspace()

        if( isalpha( s[linePtr]) ) {
            a[alphaPtr] = s[linePtr];
            alphaPtr++;
        }
        if( isdigit( s[linePtr]) ) {
            d[numPtr] = s[linePtr];
            numPtr++;
        }
        if( ispunct( s[linePtr]) ) {
            p[punctPtr] = s[linePtr];
            punctPtr++;
        }
        if( isspace( s[linePtr]) ) {
            w[spacePtr] = s[linePtr];
            spacePtr++;
        }

        linePtr++;
   }
   a[alphaPtr] = '\0';
   d[numPtr] = '\0';
   p[punctPtr] = '\0';
   w[spacePtr] = '\0';
}

