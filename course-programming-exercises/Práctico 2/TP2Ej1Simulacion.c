#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define CABEZA_FORMATO_SALIDA "%-15s%-24s%-20s%-24s%-24s%-19s%-21s%-19s\n"
#define ENTIDAD_FORMATO_SALIDA "%-15d%-24d%-20d%-24d%-24d%-19d%-21d%-19d\n"
#define MAX_ENTIDADES 1000

typedef struct
{
    int num_entidad; 
    int t_entre_arribos;
    int t_arribo; 
    int comienzo_servicio;
    int duracion_servicio;
    int fin_servicio;
    int t_espera;
    int serv_ocioso; 
} Fila_Entidad; 

typedef struct
{
    int min_espera;
	int max_espera; 
	int total_espera; 
	double promedio_espera; 
	int max_ocio;
	int min_ocio_abel;
	int total_ocio;
	double porcentaje_ocio; 
} Resultados_Simulacion; 

int max(int a, int b)
{
	if(a <= b)
    	return b;
	else 
		return a; 
}

/*
    Descripción:
        Devuelve un tiempo entre arribo de acuerdo a la siguiente distribución:
        
        Tiempo entre arribos | Probabilidad
                           4 |         0.34
                           5 |         0.48
                           7 |         0.18 
*/
int obtener_random_distribucion_arribo();

/*
    Descripción:
        Devuelve un tiempo de servicio de acuerdo a la siguiente distribución:
        
        Tiempo de servicio | Probabilidad
                         1 |         0.05
                         2 |         0.17
                         3 |         0.23
                         4 |         0.25
                         5 |         0.18
                         6 |         0.12 
*/
int obtener_random_distribucion_servicio();

/*
    Descripción:
        Simula un sistema de colas con un único servidor volcando los resultados en tabla_de_entidades. 
        La simulación corre hasta que una entidad finalize su servicio en un tiempo >= t_max_simulacion o 
        se alcance t_max_entidades. La función devuelve la cantidad de entidades simualdas. 

    Precondiciones:
        tabla_de_entidades declarada con una capacidad máxima de 1000 elementos inclusive. 
        1 <= t_max_simulacion
        1 <= max_entidades <= 1000 
*/
int simular_con_tabla_de_entidades(Fila_Entidad tabla_entidades[], int t_max_simulacion, int max_entidades); 

/*
    Descripción:
        Imprime contenido de tabla_de_entidades. 

    Precondiciones:
        tabla_de_entidades declarada con una capacidad máxima de 1000 elementos inclusive. 
        1 <= cant_entidades
*/
void imprimir_tabla_de_entidades(Fila_Entidad tabla_entidades[], int cant_entidades);

/*
    Descripción:
        Imprime en pantalla los eventos de arribo y salida de tabla_de_entidades ordenados cronológicamente. 

    Precondiciones:
        tabla_de_entidades llena con datos hasta num_entidades entidades. 
        num_entidades >= 1
*/
void imprimir_eventos_ordenados_cronologicamente(Fila_Entidad tabla_entidades[], int num_entidades); 

/*
    Descripción:
        Imprime en pantalla los tiempos máximo de espera y ocio, tiempo promedio de espera y porcentaje
        de tiempo ocioso. 

    Precondiciones:
        tabla_de_entidades llena con datos hasta num_entidades entidades. 
        num_entidades >= 1
*/
void calcular_e_imprimir_resultados(Fila_Entidad tabla_entidades[], int num_entidades);

int main()
{
    srand(time(0)); 

    Fila_Entidad tabla_entidades[MAX_ENTIDADES]; 
    int t_max_simulacion = 0, max_entidades_usuario = 0, num_entidades_simuladas = 0; 
    printf("Primero voy a realizar la simulación rellenando la tabla de entidades.\n");
    printf("Ingrese el tiempo máximo de simulación (> 0): "); 
    scanf("%d", &t_max_simulacion); 
    if(t_max_simulacion > 0)
    {
        printf("Ingrese el número máximo de entidades que puede tener la tabla (1 <= max <= 1000):"); 
        scanf("%d", &max_entidades_usuario);
        if(1 <= max_entidades_usuario && max_entidades_usuario <= MAX_ENTIDADES)
        {
            num_entidades_simuladas = simular_con_tabla_de_entidades(tabla_entidades, t_max_simulacion, max_entidades_usuario); 
            printf("La tabla de entidades obtenidas es la siguiente:\n");
            imprimir_tabla_de_entidades(tabla_entidades, num_entidades_simuladas); 
            printf("Los eventos ordenados cronológicamente son los siguientes:\n");
            imprimir_eventos_ordenados_cronologicamente(tabla_entidades, num_entidades_simuladas); 
            printf("Los resultados de la simulación son los siguientes:\n");
            calcular_e_imprimir_resultados(tabla_entidades, num_entidades_simuladas); 
            printf("Aquí termina el programa.\n"); 
        } 
        else
        {
            printf("Error: El número máximo de entidades debe ser un número entre 1 y 1000 inclusive.\n"); 
        }   
    }
    else
    {
        printf("Error: El tiempo máximo de simulación debe ser > 0.\n"); 
    }
    return 0;
}

int simular_con_tabla_de_entidades(Fila_Entidad te[], int t_max_simulacion, int max_entidades)
{
    // Debido a la restricción t_max_simulacion > 0 y que hay un elemento por defecto en la cola,
    // siempre simulamos al menos a una entidad. 
    int i = 0; 

    // Simulación de entidad por defecto: 
    te[i].num_entidad = 1; 
    te[i].t_entre_arribos = 0; 
    te[i].t_arribo = 0; 
    te[i].comienzo_servicio = 0; 
    te[i].duracion_servicio = obtener_random_distribucion_servicio(); 
    te[i].fin_servicio = te[i].duracion_servicio;
    te[i].t_espera = 0; 
    te[i].serv_ocioso = 0; 

    // Simulamos el resto de entidades: 
    i++; 
    while(te[i-1].fin_servicio < t_max_simulacion && i < max_entidades)
    {
        te[i].num_entidad = i+1; 
        te[i].t_entre_arribos = obtener_random_distribucion_arribo(); 
        te[i].t_arribo = te[i-1].t_arribo + te[i].t_entre_arribos; 
        te[i].comienzo_servicio = max(te[i-1].fin_servicio, te[i].t_arribo); 
        te[i].duracion_servicio = obtener_random_distribucion_servicio(); 
        te[i].fin_servicio = te[i].comienzo_servicio + te[i].duracion_servicio;
        te[i].t_espera = te[i].comienzo_servicio - te[i].t_arribo; 
        te[i].serv_ocioso = te[i].comienzo_servicio - te[i-1].fin_servicio; 
        i++; 
    }   
    return i;  
}

void imprimir_tabla_de_entidades(Fila_Entidad te[], int cant_entidades)
{
    int i = 0; 
    // Títulos de cabezera de tabla: 
    char* cab[8] = {"Num. Entidad", "Tiempo entre arribos", "Tiempo de arribo", "Comienzo de servicio", 
         "Duracion de servicio", "Fin de servico", "Tiempo de espera", "Servidor ocioso"}; 
    printf(CABEZA_FORMATO_SALIDA, cab[0], cab[1], cab[2], cab[3], cab[4], cab[5], cab[6], cab[7]);
    for(int i = 0; i < cant_entidades; i++)
    {
        printf(ENTIDAD_FORMATO_SALIDA, te[i].num_entidad, te[i].t_entre_arribos, te[i].t_arribo, te[i].comienzo_servicio, 
                                    te[i].duracion_servicio, te[i].fin_servicio, te[i].t_espera, te[i].serv_ocioso);
    }   
}

void imprimir_eventos_ordenados_cronologicamente(Fila_Entidad te[], int num_entidades)
{
    int cur_arr = 1, cur_sal = 0;
    printf("(Arribo, E1, 0)\n");
    while(cur_sal < num_entidades)
    {
        if(cur_arr < num_entidades && te[cur_arr].t_arribo < te[cur_sal].fin_servicio)
        {
            printf("(Arribo, E%d, %d)\n", cur_arr+1, te[cur_arr].t_arribo);
            cur_arr++; 
        }
        else
        {
            printf("(Salida, E%d, %d)\n", cur_sal+1, te[cur_sal].fin_servicio);
            cur_sal++; 
        }
    } 
    
}

void calcular_e_imprimir_resultados(Fila_Entidad te[], int num_entidades)
{
    int max_espera = 0, max_ocioso = 0, total_espera = 0, total_ocio = 0, 
       total_simulacion = te[num_entidades-1].fin_servicio;
    double promedio_espera = 0, porcentaje_ocioso = 0; 
    for(int i = 1; i < num_entidades; i++)
    {
        total_espera += te[i].t_espera; 
        total_ocio += te[i].serv_ocioso; 
        if(te[i].t_espera > max_espera)
            max_espera = te[i].t_espera; 
        if(te[i].serv_ocioso > max_ocioso)
            max_ocioso = te[i].serv_ocioso; 
    }
    promedio_espera = (double)total_espera/(double)num_entidades; 
    porcentaje_ocioso = (double)total_ocio/(double)total_simulacion; 
	printf("Max espera: %d\n", max_espera);
	printf("Promedio espera: %f\n", promedio_espera); 
	printf("Max ocio: %d\n", max_ocioso);
    printf("Porcentaje ocio: %f\n", porcentaje_ocioso); 
}

int obtener_random_distribucion_arribo()
{
	double aleatorio = (double)rand() / (double)RAND_MAX;
    if(aleatorio <= 0.34)
		return 4;
	else if (aleatorio <= 0.72)
		return 5;
	else 
		return 7; 	
}

int obtener_random_distribucion_servicio()
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