/*
 * exercise_3.h
 *
 *  Created on: 7 dic. 2020
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/matrices.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>

#ifndef EJERCICIO3_H_
#define EJERCICIO3_H_

//Stateless

long long ex_3_SL(int n);

//Stateful

long long ex_3_SF_aux(int n, hash_table* mem);
long long ex_3_SF(int n);

//Iterative

long long ex_3_I(int n);

//File Reading

list read_f_3(char* file);

#endif /* EJERCICIO3_H_ */
