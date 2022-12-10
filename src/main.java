/*
Víctor Jorge Sibaja
Pablo Gutiérrez Martínez
 */
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

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

    public static int buscarMaximo(int[] maximos, int extremoInferior){
        int maximo = 0;

        for (int i = 0; i<extremoInferior; i++){
            if (maximos[i] > maximo){
                maximo = maximos[i];
            }
        }
        return maximo;
    }

    /*
    Método que resuelve el caso para las torres que se le pase como parámetros
     */
    public static void resolverCaso(int[] piezas1, int[] piezas2, int caso){
        /*Comienza así para que la fila 0 y la columna 0 sean enteras de 1*/

        int m = piezas1.length;
        int n = piezas2.length;
        int[][] matriz= new int[n][m];
        int[] maximos = new int[n];
        int[] aux;
        int[] solucion;
        int maximo;
        int mayorMaximo=0;

        Hashtable<Integer, Integer> tabla = new Hashtable<>();

        /*
        Permitirá no tener que iterar una columna en caso de que el elemento
        de piezas 1 no esté en piezas 2
         */
        for (int i = 0; i<n; i++){
            tabla.put(piezas2[i],i);
        }



        int contador;
        for (int i = 0; i<m; i++){
            aux = maximos.clone();
            /*
            Solo en caso de que el elemento de la torre 1 esté en la torre 2
            interesará recorrer
             */
            if (tabla.containsKey(piezas1[i])){
                for (int j = 0; j<n; j++){
                    if (piezas1[i] == piezas2[j]){
                        maximo = buscarMaximo(maximos,j);
                        maximo++;
                        matriz[j][i] = maximo;
                        if (maximo > maximos[j]){
                            aux[j] = maximo;
                        }

                        if (maximo>mayorMaximo){
                            mayorMaximo=maximo;
                        }
                    }
                }
                maximos = aux;
            }

        }

        System.out.println("Caso de prueba " + caso);
        System.out.println("Número de piezas: " + mayorMaximo);


        if (mayorMaximo!= 0){
            solucion=new int[mayorMaximo];
            //Guarda la posición de la columna donde se encontro el ultimo maximo
            int auxiliar=n-1;
            for( int i=m-1; i>=0;i--){
                for( int j=auxiliar; j>=0;j-- ){
                    if ((matriz[j][i]==mayorMaximo) && mayorMaximo>0){
                        //Para evitar un OutOfBounds
                        if (mayorMaximo>0) {
                            solucion[mayorMaximo - 1] = piezas1[i];
                            mayorMaximo--;
                            auxiliar = j - 1;
                            break;
                        }
                    }
                }
            }
        }
        else {
            solucion = null;
        }


        //IMPRIMIR RESULTADO DE CASO
        System.out.print("Solución:");
        if (solucion != null){
            for (int i = 0; i<solucion.length; i++){
                System.out.print(" " + solucion[i]);
            }

        }
        else {
            System.out.print(" ");
        }
        System.out.println('\n');


    }

    public static void main(String args[]) throws IOException {
        FileReader fr = getEntrada();
        BufferedReader br = new BufferedReader(fr);

        String n;
        int caso;
        int[] piezas1,piezas2;
        caso = 0;
        while (!( n = br.readLine()).equals("0 0")){

            caso++;

            //Guardar las piezas de cada torre en un vector distinto
            piezas1 = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            piezas2 = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            //Se pasa como primer parametro el vector de piezas que sea mayor
            if (piezas2.length >= piezas1.length){
                resolverCaso(piezas2,piezas1,caso);
            }
            else{
                resolverCaso(piezas1,piezas2,caso);
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
