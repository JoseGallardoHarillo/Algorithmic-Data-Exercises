/*
 * exercise_3.c
 *
 *  Created on: 11 nov. 2020
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_3.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"
#include <stdbool.h>
#include <math.h>

//Non-Tail Recursive

	long ex_3_NTR(long a, int n) {

		long r;

		if(n>0) {

			r = ex_3_NTR(a, n/2);

			if(n%2==1) r = r*r*a;

			else r = r*r;
		}

		else r = 1L;

		return r;
	}

//Iterative

	long ex_3_It(long a, int n) {
		long first_a = a;
		long r = 1L;
		while(n>0) {
			if(n%2==1) r = r*first_a;

			first_a = first_a*first_a;
			n=n/2;
		}

		return r;
	}

//Tail Recursive

	long ex_3_TR(long a, int n, long b) {

		long first_a = a;
		long r = b;

		if(n>0) {

			if(n%2==1) r = ex_3_TR(first_a*first_a, n/2, r*first_a);

			else r = ex_3_TR(first_a*first_a, n/2, r);

		}

		return r;
	}


//File Reading

	list read_f3(char* file){
		list r = list_empty(list_type);
		iterator it = file_iterable_pchar(file);

		while(iterable_has_next(&it)){

			list li = list_empty(int_type);
			char * c = (char*) iterable_next(&it);

			pre_read_f3(&li, c);
			list_add(&r,&li);
		}

		return r;
	}

	void pre_read_f3(list *l, char* c){

		iterator it = split_iterable_pchar(c, ", ");

		while(iterable_has_next(&it)){

			char* chara = (char*) iterable_next(&it);
			int n = int_parse_s(chara);

			list_add(l, &n);
		}
	}
