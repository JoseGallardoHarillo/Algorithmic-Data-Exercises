/*
 * exercise_3.c
 *
 *  Created on: 5 ene. 2021
 *      Author: Jose Gallardo Harillo
 */

#include "exercise_3.h"

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


bool p_is_par(void* a){

	char* p = (char*) a;
	int n = int_parse_s(p);

	return es_par(n);
}

bool p_is_prime(void* a){

	char* p = (char*) a;
	int n = int_parse_s(p);

	return es_primo(n);
}


int getHeight(tree* tree, int level){

	int max = level;

	if(class_tree(tree)==Tree) for(int i=0;i<tree_child_number(tree);i++) max = MAX(max,getHeight(tree_get_child(tree,i),level+1));

	return max;
}

void ex_3_aux(tree* tree, bool predicate(void*), list l,int i){

	bool r = *(bool*) list_get(&l,i);

	switch(tree->tree_type) {

	case Empty_Tree:
		break;

	case Leaf_Tree:;

		bool test = predicate(tree_label(tree));

		if(r==true) list_set(&l,i,&test);

		break;

	case Tree:;

		test = predicado(tree_label(tree));

		if(r==true) list_set(&l,i,&test);

		for(int j=0;j<tree_child_number(tree);j++) ex_3_aux(tree_get_child(tree,j),predicate,l,i+1);
	}
}



list ex_3(tree* tree,  bool predicate(void*)){

	bool b = true;

	list l = list_empty(bool_type);
	int height = getHeight(tree,0);

	for(int i=0;i<=height;i++){
		list_add(&l,&b);
	}

	ej3aux(tree,predicate,l,0);

	return l;
}
