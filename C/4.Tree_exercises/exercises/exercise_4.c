/*
 * exercise_4.c
 *
 *  Created on: 5 ene. 2021
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_4.h"

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

#include "exercise_3.h"

void ex_4_aux(tree* tree, hash_table* dictionary, int i) {

	switch(tree->tree_type) {

		case Empty_Tree:
			break;

		case Leaf_Tree:
			break;

		case Tree:

			if(tree_child_number(tree)%2==0) {
				list* l = (list*) hash_table_get(dictionary,&i);
				list_add(l,tree_label(tree));
			}

			for(int j=0;j<tree_child_number(tree);j++){
				ex_4_aux(tree_get_child(tree,j),dictionary,i+1);
			}
	}
}

hash_table ex_4(tree* tree) {

	hash_table dictionary = hash_table_empty(int_type,list_type);

	for(int k=0;k<=getHeight(tree,0);k++) {

		list lk = list_empty(tree->type_label);

		hash_table_put(&dictionary,&k,&lk);
	}

	ex_4_aux(tree,&dictionary,0);

	return dictionary;
}
