/*
 * exercise_2.c
 *
 *  Created on: 27 oct. 2020
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_2.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"
#include <stdbool.h>
#include <math.h>
#include "../types/hash_table.h"

string ex_2(int limit){

	int i = 2;
	string message = string_of_pchar("");
	char mem[10];

	while(i<=limit) {

		bool isPrime =  es_primo(i);

		if(isPrime == true) {

			int primeSquared = i*i;
			string memStr = string_of_pchar(int_tostring(&primeSquared,mem));
			string appart = string_of_pchar("\n");

			string_add_string(&message,&memStr);
			string_add_string(&message,&appart);
		}

		i++;
	}
	return message;
}

list read_f2(char* file){
	list r = list_empty(int_type);
	iterator it = file_iterable_pchar(file);

	while(iterable_has_next(&it)){

		char * c = (char*) iterable_next(&it);
		iterator it2 = split_iterable_pchar(c,": ");
		char* s = iterable_next(&it2);
		char* s2 = iterable_next(&it2);
		int n = int_parse_s(s2);

		list_add(&r,&n);
	}

	return r;
}
