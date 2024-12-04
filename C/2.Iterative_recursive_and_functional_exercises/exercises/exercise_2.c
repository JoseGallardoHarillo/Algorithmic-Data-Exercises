/*
 * exercise_2.c
 *
 *  Created on: 11 nov. 2020
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

//Tail Recursive

	bool ex_2_TR(int a, int b) {

		if(0<a&&a<b) return false;

		else if(a>=b) return ex_2_TR(a-b, b);

		else return true;
	}

//Iterative

	bool ex_2_It(int a, int b) {

		bool isMultiple = NULL;

		while(a>=b) a = a-b;

		if(0<a&&a<b) isMultiple= false;

		else if(a==0) isMultiple= true;

		return isMultiple;
	}

//File Reading

	list read_f2(char* file){

		list r = list_empty(list_type);
		iterator it = file_iterable_pchar(file);

		while(iterable_has_next(&it)){

			list li = list_empty(int_type);
			char * c = (char*) iterable_next(&it);

			pre_read_f2(&li, c);
			list_add(&r,&li);
		}
	return r;
	}

	void pre_read_f2(list *l, char* c){
		iterator it = split_iterable_pchar(c, ", ");

		while(iterable_has_next(&it)){

			char* chara = (char*) iterable_next(&it);
			int n = int_parse_s(chara);

			list_add(l, &n);
		}
	}
