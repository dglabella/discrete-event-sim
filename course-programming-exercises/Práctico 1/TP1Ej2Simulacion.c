/*
Observaciones
	- Baker está mucho más tiempo ocioso que Abel.
	- En promedio, cada cliente espera menos de 1 segundo.
	- Conclusión: Hay capacidad de atender a más clientes. 
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

const int MAX_CLIENTES = 10000; 
const int ABEL = 0;
const int BAKER = 1; 

typedef struct{
	int num_cliente;
	int t_entrearribos; 
	int t_arribo;
	int cuando_termina_abel;
	int cuando_termina_baker; 
	int servidor_elegido; 
	int t_servicio; 
	int t_comienza_servicio;
	int t_fin_servicio; 
    int t_espera; 
}Fila_Tabla; 

// En esta estructura almacenamos los resultados de la simulación.
// Los valores se computan sobre la marcha. 
typedef struct{
	int min_espera;
	int max_espera; 
	int total_espera; 
	double promedio_espera; 
	int max_ocio_abel;
	int min_ocio_abel;
	int total_ocio_abel;
	double porcentaje_ocio_abel; 
	int max_ocio_baker;
	int min_ocio_baker;
	int total_ocio_baker; 
	double porcentaje_ocio_baker;  
}Resultados_Simulacion; 


void inicializar_tabla(Fila_Tabla ts[])
{
	for(int i = 0; i < MAX_CLIENTES; i++)
	{
		ts[i].num_cliente = i+1; 
		ts[i].t_entrearribos = -1;
		ts[i].t_arribo = -1;
		ts[i].cuando_termina_abel = -1;
		ts[i].cuando_termina_baker = -1;
		ts[i].servidor_elegido = -1;
		ts[i].t_servicio = -1;
		ts[i].t_comienza_servicio = -1;
		ts[i].t_fin_servicio = -1; 
		ts[i].t_espera = -1; 
	}
}

void inicializar_resultados(Resultados_Simulacion* resultados)
{
	resultados->min_espera = 9999;
	resultados->max_espera = -1; 
	resultados->total_espera = 0;
	resultados->promedio_espera = 0.0; 
	resultados->max_ocio_abel = -1;
	resultados->min_ocio_abel = 9999;
	resultados->total_ocio_abel = 0;
	resultados->porcentaje_ocio_abel = 0.0; 
	resultados->max_ocio_baker = -1;
	resultados->min_ocio_baker = 9999;
	resultados->total_ocio_baker = 0;
	resultados->porcentaje_ocio_baker = 0.0; 
}

int min(int a, int b)
{
	if(a <= b) 
		return a;
	else 
		return b; 
}

int max(int a, int b)
{
	if(a <= b)
    	return b;
	else 
		return a; 
}

int obtener_aleatorio_abel()
{
	double aleatorio = (double)rand() / (double)RAND_MAX;
       	if(aleatorio <= 0.39)
		return 2;
	else if (aleatorio <= 0.58)
		return 3;
	else if (aleatorio <= 0.83)
		return 4;
	else 
		return 5; 	
}

int obtener_aleatorio_baker()
{
	double aleatorio = (double)rand() / (double)RAND_MAX;
	if(aleatorio <= 0.35)
		return 3;
	else if (aleatorio <= 0.62)
		return 4;
	else if (aleatorio <= 0.77)
		return 5;
	else
		return 6; 
}

int obtener_aleatorio_entrearribos()
{
	double aleatorio = (double)rand() / (double)RAND_MAX;
	if(aleatorio <= 0.25)
		return 1;
	else if(aleatorio <= 0.65)
		return 2;
	else if(aleatorio <= 0.85)
		return 3;
	else 
		return 4; 
}

void imprimir_resultados(Fila_Tabla ts[], int num_clientes, Resultados_Simulacion resultados)
{
	printf("-------------------------------------------\n"); 
	for(int i = 0; i < num_clientes; i++)
	{
		printf("Num Cliente: %d\n", ts[i].num_cliente);
	        printf("T. Entrearribos: %d\n", ts[i].t_entrearribos); 
	        printf("T. Arribo: %d\n", ts[i].t_arribo);
	        printf("T. Cuando Termina Abel: %d\n", ts[i].cuando_termina_abel); 
	        printf("T. Cuando Termina Baker: %d\n", ts[i].cuando_termina_baker); 
	        if(ts[i].servidor_elegido == ABEL)
	        	printf("Servidor Elegido: Abel\n");
	        else 
	        	printf("Servidor Elegido: Baker\n");
	        printf("T. Servicio: %d\n", ts[i].t_servicio); 
	        printf("T. Comienza Servicio: %d\n", ts[i].t_comienza_servicio);
	        printf("T. Fin Servicio: %d\n", ts[i].t_fin_servicio); 
	        printf("T. Espera en cola: %d\n", ts[i].t_espera); 
		printf("----------------------------------------\n"); 
	}

	
	printf("Min Espera: %d\n", resultados.min_espera);
	printf("Max Espera: %d\n", resultados.max_espera);
	printf("Total Espera: %d\n", resultados.total_espera);
	printf("Promedio Espera: %f\n\n\n", resultados.promedio_espera); 
	printf("Max Ocio Abel: %d\n", resultados.max_ocio_abel);
	printf("Min Ocio Abel: %d\n", resultados.min_ocio_abel);
	printf("Total Ocio Abel: %d\n", resultados.total_ocio_abel);
	printf("Promedio Ocio Abel: %f\n\n\n", resultados.porcentaje_ocio_abel); 
	printf("Max Ocio Baker: %d\n", resultados.max_ocio_baker);
	printf("Min Ocio Baker: %d\n", resultados.min_ocio_baker);
	printf("Total Ocio Baker: %d\n", resultados.total_ocio_baker);
	printf("Promedio Ocio Baker: %f\n", resultados.porcentaje_ocio_baker); 
	

}

int simular_abel_realiza_servicio(Fila_Tabla ts[], int i, int ult_servicio_abel, Resultados_Simulacion* resultados)
{
	int intervalo_ocio_abel = 0;

	// Marcamos que Abel realiza la inspección:
	ts[i].servidor_elegido = ABEL; 

	// Calculamos cuando comienza el servicio para el cliente:
	ts[i].t_comienza_servicio = max(ts[i].t_arribo, ult_servicio_abel); 

	// Actualizamos tiempo de ocio de Abel: 
	intervalo_ocio_abel = ts[i].t_comienza_servicio - ult_servicio_abel; 
   	resultados->max_ocio_abel = max(resultados->max_ocio_abel, intervalo_ocio_abel);
	resultados->min_ocio_abel = min(resultados->min_ocio_abel, intervalo_ocio_abel); 
	resultados->total_ocio_abel += intervalo_ocio_abel; 

	// A continuación utilizamos números random para saber cuanto tiempo demora 
	// la inspección del cliente:
	ts[i].t_servicio = obtener_aleatorio_abel(); 				
	
	// Calculamos cuando termina el servicio al cliente:
	ts[i].t_fin_servicio = ts[i].t_comienza_servicio + ts[i].t_servicio; 

	// Obtenemos tiempo de espera:
	ts[i].t_espera = ts[i].t_comienza_servicio - ts[i].t_arribo; 

	// Actualizamos resultados:
	resultados->min_espera = min(resultados->min_espera, ts[i].t_espera); 
	resultados->max_espera = max(resultados->max_espera, ts[i].t_espera); 
	resultados->total_espera += ts[i].t_espera; 

	// Retornamos el tiempo en que Abel termina su último servicio:
	return ts[i].t_fin_servicio;
}

int simular_baker_realizar_servicio(Fila_Tabla ts[], int i, int ult_servicio_baker, Resultados_Simulacion* resultados)
{
	int intervalo_ocio_baker = 0; 

	// Marcamos que Baker realiza la inspección:
	ts[i].servidor_elegido = BAKER;

	// Calculamos cuando comienza el servicio para el cliente:
	ts[i].t_comienza_servicio = max(ts[i].t_arribo, ult_servicio_baker); 

	// Actualizamos tiempo de ocio de Baker: 
	intervalo_ocio_baker = ts[i].t_comienza_servicio - ult_servicio_baker; 
   	resultados->max_ocio_baker = max(resultados->max_ocio_baker, intervalo_ocio_baker);
	resultados->min_ocio_baker = min(resultados->min_ocio_baker, intervalo_ocio_baker); 
	resultados->total_ocio_baker += intervalo_ocio_baker; 

	// A continuación utilizamos números random para saber cuanto tiempo demora 
	// la inspección del cliente:
	ts[i].t_servicio = obtener_aleatorio_baker();

	// Calculamos cuando termina el servicio al cliente:
	ts[i].t_fin_servicio = ts[i].t_comienza_servicio + ts[i].t_servicio;

	// Obtenemos tiempo de espera:
	ts[i].t_espera = ts[i].t_comienza_servicio - ts[i].t_arribo;

	// Actualizamos resultados:
	resultados->min_espera = min(resultados->min_espera, ts[i].t_espera); 
	resultados->max_espera = max(resultados->max_espera, ts[i].t_espera); 
	resultados->total_espera += ts[i].t_espera; 

	// Retornamos el tiempo en que Abel termina su último servicio:
	return ts[i].t_fin_servicio; 
}

int main()
{
	srand(time(0)); 

	// Inicializamos variables donde se almacenan los resultados de la simulación. 
	Resultados_Simulacion resultados;
	inicializar_resultados(&resultados);  

	// Declaramos la tabla de simualación y la inicializamos:
	Fila_Tabla ts[MAX_CLIENTES];
	inicializar_tabla(ts); 

	// Especificamos las variables auxiliares para la simulación:
	int t_ult_servicio_abel = 0, t_ult_servicio_baker = 0; 
	int t_ult_ocio_abel = 0, t_ult_ocio_baker = 0;
	int i = 1;
	
	
	// Realizamos la simulación para el primer cliente (El que se encuentra en la
	// cola por defecto):
	ts[0].t_arribo = 0;
	ts[0].cuando_termina_abel = 0;
	ts[0].cuando_termina_baker = 0;
	t_ult_servicio_abel = simular_abel_realiza_servicio(ts, 0, t_ult_servicio_abel, &resultados); 
	
	while(ts[i-1].t_fin_servicio < 60 && i < MAX_CLIENTES)
	{
		// Obtenemos un numero random para saber el nuevo tiempo entre arribos:
		ts[i].t_entrearribos = obtener_aleatorio_entrearribos(); 

		// Calculamos el tiempo de arribo del nuevo cliente:
		ts[i].t_arribo = ts[i-1].t_arribo + ts[i].t_entrearribos;	

		// Guardamos los tiempos en los que Abel y Baker iban a terminar
		// su servicio en el momento en que arriba el nuevo cliente. 
		ts[i].cuando_termina_abel = t_ult_servicio_abel;
		ts[i].cuando_termina_baker = t_ult_servicio_baker; 

		// Si al momento de arribar el cliente ambos empleados están desocupados, o 
		// si Abel termina su trabajo antes que Baker,  
		if( (ts[i].t_arribo >= t_ult_servicio_abel &&  ts[i].t_arribo >= t_ult_servicio_baker) || 
				t_ult_servicio_abel <= t_ult_servicio_baker)
		{
			// entonces Abel realiza el servicio:
			t_ult_servicio_abel = simular_abel_realiza_servicio(ts, i, t_ult_servicio_abel, &resultados); 
		}
		else
		{
			// Si no, Baker realiza el servicio: 
			t_ult_servicio_baker = simular_baker_realizar_servicio(ts, i, t_ult_servicio_baker, &resultados); 
		}

		// Aumentamos el número de clientes:
		i++;	
	}
	
	//Ajustamos el tiempo de ocio de Baker y Abel:

	// Si el último servicio lo realizó Abel,
	if(ts[i-1].servidor_elegido == ABEL)
	{
		// entonces ajustamos los tiempos de ocio de Baker:
		t_ult_ocio_baker = ts[i-1].t_fin_servicio - t_ult_servicio_baker; 
		resultados.max_ocio_baker =  max(resultados.max_ocio_baker, t_ult_ocio_baker);
		resultados.min_ocio_baker =  min(resultados.min_ocio_baker, t_ult_ocio_baker); 
		resultados.total_ocio_baker += t_ult_ocio_baker; 
	}
	else
	{
		// Si no, ajustamos los tiempos de ocio de Abel: 
		t_ult_ocio_abel = ts[i-1].t_fin_servicio - t_ult_servicio_abel; 
   		resultados.max_ocio_abel = max(resultados.max_ocio_abel, t_ult_ocio_abel);
		resultados.min_ocio_abel = min(resultados.min_ocio_abel, t_ult_ocio_abel); 
		resultados.total_ocio_abel += t_ult_ocio_abel; 
	}

	// Calculamos los resultados restantes:
	resultados.promedio_espera = (double)resultados.total_espera / (double)i; 
	resultados.porcentaje_ocio_abel = (double)resultados.total_ocio_abel / (double)ts[i-1].t_fin_servicio; 
	resultados.porcentaje_ocio_baker = (double)resultados.total_ocio_baker / (double)ts[i-1].t_fin_servicio; 

	// Imprimimos resultados:
	imprimir_resultados(ts, i, resultados); 
	return 0; 
}
