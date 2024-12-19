/*
 * main.c
 *
 *  Created on: 5 ene. 2021
 *      Author: PC
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/matrices.h"
#include "types/tree.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>

#include "exercises/exercise_1.h"
#include "exercises/exercise_2.h"
#include "exercises/exercise_3.h"
#include "exercises/exercise_4.h"
#include "exercises/exercise_5.h"

void forTesting_1(char* file){

	char mem11[256];
	char mem12[256];

	iterator s1 = file_iterable_pchar(file);
	memory_heap mh1 = memory_heap_create();

	while(iterable_has_next(&s1)){
		char* line = iterable_next(&s1);

		binary_tree* bt = binary_tree_parse(line,&mh1);
		binary_tree* bti = binary_tree_map(bt, int_type, int_parse, &mh1);
		bool r1 = ex_1(bti);

		bool_tostring(&r1,mem11);
		binary_tree_tostring(bti,mem12);

		printf("Input tree: %s\n",mem12);
		printf("Does it satisfy the predicate of the statement? %s\n",mem11);
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	memory_heap_free(&mh1);
}

void forTesting_2(char* file){

	char mem21[256];
	char mem22[256];
	char mem23[256];
	iterator s2 = file_iterable_pchar(file);
	memory_heap mh2 = memory_heap_create();

	while(iterable_has_next(&s2)){

		char* line = iterable_next(&s2);
		iterator it2 = pchar_to_iterable_pchar(line,"#");
		char* btstr = iterable_next(&it2);
		char* lstr = iterable_next(&it2);
		list lc = list_empty(char_type);

		list_parse_dedicado(&lc,lstr);

		binary_tree* bt = binary_tree_parse(btstr,&mh2);

		bool r2 = ex_2(bt,lc);

		bool_tostring(&r2,mem21);
		binary_tree_tostring(bt,mem22);
		list_tostring(&lc,mem23);

		printf("Input tree: %s\n",mem22);
		printf("Does a path exist from the root for %s?: %s\n",mem23,mem21);
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}

	memory_heap_free(&mh2);
}

void forTesting_3(char* file,bool predicate(void*)){

	char mem31[256];
	char mem32[256];
	iterator s3 = file_iterable_pchar(file);
	memory_heap mh3 = memory_heap_create();

	while(iterable_has_next(&s3)){
		char* row = (char*) iterable_next(&s3);
		tree* t = tree_parse(row,&mh3);
		list r31 = ex_3(t,predicate);

		tree_tostring(t,mem31);
		list_tostring(&r31,mem32);

		printf("Input tree: %s\n",mem31);
		printf("Does it satisfy the predicate: %s\n",mem32);
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	memory_heap_free(&mh3);
}

void forTesting_4(char* file){

	char mem41[256];
	char mem42[256];
	iterator s4 = file_iterable_pchar(file);
	memory_heap mh4 = memory_heap_create();

	while(iterable_has_next(&s4)){
		char* row = (char*) iterable_next(&s4);
		tree* t = tree_parse(row,&mh4);
		hash_table r4 = ej4(t);

		tree_tostring(t,mem41);
		hash_table_tostring(&r4,mem42);

		printf("Input tree: %s\n",mem41);
		printf("Output Map: %s\n",mem42);
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	memory_heap_free(&mh4);
}

void forTesting_5(char* file){

	char mem51[256];
	char mem52[256];
	iterator s5 = file_iterable_pchar(file);
	memory_heap mh5 = memory_heap_create();

	while(iterable_has_next(&s5)){
		char* row = (char*) iterable_next(&s5);
		tree* t = tree_parse(row,&mh5);
		hash_table r5 = ej5(t);

		tree_tostring(t,mem51);
		hash_table_tostring(&r5,mem52);

		printf("Input tree: %s\n",mem51);
		printf("Output Map: %s\n",mem52);
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	memory_heap_free(&mh5);
}

int main(){

	//Test 1


	printf("Exercise 1");
	printf("\n\n");

	char* file1 = "files/input_1.txt";

	forTesting_1(file1);


	//Test 2

	printf("\n\n");
	printf("Exercise 2");
	printf("\n\n");

	char* file2 = "files/input_2.txt";

	forTesting_2(file2);

	//Test 3

	printf("\n\n");
	printf("Exercise 3");
	printf("\n\n");

	char* file3 = "files/input_3.txt";

	bool (*predicate)(void*);

	printf("============================= Predicate \"is-par\" =============================");
	printf("\n\n");

	predicate = p_is_par;

	paraTest3(file3,predicate);

	printf("============================= Predicate \"is-prime\" =============================");
	printf("\n\n");

	predicate = p_is_prime;

	forTesting_3(file3,predicate);


	//Test 4

	printf("\n\n");
	printf("Exercise 4");
	printf("\n\n");

	char* file4 = "files/input_4.txt";

	forTesting_4(file4);

	//Test 5

	printf("\n\n");
	printf("Exercise 5");
	printf("\n\n");

	char* file5 = "files/input_5.txt";

	forTesting_5(file5);
}





