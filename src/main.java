/*
Víctor Jorge Sibaja
Pablo Gutiérrez Martínez
 */
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
        int[] relacionados = new int [n];
        int[] aux = new int[n];
        int[] solucion;


        int maximo=0;
        int maximoTotal = 0;


        int contador;
        for (int i = 0; i<m; i++){
            aux = maximos.clone();
            for (int j = 0; j<n; j++){
                if (piezas1[i] == piezas2[j]){

                    maximo = buscarMaximo(maximos,j);
                    maximo++;
                    matriz[j][i] = maximo;
                    if (maximo > maximos[j]){
                        aux[j] = maximo;
                        relacionados[j] = piezas1[i];
                    }
                    if (maximo > maximoTotal){
                        maximoTotal = maximo;
                    }
                }
            }
            maximos = aux;
        }

        System.out.println("Caso de prueba " + caso);
        System.out.println("Número de piezas: " + maximoTotal);

        if (maximoTotal != 0){
            solucion=new int[maximoTotal];
            contador=maximoTotal-1;
            int auxiliar=n-1;
            for( int i=m-1; i>=0;i--){
                for( int j=auxiliar; j>=0;j-- ){
                    if ((matriz[j][i]==maximoTotal) && contador > -1){
                        solucion[contador]=piezas1[i];
                        contador--;
                        maximoTotal--;
                        auxiliar=j-1;

                        break;

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
