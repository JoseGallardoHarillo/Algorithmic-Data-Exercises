/*
 * exercise_1.c
 *
 *  Created on: 7 dic. 2020
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_1.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/matrices.h"
#include <stdbool.h>
#include <math.h>

bool ex_1_aux(matrix matrix, int i, int j, int n){

	bool r;
	int Corner1 = matrix_get(matrix,i,j);
	int Corner2 = matrix_get(matrix,i+n-1,j);
	int Corner3 = matrix_get(matrix,i,j+n-1);
	int Corner4 = matrix_get(matrix,i+n-1,j+n-1);

	if(n==2) {

		if(Corner1==Corner2||Corner1==Corner3||Corner1==Corner4||Corner2==Corner3||Corner2==Corner4||
				Corner3==Corner4) r = false;

		else r=true;
	}

	else {

		if(Corner1==Corner2||Corner1==Corner3||Corner1==Corner4||Corner2==Corner3||Corner2==Corner4||
				Corner3==Corner4) r = false;

		else r= ex_1_aux(matrix, i, j, n/2) && ex_1_aux(matrix, i+(n/2), j, n/2) && ex_1_aux(matrix, i, j+(n/2), n/2) &&
				ex_1_aux(matrix, i+(n/2), j+(n/2), n/2);
	}

	return r;
}

bool ex_1(matrix matrix){
	int n = matrix_nc(matrix);
	int i=0;
	int j=0;
	return ej1aux(matrix,i,j,n);
}
