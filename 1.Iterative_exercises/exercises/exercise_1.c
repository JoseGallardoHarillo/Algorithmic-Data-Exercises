/*
 * exercise_1.c
 *
 *  Created on: 27 oct. 2020
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"
#include <stdbool.h>
#include <math.h>
#include "../types/hash_table.h"
#include "exercise_1.h"

list ex_1(list ll){

	list lRes = list_empty(int_type);
	iterator it = list_iterable(&ll);

	while(iterable_has_next(&it)){

		list sl = *(list*) iterable_next(&it);

		for(int j=0;j<sl.size;j++){

			bool isPrime = es_primo( *(int*) list_get(&sl,j));

			if(isPrime==true) list_add(&lRes,list_get(&sl,j));
		}
	}

	return lRes;
}

list read_f(char* file){

	list r = list_empty(list_type);
	iterator it = file_iterable_pchar(file);

	while(iterable_has_next(&it)){

		list li = list_empty(int_type);
		char * c = (char*) iterable_next(&it);

		pre_read_f(&li, c);
		list_add(&r,&li);
	}

	return r;
}

void pre_read_f(list *l, char* character){

	iterator it = split_iterable_pchar(character, ", ");

	while(iterable_has_next(&it)){

		char* chara = (char*) iterable_next(&it);
		int n = int_parse_s(chara);

		list_add(l, &n);
	}
}
