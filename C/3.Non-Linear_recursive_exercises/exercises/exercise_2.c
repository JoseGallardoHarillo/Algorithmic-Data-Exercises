/*
 * exercise_2.c
 *
 *  Created on: 7 dic. 2020
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_2.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/matrices.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>
#include "ejemplos/tupla.h"

smax ex_2_aux_2(list li, int left, int k, int right){

	int sum = 0;
	int l = 0;
	int leftSum = 0;

	for(int i=k;i>=left;i--) {

		sum = sum+ *(int*) list_get(&li,i);

		if(sum>leftSum) {

			leftSum = sum;
			l=i;
		}
	}

	sum = 0;

	int r = 0;
	int rightSum = 0;

	for(int i=k+1;i<=right;i++) {

		sum = sum + *(int*) list_get(&li,i);

		if(sum>rightSum) {
			rightSum=sum;
			r=i+1;
		}
	}

	return smax_of(l, r, leftSum+rightSum);
}


smax ex_2_aux(list li, int left, int right){

	smax maxTuple;

	if(right-left<=1) {
		return smax_of(left,right, *(int*) list_get(&li,left));

	}

		int k = (left+right)/2;

		smax s1= ej2aux(li, left, k);

		smax s2= ej2aux(li, k+1, right);

		smax s3= ej2aux2(li, left, k, right);

		int maxIntermediate = MAX(s1.suma, s2.suma);
		int maxTotal = MAX(maxIntermediate, s3.suma);

		if(maxTotal==s1.suma) {
			maxTuple = s1;
		}else if(maxTotal==s2.suma) {
			maxTuple = s2;
		}else {
			maxTuple = s3;
		}

		return maxTuple;
	}

smax ex_2(list li){

	int n = li.size;

	return ex_2_aux(li, 0, n-1);
}

	//File Reading

	void pre_read_2(list *l, char* character){

		iterator it = pchar_to_iterable_pchar(character, ", ");

		while(iterable_has_next(&it)){

			char* chara = (char*) iterable_next(&it);
			int n = int_parse_s(chara);

			list_add(l, &n);
		}
	}

	list read_f_2(char* file){

		list r = list_empty(list_type);
		iterator it = file_iterable_pchar(file);

		while(iterable_has_next(&it)){

			list li = list_empty(int_type);
			char * c = (char*) iterable_next(&it);

			pre_read_2(&li, c);
			list_add(&r,&li);
		}
	return r;
	}
