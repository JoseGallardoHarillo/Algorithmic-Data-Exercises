/*
 * ejercicio1.c
 *
 *  Created on: 27 oct. 2020
 *      Author: PC
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../types/list.h"
#include "../types/types.h"
#include <stdbool.h>
#include <math.h>
#include "../types/hash_table.h"
#include "../ejercicios/ejercicio1.h"


//Ejercicio 1


list ej1(list ll){
	list lres= list_empty(int_type);
	iterator it = list_iterable(&ll);
	while(iterable_has_next(&it)){
		list sl = *(list*) iterable_next(&it);
		for(int j=0;j<sl.size;j++){
			bool esPrimo=  es_primo( *(int*) list_get(&sl,j));
					if(esPrimo==true){
						list_add(&lres,list_get(&sl,j));
					}
		}
	}
	return lres;
}



//Para lectura del fichero

list leer_f(char* file){
	list r = list_empty(list_type);
	iterator it = file_iterable_pchar(file);
	while(iterable_has_next(&it)){
		list li = list_empty(int_type);
		char * c = (char*) iterable_next(&it);
		prelectura(&li, c);
		list_add(&r,&li);
	}
return r;
}

void prelectura(list *l, char* caracter){
	iterator it = split_iterable_pchar(caracter, ", ");
	while(iterable_has_next(&it)){
		char* chara = (char*) iterable_next(&it);
		int n = int_parse_s(chara);
		list_add(l, &n);
	}
}
