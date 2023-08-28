#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "TP2Ej2WaitingList.h" 
#include "TP2Ej2FEL.h"

#define INF 9999
#define NEG_INF -1

typedef struct 
{
    int servidor_ocioso; 
    int num_entidades; 
    int contador_arribos;
    int ult_salida; 
    int tiempo_transcurrido; 
}Estado_Sistema; 

typedef struct
{
    int min_espera;
	int max_espera; 
	int total_espera; 
	double promedio_espera; 
	int max_ocio;
	int min_ocio;
	int total_ocio;
	double porcentaje_ocio; 
    int total_entidades; 
    int max_transito;
    int min_transito; 
    int total_transito; 
    double promedio_transito; 
    int max_cola; 
    int max_entidades; 
} Resultados; 

void inicializar_sistema(Estado_Sistema* estado_actual)
{
    estado_actual->servidor_ocioso = TRUE;
    estado_actual->num_entidades = 0;
    estado_actual->contador_arribos = 0; 
    estado_actual->ult_salida = 0; 
    estado_actual->tiempo_transcurrido = 0; 
}

void inicializar_resultados(Resultados* res)
{
    res->min_espera = INF;
    res->max_espera = NEG_INF;
    res->total_espera = 0;
    res->promedio_espera = 0.0; 
    res->max_ocio = NEG_INF;
    res->min_ocio = INF;
    res->total_ocio = 0;
    res->porcentaje_ocio = 0.0;
    res->total_entidades = 0; 
    res->max_transito = NEG_INF;
    res->min_transito = INF;
    res->promedio_transito = 0; 
    res->total_transito = 0; 
    res->max_cola = 0; 
    res->max_entidades = 0; 
}

int nuevo_tiempo_entrearribos()
{
	double aleatorio = (double)rand() / (double)RAND_MAX;
    if(aleatorio <= 0.34)
		return 4;
	else if (aleatorio <= 0.72)
		return 5;
	else 
		return 7; 	
}

int nuevo_tiempo_servicio()
{
	double aleatorio = (double)rand() / (double)RAND_MAX;
    if(aleatorio <= 0.05)
		return 1;
	else if (aleatorio <= 0.22)
		return 2;
	else if (aleatorio <= 0.45)
		return 3;
	else if (aleatorio <= 0.70)
		return 4; 
    else if (aleatorio <= 0.88)
        return 5;
    else 
        return 6; 
}

int max(int a, int b)
{
    if(a <= b)
        return b;
    else 
        return a; 
}

int min(int a, int b)
{
    if(a <= b)
        return a;
    else   
        return b; 
}

void imprimir_resultados(Resultados res)
{
    printf("Max espera: %d\n", res.max_espera); 
    printf("Min espera: %d\n", res.min_espera); 
    printf("Promedio espera: %f\n", res.promedio_espera); 
    printf("\n");
    printf("Max ocio: %d\n", res.max_ocio); 
    printf("Min ocio: %d\n", res.min_ocio); 
    printf("Total ocio: %d\n", res.total_ocio); 
    printf("Porcentaje ocio: %f\n", res.porcentaje_ocio); 
    printf("\n");
    printf("Max transito: %d\n", res.max_transito); 
    printf("Min transito: %d\n", res.min_transito); 
    printf("Promedio transito: %f\n", res.promedio_transito); 
    printf("\n");
    printf("Max entidades en sistema: %d\n", res.max_entidades); 
    printf("Total entidades en sistema: %d\n", res.total_entidades); 
    printf("\n");
    printf("Max cola de espera: %d\n", res.max_cola); 
}

Evento crear_evento_de_arribo(int id_entidad, int ult_arribo)
{
    Evento nuevo_arribo; 
    int t_entrearribos = nuevo_tiempo_entrearribos(); 
    nuevo_arribo.tipo = ARRIBO; 
    nuevo_arribo.entidad = id_entidad; 
    nuevo_arribo.tiempo = ult_arribo + t_entrearribos; 
    return nuevo_arribo; 
}

Evento crear_evento_de_salida(int id_entidad, int tiempo_ingreso)
{
    Evento nueva_salida; 
    int t_servicio= nuevo_tiempo_servicio(); 
    nueva_salida.tipo = SALIDA; 
    nueva_salida.entidad = id_entidad; 
    nueva_salida.tiempo = tiempo_ingreso + t_servicio; 
    return nueva_salida; 
}

void ajustar_resultados_sobre_ocio(Resultados* resultados, int t_ocio)
{
    resultados->total_ocio += t_ocio; 
    resultados->max_ocio = max(resultados->max_ocio, t_ocio);
    resultados->min_ocio = min(resultados->min_ocio, t_ocio);
}

void ajustar_resultados_sobre_espera(Resultados* resultados, int t_espera)
{
    resultados->total_espera += t_espera; 
    resultados->max_espera = max(resultados->max_espera, t_espera);
    resultados->min_espera = min(resultados->min_espera, t_espera);
}

void ajustar_resultados_sobre_transito(Resultados* resultados, int t_transito)
{
    resultados->total_transito += t_transito; 
    resultados->max_transito = max(resultados->max_transito, t_transito);
    resultados->min_transito = min(resultados->min_transito, t_transito);
}


void simular_arribo(Evento fel[], Cola_Espera* cola, Evento arribo_actual, Estado_Sistema* estado_actual, Resultados* resultados)
{
    printf("(Arribo, E%d, %d)\n", arribo_actual.entidad, arribo_actual.tiempo);

    int id_entidad = arribo_actual.entidad;
    int t_arribo = arribo_actual.tiempo; 
    int t_ocio = 0, t_espera = 0, t_transito = 0; 
    Evento nueva_salida, nuevo_arribo; 

    if(estado_actual->servidor_ocioso == TRUE)
    {
        t_ocio = t_arribo - estado_actual->ult_salida;
        t_espera = 0; 
        nueva_salida = crear_evento_de_salida(id_entidad, t_arribo);
        t_transito = nueva_salida.tiempo - t_arribo; 
        insertar_evento_en_fel(fel, nueva_salida);  
        ajustar_resultados_sobre_espera(resultados, t_espera);
        ajustar_resultados_sobre_transito(resultados, t_transito);
    }
    else
    {
        insertar_en_cola_de_espera(cola, id_entidad, t_arribo); 
    }

    nuevo_arribo = crear_evento_de_arribo(id_entidad+1, t_arribo);
    insertar_evento_en_fel(fel, nuevo_arribo);
    
    estado_actual->num_entidades++;
    estado_actual->contador_arribos += 1;
    estado_actual->servidor_ocioso = FALSE;  
    estado_actual->tiempo_transcurrido = t_arribo;
    ajustar_resultados_sobre_ocio(resultados, t_ocio);
    if(resultados->max_entidades < estado_actual->num_entidades)
        resultados->max_entidades = estado_actual->num_entidades; 
}

void simular_salida(Evento fel[], Cola_Espera* cola, Evento salida_actual, Estado_Sistema* estado_actual, Resultados* resultados)
{
   printf("(Salida, E%d, %d)\n", salida_actual.entidad, salida_actual.tiempo);

    int id_entidad = salida_actual.entidad;
    int t_salida = salida_actual.tiempo; 
    int id_frente;
    int t_arribo_frente; 
    int t_espera = 0; 
    int t_transito = 0; 
    Evento nueva_salida; 

    if(cola_de_espera_vacia(cola) == TRUE)
    {
        estado_actual->servidor_ocioso = TRUE; 
    }
    else
    {
        estado_actual->servidor_ocioso = FALSE; 
        quitar_entidad_de_cola_de_espera(cola, &id_frente, &t_arribo_frente); 
        nueva_salida = crear_evento_de_salida(id_frente, t_salida);
        insertar_evento_en_fel(fel, nueva_salida);    
        t_espera = t_salida - t_arribo_frente; 
        t_transito = nueva_salida.tiempo - t_arribo_frente;
        ajustar_resultados_sobre_espera(resultados, t_espera); 
        ajustar_resultados_sobre_transito(resultados, t_transito); 
    }
    estado_actual->num_entidades--; 
    estado_actual->tiempo_transcurrido = t_salida;
    estado_actual->ult_salida = t_salida; 
}

void simular_por_eventos(int max_minutos)
{
    Estado_Sistema estado_actual; 
    Evento evento_actual; 
    Evento fel[2];
    Cola_Espera cola_de_espera;  
    Resultados resultados; 

    inicializar_sistema(&estado_actual);
    inicializar_cola_de_espera(&cola_de_espera);  
    inicializar_fel(fel); 
    inicializar_resultados(&resultados);
    evento_actual = crear_evento_de_arribo(1, 0); 

    while(evento_actual.tiempo < max_minutos) 
    {
        switch(evento_actual.tipo)
        {
            case ARRIBO:
                simular_arribo(fel, &cola_de_espera, evento_actual, &estado_actual, &resultados); 
                break; 
            case SALIDA:
                simular_salida(fel, &cola_de_espera, evento_actual, &estado_actual, &resultados); 
                break;
            default:
                printf("Error: Estado no reconocido.\n");
        }
        evento_actual = quitar_evento_de_fel(fel); 
    }
    resultados.porcentaje_ocio = (double)resultados.total_ocio / (double)estado_actual.tiempo_transcurrido; 
    resultados.promedio_espera = (double)resultados.total_espera / (double)estado_actual.contador_arribos; 
    resultados.promedio_transito = (double)resultados.total_transito / (double)estado_actual.contador_arribos;
    resultados.total_entidades = estado_actual.contador_arribos;  
    resultados.max_cola = cola_de_espera.max_entidades; 
    imprimir_resultados(resultados); 
}

int main()
{
    srand(time(0));
    int max_minutos = 0;
    printf("Ingrese la cantidad de minutos máxima de la simulación: (> 7)\n");
    scanf("%d", &max_minutos);
    if(max_minutos > 7)
    {
        simular_por_eventos(max_minutos);           
    }
    else
    {
        printf("Error: La cantidad máxima de minutos de la simulación debe ser mayor a 7.\n"); 
    }
    printf("Aquí termina el programa.\n");
    return 0; 
}