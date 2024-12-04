/*
 * exercise_1.h
 *
 *  Created on: 11 nov. 2020
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"
#include <stdbool.h>
#include <math.h>
#ifndef EJERCICIO1_H_
#define EJERCICIO1_H_

//Iterative

	int ex_1_It(char* w1, char* w2);

//Tail Recursive

	int ex_1_TR(char* w1,char* w2,int left, int right,
				int middle, char c1, char c2);

#endif /* EJERCICIO1_H_ */
