import java.util.ArrayList;
import java.util.Arrays;
public class Sort {

    public static void main(String[] args) {
	    // write your code here
        /*
        ArrayList<Integer> L1 = new ArrayList<Integer>(
                Arrays.asList(6, 3, 2, 7, 1, 8, 6)
        );
        bubbleSort(L1);
        System.out.println(L1);
        ArrayList<String> L2 = new ArrayList<String>(
                Arrays.asList("6", "3", "23", "7", "19", "8", "63")
        );
        bubbleSortStr(L2);
        System.out.println(L2);
        ArrayList<Integer> L3 = new ArrayList<Integer>(
                Arrays.asList(5, 3, 2, 1, 7, 4)
        );
        selectionSort(L3);
        System.out.println(L3);
        ArrayList<Integer> L4 = new ArrayList<Integer>(
                Arrays.asList(8, 5, 9, 2, 6, 3)
        );
        insertionSort(L4);
        System.out.println(L4);
        */

        int[] A = new int[]{2, 4, 5, 7, 11, 12, 13};
        int[] B = new int[]{1, 3, 4, 6, 8, 9, 10};
        merge(A, B);
    }

    public static void bubbleSort(ArrayList<Integer> list) {
        for (int outer = 0; outer < list.size() - 1; outer++) {
            for (int inner = 0; inner < list.size()-outer-1; inner++) {
                System.out.println("\touter=" + outer + " inner=" + inner + ": " + list);
                if (list.get(inner) > list.get(inner + 1)) {
                    // swap list[inner] & list[inner+1]
                    int temp = list.get(inner);
                    list.set(inner, list.get(inner + 1));
                    list.set(inner + 1, temp);

                }
            }
        }
    }

    public static void bubbleSortStr(ArrayList<String> list) {
        for (int outer = 0; outer < list.size() - 1; outer++) {
            for (int inner = 0; inner < list.size()-outer-1; inner++) {
                System.out.println("\touter=" + outer + " inner=" + inner + ": " + list);
                if (list.get(inner).compareTo(list.get(inner + 1)) > 0) {
                    // swap list[inner] & list[inner+1]
                    String temp = list.get(inner);
                    list.set(inner, list.get(inner + 1));
                    list.set(inner + 1, temp);

                }
            }
        }
    }

    public static void selectionSort(ArrayList <Integer> list){
        int min, temp;
        for (int outer = 0; outer < list.size() - 1; outer++){
            min = outer;
            for (int inner = outer + 1; inner < list.size(); inner++){
                System.out.println("\touter=" + outer + " inner=" + inner + ": " + list);
                if (list.get(inner) < list.get(min)) {
                    min = inner; // a new smallest item is found
                }
            }
            //swap list[outer] & list[min]
            temp = list.get(outer);
            list.set(outer, list.get(min));
            list.set(min, temp);
        }
    }

    public static void insertionSort(ArrayList <Integer> list){
        for (int outer = 1; outer < list.size(); outer++){
            int position = outer;
            int key = list.get(position);
            // Shift larger values to the right
            while (position > 0 && list.get(position - 1) > key){
                list.set(position, list.get(position - 1));
                position--;
            }
            list.set(position, key);
        }
    }
    public static int[] merge(int[] A, int[] B ) {
        int[] M = new int[A.length + B.length];
        int ptra= 0;
        int ptrb = 0;
        while(ptra < A.length && ptrb < B.length) {
            if(A[ptra] < B[ptrb]){
                M[ptra + ptrb] = A[ptra];
                ptra++;

            }
            else{
                M[ptra + ptrb] = B[ptrb];
                ptrb++;

            }
        }
        if (ptra == A.length) {
            for(int i = ptrb;i < B.length;i++) {
                M[ptra + ptrb] = B[i];
                ptrb++;
            }
        }
        else if (ptrb == B.length)  {
            for(int i = ptra;i < A.length;i++) {
                M[ptra + ptrb] = A[i];
                ptra++;
            }
        }
        printArray(M);
        return M;
    }

    public static void printArray(int[] A){
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }


}
