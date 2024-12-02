/*
 * ejercicio3.c
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
#include "../ejercicios/ejercicio3.h"


//Ejercicio 3

hash_table ej3(list l){

	iterator it = list_iterable(&l);
	hash_table diccionario = hash_table_empty(int_type,double_type);
	punto *p;
	double x= 0.0;
	while(iterable_has_next(&it)) {

		p= (punto*) iterable_next(&it);
		Cuadrante cuadrante = punto_cuadrante(p);
		double * valor = (double*) hash_table_get(&diccionario,&cuadrante);
		x = p->x;
		double v = 0.0;

		if (valor==NULL){
			v=x;
			hash_table_put(&diccionario,&cuadrante,&v);
		}
		else if(valor!=NULL){
			v = *valor+x;
			hash_table_put(&diccionario,&cuadrante,&v);
		}
	}
	return diccionario;
}

//Para lectura del fichero

void leer_f3(const char* file, list* l){
	iterator it = file_iterable_pchar(file);
	while(iterable_has_next(&it)){
		char * c = (char*) iterable_file_next(&it);
		punto p = punto_parse_s(c);
		list_add(l,&p);
	}
}


