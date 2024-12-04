/*
 * exercise_2.h
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

#ifndef EJERCICIO2_H_
#define EJERCICIO2_H_

//Iterative

bool ex_2_It(int a, int b);

//Tail Recursive

bool ex_2_TR(int a, int b);

//File Reading

list read_f2(char* file);

void pre_read_f2(list *l, char* c);

#endif /* EJERCICIO2_H_ */
