/*
 * PI1.c
 *
 *  Created on: 10 oct. 2020
 *      Author: PC
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "types/list.h"
#include "types/types.h"
#include <stdbool.h>
#include <math.h>
#include "types/hash_table.h"
#include "ejercicios/ejercicio1.h"
#include "ejercicios/ejercicio2.h"
#include "ejercicios/ejercicio3.h"


//Tests

int main(){

	//Test 1

	char mem[256];

	printf("Ejercicio 1:");
	printf(" \n\n");

	char* fichero1 = "ficheros/PI1Ej1DatosEntrada.txt";
	list lineas = leer_f(fichero1);
	list res1 = ej1(lineas);
	list_tostring(&res1,mem);
	printf("%s\n",mem);
	printf(" \n\n");


	//Test 2

	char mem2[256];

	printf("Ejercicio 2:");
	printf(" \n\n");

	char* fichero2 = "ficheros/PI1Ej2DatosEntrada.txt";
	list lineas2 = leer_f2(fichero2);
	for(int i=0;i<lineas2.size;i++){
		int n = *(int*) list_get(&lineas2,i);
		string res2 = ej2(n);
		string_tostring(&res2,mem2);
		printf("Límite %d:\n",n);
		printf("%s",mem2);
		printf(" \n\n");
		printf("========================================\n");
	}



	//Test 3


	char mem3[256];


	printf("Ejercicio 3:");
	printf(" \n\n");
	const char* fichero3 = "ficheros/PI1Ej3DatosEntrada.txt";
	list r = list_empty(punto_type);
	leer_f3(fichero3,&r);
	hash_table res3 = ej3(r);
	hash_table_tostring(&res3,mem3);
	printf("%s\n",mem3);



}
