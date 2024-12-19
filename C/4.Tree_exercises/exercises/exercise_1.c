/*
 * exercise_1.c
 *
 *  Created on: 5 ene. 2021
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_1.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include "types/tree.h"
#include "types/matrices.h"
#include "types/hash_table.h"
#include "types/tree.h"
#include <stdbool.h>
#include <math.h>


bool ex_1(binary_tree *tree){

	bool r = true;

	switch(tree->tree_type) {

		case Empty_Binary_Tree:
			break;

		case Leaf_Binary_Tree:
			break;

		case Binary_Tree:

			if(*(int*) tree->label== *(int*) tree->left->label+ *(int*) tree->right->label) {
				r= ex_1(tree->left)&&ex_1(tree->right);
			}

			else{
				r=false;
			}
	}

	return r;
}
