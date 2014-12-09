//Gio Lapid
//1262354
//CS12B
//07/06/13

//PA 2
//Search.java
//Takes command line arguments giving a file to be searched and target word(s) to search for

import java.util.*;
import java.io.*;

class Search{
    public static void main(String[] args)throws IOException {     
        //variables 
	String[] arrays = null; 
	int[] temp = null;
	String line = null;
	int n = 0, j = 1, lineCount = 0;

        //checks if user uses the program right
	if(args.length < 2){
	    System.err.println("Usage: Search file target1 [target2 ..]");
	    System.exit(1);
	}

	//needs two scanners to count and make an array
	Scanner inn = new Scanner(new File(args[0]));
	Scanner in = new Scanner(new File(args[0]));
       
	//check number of lines
	while(inn.hasNextLine()){
	    inn.nextLine();
	    lineCount++;
	}
       
  	arrays = new String[lineCount];
	temp = new int[lineCount];
	for(int i = 0; i < lineCount; i++)temp[i] = i;//indices

	//copies the array
	while(in.hasNextLine()){
	    if(n < 10){
		String statement = in.nextLine();
		arrays[n] = screen (statement);
		n++;
	    }
	}

	//Sort then find the target
       	mergeSort(arrays, temp, 0, lineCount-1);
	while(args.length > j){
	    if(binarySearch(arrays, temp, 0, lineCount-1, args[j]) > -1) {
		System.out.print(args[j] + " found on line ");
		System.out.println(binarySearch(arrays, temp, 0, lineCount-1, args[j])+1);
	    }else{
		System.out.println(args[j] + " not found");
	    }
	    j++;
	}
	//copy string to sort
	inn.close();
	in.close();
    }

    //helps store string into a new array called arrays
    public static String screen (String statement){
	String token[]= statement.split("\n");
	String rev = "";
	for(int i = 0; i < token.length; i++){
	    rev = token[i];
	    return rev;
	}
	return rev;
    }
    //mergeSort
    public static void mergeSort(String[] word, int[] A, int p, int r){
	int q;
	if(p < r) {
	    q = (p+r)/2;
	    mergeSort(word, A, p, q);
	    mergeSort(word, A, q+1, r);
	    merge(word, A, p, q, r);
	}
    }
    //merge
    public static void merge(String[] word, int[] A, int p, int q, int r){
	int n1 = q-p+1;
	int n2 = r-q;
	int[] L = new int[n1];
	int[] R = new int[n2];
	String[] left = new String[n1];
	String[] right = new String[n2];
	int i, j, k;

	for(i=0; i<n1; i++){ 
	    L[i] = A[p+i];
	    left[i] = word[p+i];
	}
	for(j=0; j<n2; j++){
	    right[j] = word[q+j+1];
	    R[j] = A[q+j+1];
	}
	i = 0; j = 0;
	for(k=p; k<=r; k++){
	    if( i<n1 && j<n2 ){
		if( left[i].compareTo(right[j]) < 0 ){
		    word[k] = left[i];
		    A[k] = L[i];
		    i++;
		}else{
		    word[k] = right[j];
		    A[k] = R[j];
		    j++;
		}
	    }else if( i<n1 ){
		word[k] = left[i];
		A[k] = L[i];
		i++;
	    }else{ // j<n2
		word[k] = right[j];
		A[k] = R[j];
		j++;
	    }
	}
    }
    //binarySearch
    public static int binarySearch(String[] A, int[] B,  int p, int r,  String target){
	int q;
	if(p > r) {
	    return -1;
	}else{
	    q = (p+r)/2;
	    if(target.compareTo(A[q]) == 0){
		return B[q];
	    }else if(target.compareTo(A[q]) < 0){
		return binarySearch(A, B, p, q-1, target);
	    }else{
		return binarySearch(A, B, q+1, r, target);
	    }
	}
    }
}