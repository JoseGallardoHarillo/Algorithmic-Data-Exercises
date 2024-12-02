/*
 * exercise_3.h
 *
 *  Created on: 27 oct. 2020
 *      Author: Jose Gallardo Harillo
 */

#ifndef EJERCICIO3_H_
#define EJERCICIO3_H_
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"
#include <stdbool.h>
#include <math.h>
#include "../types/hash_table.h"

hash_table ex_3(list l);
void read_f3(const char* file, list* l);

#endif /* EJERCICIO3_H_ */
