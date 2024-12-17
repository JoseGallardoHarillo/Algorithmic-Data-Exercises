/*
 * exercise_3.c
 *
 *  Created on: 7 dic. 2020
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_3.h"

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

long long ex_3_SL(int n) {

	long long r;

	if(n==1||n==2) r= 1L;

	else if(n==0) r= 2L;

	else r= (4*ex_3_SL(n-1))+ex_3_SL(n-2)+ex_3_SL(n-3);

	return r;
}


//Stateful

long long ex_3_SF_aux(int n, hash_table* mem) {

	long long r;

	if(hash_table_contains(mem,&n)) r = *(long long*) hash_table_get(mem,&n);

	else if(n==0) {
		r = 2L;
		hash_table_put(mem,&n,&r);
	}

	else if(n==1) {
		r = 1L;
		hash_table_put(mem,&n,&r);
	}

	else if(n==2) {
		r = 1L;
		hash_table_put(mem,&n,&r);
	}

	else {
		r = (4*ej3Memaux(n-1,mem))+ej3Memaux(n-2,mem)+ej3Memaux(n-3,mem);
		hash_table_put(mem,&n,&r);
	}

	return r;
}

long long ex_3_SF(int n){

	hash_table mem = hash_table_empty(int_type,long_pair_type);

	return ex_3_SF_aux(n,&mem);

}

//Iterative

long long ex_3_It(int n) {

	int i = 0;

	long long v = 2L; //f(i)
	long long u = 1L; //f(i+1)
	long long w = 1L; //f(i+2)


	while(i<n) {
		//<w',u',v',i'> = <f(i'+2),f(i'+1),f(i'),i'> = <f(i+3),f(i+2),f(i+1),i+1>=
		//= <(4*f(i+2))+f(i+1)+f(i),w,u,i+1> = <4*w+u+v,w,u,i+1>

		long long w0 = w;
		long long u0 = u;

		w= (4*w0)+u0+v;
		u= w0;
		v= u0;
		i = i+1;
	}

	return v;
}

//File Reading

list read_f_3(char* file){

	list r = list_empty(int_type);
	iterator it = file_iterable_pchar(file);

	while(iterable_has_next(&it)){

		char * c = (char*) iterable_next(&it);
		iterator it2 = pchar_to_iterable_pchar(c,"n=");
		char* s = iterable_next(&it2);
		int n = int_parse_s(s);

		list_add(&r,&n);
		}

	return r;
	}
