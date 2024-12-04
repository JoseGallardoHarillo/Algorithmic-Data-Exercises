/*
 * main.c
 *
 *  Created on: 11 nov. 2020
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

#include "exercises/exercise_1.h"
#include "exercises/exercise_2.h"
#include "exercises/exercise_3.h"

//Test

int main(){

	//Test 1

	char memI1[256];
	char memTR1[256];


	printf("Exercise 1:");
	printf("\n\n");

	iterator it = file_iterable_pchar("files/input_1.txt");

	while(iterable_has_next(&it)){

		iterator it11 = split_iterable_pchar(iterable_next(&it),"#");
		char* w1 = iterable_next(&it11);
		char* w2 = iterable_next(&it11);
		int right = strlen(w1)-1;
		int left = 0;
		int middle = right/2;
		char c1 = w1[middle];
		char c2 = w2[middle];
		int res1I = ex_1_It(w1,w2);
		int res1TR = ex_1_TR(w1,w2,left,right,middle,c1,c2);

		int_tostring(&res1I,memI1);
		int_tostring(&res1TR,memTR1);

		printf("%s,%s\n",w1,w2);
		printf("1. Iterative (while): %s\n",memI1);
		printf("2. Tail Recursive: %s\n",memTR1);
		printf("\n\n");
	}

	//Test 2

	printf("Exercise 2:");
	printf("\n\n");

	char* f2 = "files/input_2.txt";
	list lines2 = read_f2(f2);

	for(int i=0;i<lines2.size;i++){

		list sl = *(list*) list_get(&lines2,i);
		int a = *(int*) list_get(&sl,0);
		int b = *(int*) list_get(&sl,1);
		bool res2I = ex_2_It(a,b);
		bool res2TR = ex_2_TR(a,b);

		printf("%d,%d\n",a,b);
		printf("1. Iterative (while): %s\n",bool_tostring(res2I));
		printf("2. Tail Recursive: %s\n",bool_tostring(res2TR));
		printf("\n\n");
	}

	//Test 3

	char memI3[256];
	char memTR3[256];
	char memNTR3[256];

	printf("Exercise 3:");
	printf("\n\n");

	char* f3 = "files/input_3.txt";
	list lines3 = read_f3(f3);

	for(int i=0;i<lines3.size;i++){

		list sl2 = *(list*) list_get(&lines3,i);
		int a = *(int*) list_get(&sl2,0);
		long al = (long) a;
		int n = *(int*) list_get(&sl2,1);
		long b = 1L;
		long res3I = ex_3_It(al,n);
		long res3NTR = ex_3_NTR(al,n);
		long res3TR = ex_3_TR(al,n,b);

		long_tostring(&res3I,memI3);
		long_tostring(&res3TR,memTR3);
		long_tostring(&res3NTR,memNTR3);

		printf("%d,%d\n",a,n);
		printf("1. Iterative (while): %s\n",memI3);
		printf("2. Tail Recursive: %s\n",memTR3);
		printf("3. Non-Tail Recursive: %s\n",memNTR3);
		printf("\n\n");
	}

}
