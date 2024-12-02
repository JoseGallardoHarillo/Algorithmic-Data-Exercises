/*
 * main.c
 *
 *  Created on: 10 oct. 2020
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include <stdbool.h>
#include <math.h>
#include "types/hash_table.h"
#include "exercises/exercise_1.h"
#include "exercises/exercise_2.h"
#include "exercises/exercise_3.h"

//Tests

int main(){

	//Test 1

	char mem[256];

	printf("Exercise 1:");
	printf(" \n\n");

	char* file1 = "files/input_1.txt";
	list lines = read_f(file1);
	list res1 = ex_1(lines);
	list_tostring(&res1,mem);

	printf("%s\n\n",mem);
	printf("========================================\n\n");

	//Test 2

	char mem2[256];

	printf("Exercise 2:");
	printf(" \n\n");

	char* file2 = "files/input_2.txt";
	list lines2 = read_f2(file2);

	for(int i=0;i<lines2.size;i++){

		int n = *(int*) list_get(&lines2,i);
		string res2 = ex_2(n);

		string_tostring(&res2,mem2);

		printf("Limit %d:\n",n);
		printf("%s",mem2);
		printf(" \n\n");
	}

	printf("========================================\n\n");

	//Test 3

	char mem3[256];

	printf("Exercise 3:");
	printf(" \n\n");

	const char* file3 = "files/input_3.txt";
	list r = list_empty(punto_type);

	read_f3(file3,&r);

	hash_table res3 = ex_3(r);

	hash_table_tostring(&res3,mem3);

	printf("%s\n",mem3);
}
