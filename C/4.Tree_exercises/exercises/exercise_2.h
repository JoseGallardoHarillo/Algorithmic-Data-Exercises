/*
 * exercise_2.h
 *
 *  Created on: 5 ene. 2021
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/matrices.h"
#include "types/tree.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>


#ifndef EJERCICIO2_H_
#define EJERCICIO2_H_

bool ex_2_aux(binary_tree* tree, list l, int i, bool r);

bool ex_2(binary_tree* tree, list l);

list * list_parse_dedicated(list * out, char * text);

#endif /* EJERCICIO2_H_ */
