/*
 * ejercicio2.c
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
#include "../ejercicios/ejercicio2.h"

//Ejercicio 2

string ej2(int limite){
	int i = 2;
	string mensaje = string_of_pchar("");
	char mem[10];
	while(i<=limite) {
		bool esPrimo=  es_primo(i);
		if(esPrimo==true) {
			int primoCuadrado = i*i;
			string memstr = string_of_pchar(int_tostring(&primoCuadrado,mem));
			string aparte = string_of_pchar("\n");
			string_add_string(&mensaje,&memstr);
			string_add_string(&mensaje,&aparte);
		}
		i++;
		}
	return mensaje;
}


//Para lectura del fichero

list leer_f2(char* file){
	list r = list_empty(int_type);
	iterator it = file_iterable_pchar(file);
	while(iterable_has_next(&it)){
		char * c = (char*) iterable_next(&it);
		iterator it2 = split_iterable_pchar(c,": ");
		char* s = iterable_next(&it2);
		char* s2 = iterable_next(&it2);
		int n = int_parse_s(s2);
		list_add(&r,&n);
		}
	return r;
	}
