/*
 * exercise_4.h
 *
 *  Created on: 5 ene. 2021
 *      Author: Jose Gallardo Harillo
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/tree.h"
#include "types/matrices.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>


#ifndef EJERCICIO4_H_
#define EJERCICIO4_H_

void ex_4_aux(tree* tree, hash_table* dictionary, int i);
hash_table ex_4(tree* tree);

#endif /* EJERCICIO4_H_ */
