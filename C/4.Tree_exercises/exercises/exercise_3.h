/*
 * exercise_3.h
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

#ifndef EJERCICIO3_H_
#define EJERCICIO3_H_

bool p_is_par(void* a);
bool p_is_prime(void* a);
int getHeight(tree* tree, int level);
void ex_3_aux(tree* tree, bool predicate(void*), list l,int i);
list ex_3(tree* tree,  bool predicate(void*));

#endif /* EJERCICIO3_H_ */
