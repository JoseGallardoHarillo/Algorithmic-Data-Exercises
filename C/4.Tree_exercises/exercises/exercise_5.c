/*
 * exercise_5.c
 *
 *  Created on: 5 ene. 2021
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_5.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/set.h"
#include "types/types.h"
#include "types/tree.h"
#include "types/matrices.h"
#include "types/hash_table.h"
#include <stdbool.h>
#include <math.h>


void ex_5_aux(tree* treee, hash_table* dictionary) {
	int zero = 0;
	switch(treee->tree_type) {

		case Empty_Tree:

			if(hash_table_contains(dictionary,&zero)) {
				set* s = hash_table_get(dictionary,&zero);
				set_add(s,treee);
			}
			else {
				set s = set_empty(tree_type);
				set_add(&s,treee);
				hash_table_put(dictionary,0,&s);
			}

			break;

		case Leaf_Tree:

			if(hash_table_contains(dictionary,&zero)) {
				set* s = hash_table_get(dictionary,&zero);
				set_add(s,treee);
			}
			else {
				set s = set_empty(tree_type);
				set_add(&s,treee);
				hash_table_put(dictionary,&zero,&s);
			}

			break;

		case Tree:;

			int nh= tree_child_number(treee);

			if(hash_table_contains(dictionary,&nh)) {
				set* s = hash_table_get(dictionary,&nh);
				set_add(s,treee);
			}

			else {
				set s = set_empty(tree_type);
				set_add(&s,treee);
				hash_table_put(dictionary,&nh,&s);
												}
			for(int j=0;j<nh;j++) {
				tree* child = tree_get_child(treee,j);
				ej5aux(child, dictionary);
			}

			break;
		}
	}

hash_table ex_5(tree* tree) {

	hash_table dictionary = hash_table_empty(int_type,set_type);

	ej5aux(tree, &dictionary);

	return dictionary;
}
