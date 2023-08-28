#ifndef TP2_EJ2_WAITING_LIST_H
#define TP2_EJ2_WAITING_LIST_H

#define EXITO 1
#define FRACASO 0
#define TRUE 1
#define FALSE 0 

typedef struct Nodo_Cola
{
    int num_entidad; 
    int t_arribo; 
    struct Nodo_Cola* next; 
    struct Nodo_Cola* prev; 
}Nodo_Cola; 

typedef struct
{
    int cant_entidades; 
    int max_entidades; 
    Nodo_Cola* cabeza; 
    Nodo_Cola* cola; 
}Cola_Espera; 

void inicializar_cola_de_espera(Cola_Espera* queue)
{
    queue->cant_entidades = 0;
    queue->max_entidades = 0;
    queue->cabeza = NULL;
    queue->cola = NULL; 
}

int insertar_en_cola_de_espera(Cola_Espera* queue, int num_ent, int t_arr)
{
    int resultado_operacion = EXITO; 
    Nodo_Cola* nuevo = (Nodo_Cola*)malloc(sizeof(Nodo_Cola));
    if(nuevo != NULL)
    {
        nuevo->num_entidad = num_ent;
        nuevo->t_arribo = t_arr; 
        nuevo->prev = NULL; 
        nuevo->next = queue->cola; 
        if(queue->cant_entidades == 0)
        {
            queue->cabeza = nuevo; 
        }
        else
        {
            queue->cola->prev = nuevo; 
        }
        queue->cola = nuevo; 
        resultado_operacion = EXITO; 
        queue->cant_entidades++;
        if(queue->cant_entidades > queue->max_entidades)
           queue->max_entidades = queue->cant_entidades; 
    }
    else
    {
        resultado_operacion = FRACASO;
        free(nuevo); 
    }
    return resultado_operacion;
}

void quitar_entidad_de_cola_de_espera(Cola_Espera* queue, int* num_entidad, int* t_arribo)
{
    Nodo_Cola* elim; 
    if(queue->cant_entidades > 0)
    {
        *num_entidad = queue->cabeza->num_entidad; 
        *t_arribo = queue->cabeza->t_arribo;
        elim = queue->cabeza; 
        if(queue->cant_entidades == 1)
        {
            queue->cabeza = NULL;
            queue->cola = NULL; 
        }
        else
        {
            queue->cabeza = queue->cabeza->prev; 
            queue->cabeza->next = NULL; 
        }
        queue->cant_entidades--; 
        free(elim); 
    }
}

int cola_de_espera_vacia(Cola_Espera* queue)
{
    if(queue->cant_entidades == 0)
        return TRUE;
    else 
        return FALSE; 
}


#endif
