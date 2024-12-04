/*
 * exercise_1.c
 *
 *  Created on: 11 nov. 2020
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_1.h"
#include "exercise_1.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"

//Iterative

	int ex_1_It(char* w1, char* w2) {

		int right = strlen(w1)-1;
		int middle = right/2;
		int left = 0;

		while(right-left>0){

			char c1 = w1[middle];
			char c2 = w2[middle];

			if(c1==c2) {

				left = middle+1;
				middle = (left+right)/2;
			}

			else {

				right = middle;
				middle = (left+right)/2;
			}
		}

	return middle;
	}


//Tail Recursive

	int ex_1_TR(char* w1,char* w2,int left, int right,
			int middle, char c1, char c2) {

		int r= middle;

		if(right-left>0) {

			if(c1==c2) {

				int left_r = middle+1;
				int middle_r = (left_r+right)/2;
				r = ex_1_TR(w1,w2, left_r,right,middle_r,w1[middle_r],
						w2[middle_r]);
			}

			else {

				int right_r = middle;
				int middle_r = (left+right_r)/2;
				r = ex_1_TR(w1,w2, left,right_r,middle_r,w1[middle_r],
						w2[middle_r]);
		}

		}

		return r;
	}
