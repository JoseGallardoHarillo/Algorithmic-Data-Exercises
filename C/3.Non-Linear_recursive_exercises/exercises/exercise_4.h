/*
 * exercise_4.h
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


#ifndef EJERCICIO4_H_
#define EJERCICIO4_H_

//Stateless

int ex_4_SL(int a, int b);

//Stateful

int ex_4_SF(int a, int b);
int ex_4_SF_aux(int a, int b, int mem[a+1][b+1]);

//Iterativo

int ex_4_It(int a, int b);

//Para lectura del fichero

void pre_read_f_4(list *l, char* caracter);
list read_f_4(char* file);


#endif /* EJERCICIO4_H_ */
