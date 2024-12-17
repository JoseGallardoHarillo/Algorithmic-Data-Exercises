/*
 * exercise_2.h
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
#include "ejemplos/tupla.h"

#ifndef EJERCICIO2_H_
#define EJERCICIO2_H_

smax ex_2_aux_2(list li, int left, int k, int right);

smax ex_2_aux(list li, int left, int right);

smax ex_2(list li);

//File Reading

void pre_read_2(list *l, char* caracter);

list read_f_2(char* file);

#endif /* EJERCICIO2_H_ */
