/*
Víctor Jorge Sibaja
Pablo Gutiérrez Martínez
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class main {

    public static FileReader getEntrada(){
        File fichero = null;
        FileReader fr = null;

        try {
            fichero = new File("entrada 2.txt");
            fr = new FileReader(fichero);


        }
        catch (Exception e){
            e.printStackTrace();
        }

        return fr;
    }

    public static int primeraAparicion(int valor, int[] v, int izq){
        //Por defecto la solución será -1, lo cual indicará que el elemento no está en el rango seleccionado del vector
        int solucion = -1;
        for (int i= izq; i<v.length; i++){
            if (v[i] == valor){
                return i;
            }
        }
        return solucion;
    }

    /*
    Función que retorna la solución de un caso.
    pizas1 es la torre con menos piezas
    piezas2 es la torre con más piezas
     */
    public static void resolverCaso(int[] piezas1, int[] piezas2, int caso){
        /*
        Vector que almacena la solución. Inicialmente será null para que si no hay piezas comunes se retorne un objeto
        nulo
         */

        int[] resultado = new int[piezas1.length];
        int[] distancias = new int[piezas1.length];
        int altura = 0; //Altura de la solución

        /*
        min1: posicion de torre 1 con menor distancia
        min2: posicion de torre 2 con menor distancia
         */
        int izq,pos,min1,min2;
        izq = -1;



        for (int j = 0; j<piezas1.length; j++) {
            min2 = 101;
            min1 = -1;


            for (int i = j; i < piezas1.length; i++) {
                pos = primeraAparicion(piezas1[i], piezas2, izq+1);
                //System.out.println("DISTANCIA: " + pos);
                if (pos < min2){
                    if (pos != -1){
                        min1 = i;
                        min2 = pos;
                    }
                }

            }

            //System.out.println("VALOR: " + min1);
            //System.out.println();
            if (min1 != -1){
                j = min1;
                izq = min2;
                resultado[j] = piezas1[min1];
                altura ++;
            }


        }



        //IMPRIMIR RESULTADO DE CASO
        System.out.println("Caso de prueba " + caso);

        System.out.println("Número de piezas: " + altura);
        System.out.print("Solución: ");
        for(int i = 0; i<resultado.length; i++){
            if (resultado[i] != 0){
                System.out.print(resultado[i] + " ");
            }
        }

        System.out.println('\n');


    }

    public static void main(String args[]) throws IOException {
        FileReader fr = getEntrada();
        BufferedReader br = new BufferedReader(fr);

        String n;
        int n1,n2,caso;
        int[] piezas1,piezas2,resultado;
        caso = 0;
        while (!(n = br.readLine()).equals("0 0")){

            caso++;
            //Numero de piezas de cada torre para el caso i
            n1 = Integer.parseInt(n.split(" ")[0]);
            n2 = Integer.parseInt(n.split(" ")[1]);

            //Guardar las piezas de cada torre en un vector distinto
            piezas1 = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            piezas2 = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();


            if (piezas1.length <= piezas2.length){
                resolverCaso(piezas1,piezas2,caso);
            }
            else{
                resolverCaso(piezas2,piezas1,caso);
            }


        }

        //Cerrar el fichero
        try{
            if (null != fr) {
                fr.close();
            }
        }
            catch (Exception e2){
            e2.printStackTrace();
        }

    }
}
