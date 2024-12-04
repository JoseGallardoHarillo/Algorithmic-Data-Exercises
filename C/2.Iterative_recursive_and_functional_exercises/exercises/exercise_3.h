/*
 * exercise_3.h
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

#ifndef EJERCICIO3_H_
#define EJERCICIO3_H_


//Non-Tail Recursive

	long ex_3_NTR(long a, int n);

//Iterative

	long ex_3_It(long a, int n);

//Tail Recursive

	long ex_3_TR(long a, int n, long b);

//File Reading

	list read_f3(char* file);

	void pre_read_f3(list *l, char* c);

#endif /* EJERCICIO3_H_ */
