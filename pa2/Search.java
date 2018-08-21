// -----------------------------------------------------------------------------
// File: Search.java
// Name: Sophia Tacderas
// ID: 1465379
// Due: 10/14/16, 10 pm
// Class: CMPS 12B
// Assignment: pa2
// Purpose: Reads file and searches for target words.
// Borrows/modifies code from: mergeSort.java, binarySearch.java, lineCount.java
// ------------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class Search {

    public static void main(String[] args) throws IOException {

        // From LineCount.java
        // Check number of command line arguments
        if(args.length < 2){
            System.err.println("Usage: Search file target1 [target2 target3 ..]");
            System.exit(1);
        }

        // Count the number of lines in inFile
        Scanner inFile = new Scanner(new File(args[0]));
        int lineCount = 0;
        while( inFile.hasNextLine() ){
            inFile.nextLine();
            lineCount++;
        }

        // Define string array for the words from inFile
        String[] wordArray = new String[lineCount];

        // Create an array for the line numbers of the words in wordArray
        // To be used to report position of word in original file prior to mergeSort
        int[] lineNumberArray = new int[lineCount];

        // Load the lineNumberArray from value 1 to lineCount
        // Array starts at position 0
        for (int i = 1; i <= lineCount; i++) {
            lineNumberArray[i - 1] = i;
        }

        // Reload file to Scanner inFile since the loop count is already at the end of the file
        inFile = new Scanner(new File(args[0]));

        // Load the wordArray with words from inFile
        // Array starts at position 0
        for (int i = 1; i <= lineCount; i++) {
            wordArray[i - 1] = inFile.nextLine();
        }

        // Invoke mergeSort method
        mergeSort(wordArray, lineNumberArray, 0, lineCount - 1);

        // Based on number of arguments, loop through binarySearch method
        // args.length counts all arguments including inFile
        for (int ctr = 1; ctr < args.length; ctr++) {

            String currArg = args[ctr];
            int lineReturn = binarySearch(wordArray, lineNumberArray, 0, lineCount - 1, currArg);

            if (lineReturn == -1) {
                // if return code is -1 then target not found
                System.out.println(currArg + " not found");
            } else {
                // if return code is > 0 then target is found
                // Return the original lineNumber from lineNumber array
                System.out.println(currArg + " found on line " + lineReturn);
            }

        }

        // Close inFile
        inFile.close();

    }

    // Based off of MergeSort.java, except:
    // Uses String array for the word and int array for the original lineNumber
    static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
        int q;
        if(p < r) {
            q = (p+r)/2;
            mergeSort(word, lineNumber, p, q);
            mergeSort(word, lineNumber, q+1, r);
            merge(word, lineNumber, p, q, r);
        }
    }

    // Also based off of MergeSort.java, except:
    // Processes both word array and lineNumber array
    static void merge(String[] word, int[] lineNumber, int p, int q, int r) {

        int n1 = q-p+1;
        int n2 = r-q;
        String[] leftOfWord = new String[n1];
        String[] rightOfWord = new String[n2];
        int[] leftOfLN = new int[n1];
        int[] rightOfLN = new int[n2];
        int i, j, k;

        for(i=0; i<n1; i++){
            leftOfWord[i] = word[p+i];
            leftOfLN[i] = lineNumber[p+i];
        }
        for(j=0; j<n2; j++){
            rightOfWord[j] = word[q+j+1];
            rightOfLN[j] = lineNumber[q+j+1];
        }

        i = 0; j = 0;
        for(k=p; k<=r; k++){
            if( i<n1 && j<n2 ){
                if (leftOfWord[i].compareTo(rightOfWord[j]) < 0) {
                    word[k] = leftOfWord[i];
                    lineNumber[k] = leftOfLN[i];
                    i++;
                }else{
                    word[k] = rightOfWord[j];
                    lineNumber[k] = rightOfLN[j];
                    j++;
                }
            }else if( i<n1 ){
                word[k] = leftOfWord[i];
                lineNumber[k] = leftOfLN[i];
                i++;
            }else{ // j<n2
                word[k] = rightOfWord[j];
                lineNumber[k] = rightOfLN[j];
                j++;
            }
        }

    }

    // Based off of BinarySearch.java, except:
    // target is being searched/compared with word from wordArray
    static int binarySearch(String[] word, int[] lineNumber, int p, int r, String target) {

        int q;
        if(p > r) {
            // Returns -1 if target not found
            return -1;
        }else{
            q = (p+r)/2;
            if(target.compareTo(word[q]) == 0){
                // Returns > 0 if found; returned value is original lineNumber
                return lineNumber[q];
            }else if(target.compareTo(word[q]) < 0){
                return binarySearch(word, lineNumber, p, q-1, target);
            }else{ // target.compareTo(word[q]) > 0
                return binarySearch(word, lineNumber, q+1, r, target);
            }
        }

    }

}