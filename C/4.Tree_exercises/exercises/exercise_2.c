/*
 * exercise_2.c
 *
 *  Created on: 5 ene. 2021
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_2.h"

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


bool ex_2_aux(binary_tree* tree, list l, int i, bool r) {

	if(i>=l.size-1) {
		i=0;
	}
	char character = *(char*) list_get(&l,i);
	char nextChar = *(char*) list_get(&l,i+1);

	if(r!=true) {

		switch(tree->tree_type) {

			case Empty_Binary_Tree:
				break;

			case Leaf_Binary_Tree:

				if(*(char*) tree->label== *(char*) list_get(&l,l.size-1)) r=true;

				else r=false;

				break;

			case Binary_Tree:

				if(*(char*) tree->label==character) {

					if(*(char*) tree->left->label== nextChar) r= ex_2_aux(tree->left,l,i+1,false);

					if(*(char*) tree->right->label== nextChar) r= ex_2_aux(tree->right,l,i+1,false);
				}

				else r=false;
		}
	}

	return r;
}

bool ex_2(binary_tree* tree, list l) {
	return ex_2_aux(tree, l, 0, false);
}


//File Reading

list * list_parse_dedicated(list * out, char * text) {
	iterator it = text_to_iterable_pchar(text, "[ ,]");
	while(iterable_has_next(&it)){
		void * e = iterable_next(&it);
		list_add(out,e);
	}
	iterable_free(&it);
	return out;
}
