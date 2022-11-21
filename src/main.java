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
        System.out.println("Caso de prueba " + caso);

        int[] resultado = null;
        int altura = 0; //Altura de la solución

        int alturaIteracion,izq,pos1,pos2,distancia1,distancia2;
        int[] resultadoIteracion = null;



        for (int j = 0; j<piezas1.length; j++) {
            resultadoIteracion = new int[piezas1.length];
            alturaIteracion = 0;
            izq = -1;

            for (int i = j; i < piezas1.length; i++) {

                pos1 = primeraAparicion(piezas1[i], piezas2, izq+1);

                if (i != piezas1.length-1){
                    pos2 = primeraAparicion(piezas1[i+1], piezas2, izq+1);
                }
                else{
                    pos2 = -1;
                }

                System.out.println("POSICIONES: " + pos1 + " "  + pos2);
                if (pos1 != -1){
                    //En caso de que la pieza i y la i+1 se encuentren en el subarray de la torre 2
                    if (pos2 != - 1){
                        if (pos1 <= pos2) {
                            izq = pos1;
                            resultadoIteracion[i] = piezas1[i];
                            alturaIteracion ++;
                        }
                        else {
                            izq = pos2;
                            resultadoIteracion[i] = piezas1[i+1];
                            alturaIteracion ++;
                            i = i + 1;
                        }
                    }

                    //En caso de que la pieza i está en el subarray y la i+1 no
                    else{
                        izq = pos1;
                        resultadoIteracion[i] = piezas1[i];
                        alturaIteracion ++;
                    }
                }



                /*
                  Al final del bucle si la altura de la iteracion es superior a la de el anterior resultado almacenado,
                  se reemplazará el anterior por el nuevo
                */

            }

            System.out.println("RESULTADO ITERACION: " + Arrays.toString(resultadoIteracion));
            //System.out.println("ALTURA ITERACIÓN: " + alturaIteracion + " ,ALTURA TOTAL: " + altura + '\n');

            if (alturaIteracion > altura) {
                resultado = resultadoIteracion;
                altura = alturaIteracion;
            }
        }


        /*IMPRIMIR RESULTADO DE CASO
        System.out.println("Caso de prueba " + caso);
        if (resultado != null){
            System.out.println("Número de piezas: " + altura);
            System.out.print("Solución: ");
            for (int i = 0; i<resultado.length; i++){
                if (resultado[i] != 0){
                    System.out.print(resultado[i] + " ");
                }
            }
        }
        else {
            System.out.println("Número de piezas: " + 0);
            System.out.print("Solución: ");
        }

        System.out.println('\n');
        */

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
