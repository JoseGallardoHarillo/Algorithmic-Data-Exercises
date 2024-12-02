/*
 * exercise_1.h
 *
 *  Created on: 27 oct. 2020
 *      Author: Jose Gallardo Harillo
 */

#ifndef EJERCICIO1_H_
#define EJERCICIO1_H_

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"
#include <stdbool.h>
#include <math.h>
#include "../types/hash_table.h"

list ex_1(list ll);
void pre_read_f(list *l, char* caracter);
list read_f(char* file);

#endif /* EJERCICIO1_H_ */
