public class Recursion {

    // reverses the leftmost n elements in X and puts them in Y
    static void reverseArray1(int[] X, int n, int[] Y) {
        if (n > 0) {
            Y[X.length - n] = X[n - 1]; // reverse leftmost n-1 elements
            reverseArray1(X, n - 1, Y);
        }
    }

    // reverses the rightmost n elements in X and puts them in Y
    static void reverseArray2(int[] X, int n, int[] Y) {
        if (n > 0) {
            reverseArray2(X, n - 1, Y);
            Y[n - 1] = X[X.length - n]; // reverse rightmost n-1 elements
        }
    }

    // reverses the elements in X by altering the order of elements in X
    static void reverseArray3(int[] X, int i, int j) {
        int middle = (i + j) / 2; // set midpoint of array

        if (((i >= 0) && (j <= (X.length - 1)))
                && ((i < middle) || (j > middle))) {
            int temp = 0; // temporary storage variable

            // swap beginning and end of elements
            temp = X[j];
            X[j] = X[i];
            X[i] = temp;

            // move pointers closer to middle and print elements
            reverseArray3(X, i + 1, j - 1);
        }
    }

    // returns the index of the largest value in int array X
    static int maxArrayIndex(int[] X, int p, int r) {
        int q = 0;
        int maxValueIndex = 0;

        if (p == r) {
            return p;
        }

        if (p < r) {
            q = (p + r) / 2;
            int left_half = maxArrayIndex(X, p, q);
            int right_half = maxArrayIndex(X, q + 1, r);

            //compare left half to right half values
            if (X[left_half] > X[right_half]) {
                maxValueIndex = left_half;
            } else {
                maxValueIndex = right_half;
            }
        }
        return maxValueIndex;
    }

    // returns the index of the smallest value in int array X
    static int minArrayIndex(int[] X, int p, int r) {
        int q = 0;
        int minValueIndex = 0;

        if (p == r) {
            return p;
        }

        if (p < r) {
            q = (p + r) / 2;
            int left_half = minArrayIndex(X, p, q);
            int right_half = minArrayIndex(X, q + 1, r);

            //compare left half to right half values
            if (X[left_half] < X[right_half]) {
                minValueIndex = left_half;
            } else {
                minValueIndex = right_half;
            }
        }
        return minValueIndex;
    }

    public static void main(String[] args) {
        int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        int minIndex = minArrayIndex(A, 0, A.length - 1);
        int maxIndex = maxArrayIndex(A, 0, A.length - 1);

        for(int x: A) System.out.print(x+" ");
        System.out.println();

        System.out.println("minIndex = " + minIndex);
        System.out.println("maxIndex = " + maxIndex);

        reverseArray1(A, A.length, B);
        for(int x: B) System.out.print(x+" ");
        System.out.println();

        reverseArray2(A, A.length, C);
        for(int x: C) System.out.print(x+" ");
        System.out.println();

        reverseArray3(A, 0, A.length - 1);
        for(int x: A) System.out.print(x+" ");
        System.out.println();
    }

}
