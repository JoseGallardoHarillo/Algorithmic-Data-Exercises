/*
 * exercise_4.c
 *
 *  Created on: 7 dic. 2020
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_4.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/matrices.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>

//Stateless

int ex_4_SL(int a, int b) {

	int r;

	if(a<2&&b<2) r = a+b*b;

	else if(a<2||b<2) r = a*a+b;

	else r = ex_4_SL(a/2, b-1)+ex_4_SL(a/3, b-2)+ex_4_SL(a-2, b/4);

	return r;
}

//Stateful

int ex_4_SF_aux(int a, int b, int mem[a+1][b+1] ) {

	if(a<2&&b<2) mem[a][b]= a+b*b;

	else if(a<2||b<2) mem[a][b] = a*a+b;

	else mem[a][b] = ex_4_SF_aux(a/2, b-1,mem)+ex_4_SF_aux(a/3, b-2,mem)+ex_4_SF_aux(a-2, b/4,mem);

	return mem[a][b];
}

int ex_4_SF(int a, int b) {

	int mem[a+1][b+1];

	return ex_4_SF_aux( a, b, mem);
}

//Iterative

int ex_4_It(int a, int b) {

	int mem[a+1][b+1];

	for(int a0=0;a0<=a;a0++) {

		for(int b0=0;b0<=b;b0++) {

			if(a0<2&&b0<2) mem[a0][b0]= a0+b0*b0;

			else if(a0<2||b0<2) mem[a0][b0] = a0*a0+b0;

			else mem[a0][b0] = ex_4_SF_aux(a0/2, b0-1,mem)+ex_4_SF_aux(a0/3, b0-2,mem)+ex_4_SF_aux(a0-2, b0/4,mem);
		}
	}

	return mem[a][b];
}



//File Reading

void pre_read_f_4(list *l, char* character){

	iterator it = pchar_to_iterable_pchar(character, ",");

	while(iterable_has_next(&it)){

		char* chara = (char*) iterable_next(&it);
		int n = int_parse_s(chara);
		list_add(l, &n);
	}
}



list read_f_4(char* file){

	list r = list_empty(list_type);
	iterator it = file_iterable_pchar(file);

	while(iterable_has_next(&it)){

		list li = list_empty(int_type);
		char * c = (char*) iterable_next(&it);

		pre_read_f_4(&li, c);

		list_add(&r,&li);
	}

	return r;
}


