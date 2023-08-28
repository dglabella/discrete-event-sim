#ifndef TP2_EJ2_FEL_H
#define TP2_EJ2_FEL_H

#define ARRIBO 0
#define SALIDA 1
#define INDEFINIDO 2 
#define TRUE 1
#define FALSE 0

typedef struct
{
    int tipo; 
    int entidad; 
    int tiempo; 
}Evento; 

void inicializar_fel(Evento fel[])
{
    fel[0].tipo = INDEFINIDO;
    fel[1].tipo = INDEFINIDO; 
}

void insertar_evento_en_fel(Evento fel[], Evento nuevo)
{
    if(fel[0].tipo == INDEFINIDO)
    {
        fel[0] = nuevo;
    }
    else if(nuevo.tiempo < fel[0].tiempo  || (nuevo.tiempo == fel[0].tiempo && nuevo.tipo == SALIDA))
    {
        fel[1] = fel[0];
        fel[0] = nuevo;
    }
    else
    {
        fel[1] = nuevo; 
    }
} 

Evento quitar_evento_de_fel(Evento fel[])
{
    Evento evento_retorno = fel[0]; 
    fel[0] = fel[1]; 
    fel[1].tipo = INDEFINIDO;
    return evento_retorno;
}

#endif
