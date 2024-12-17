/*
 * main.c
 *
 *  Created on: 7 dic. 2020
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/matrices.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>

#include "exercises/exercise_1.h"
#include "exercises/exercise_2.h"
#include "exercises/exercise_3.h"
#include "exercises/exercise_4.h"


void test1(char* file){

	char mem1[256];
	list ls =  list_of_list_of_file_type(file,int_type);
	int nf = list_size(&ls);
	int nc = list_size(list_get(&ls, 0));
	int array[nf][nc];

	list_of_list_to_2_array(&ls, array);

	matrix matrix = matrix_of_array(array, nf, nc);

	bool r = ex_1(matrix);
	char* bstr = bool_tostring(&r,mem1);

	printf("Input Matrix (%dx%d):\n",matrix_nc(matrix),matrix_nc(matrix));

	if(matrix_nc(matrix)>32) printf("It is not displayed due to the size");

	else matrix_print(matrix,"seen");

	printf("\n\n");
	printf("Is valid? %s\n",bstr);
	printf("================================================================\n");

}


void test2(char* file){

	char mem[256];
	char mem2[256];

	list lli = read_f_2(file);

	for(int i=0;i<lli.size;i++) {

		list li = *(list*) list_get(&lli,i);
		smax r2 = ex_2(li);

		list_tostring(&li,mem);

		printf("Input list: %s\n",mem);
		printf("Maximum sum sequence in an interval [%d, %d): \n",r2.li,r2.ls);

		list listaS = list_empty(int_type);

		for(int j=r2.li;j<r2.ls;j++) {

			int sn = *(int*) list_get(&li,j);

			list_add(&listaS,&sn);
		}

		list_tostring(&listaS,mem2);

		printf("Subsecuence = %s; Sum obtained = %d\n",mem2,r2.suma);
		printf("===============================================================\n");
	}
}

void test3(char* file){

	list li = read_f_3(file);


	for(int i=0;i<li.size;i++){

		int n = *(int*) list_get(&li,i);

		long long r3SL = ex_3_SL(n);
		long long r3SF = ex_4_SF(n);
		long long r3It = ex_3_It(n);

		printf("Integer Input: %d\n",n);
		printf("F. Recursive Stateless: %lld\n",r3SL);
		printf("F. Recursive Stateful: %lld\n",r3SF);
		printf("F. Iterative:		  %lld\n",r3It);
		printf("===============================================================\n");

	}
}

void test4(char* file){

	list li = read_f_4(file);

	printf("Pairs input:\n");

	for(int i=0;i<li.size;i++){

		list sl = *(list*) list_get(&li,i);

		int a = *(int*) list_get(&sl,0);
		int b = *(int*) list_get(&sl,1);

		int r4SL = ex_4_SL(a,b);
		int r4SF = ex_4_SF(a,b);
		int r4It = ex_4_It(a,b);

		printf("(a, b) = (%d,%d)\n",a,b);
		printf("F. Recursive Stateless: %lld\n",r4SL);
		printf("F. Recursive Stateful: %lld\n",r4SF);
		printf("F. Iterative: %lld\n",r4It);
		printf("===============================================================\n");
	}
}

int main(){

	//Test 1

	printf("Exercise 1:");
	printf(" \n\n");

	char* f1_1 = "files/input_1_1.txt";
	char* f1_2 = "files/input_1_2.txt";
	char* f1_3 = "files/input_1_3.txt";
	char* f1_4 = "files/input_1_4.txt";
	char* f1_5 = "files/input_1_5.txt";
	char* f1_6 = "files/input_1_6.txt";

	test1(f1_1);
	test1(f1_2);
	test1(f1_3);
	test1(f1_4);
	test1(f1_5);
	test1(f1_6);

	//Test 2

	printf("\n\n");
	printf("Exercise 2:");
	printf(" \n\n");

	char* f2 = "files/input_2.txt";

	test2(f2);

	//Test 3

	printf("\n\n");
	printf("Exercise 3:");
	printf(" \n\n");

	char* f3 = "files/input_3.txt";

	test3(f3);

	//Test 4

	printf("\n\n");
	printf("Exercise 4:");
	printf(" \n\n");

	char* f4 = "files/input_4.txt";

	test4(f4);
}


