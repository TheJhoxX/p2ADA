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
            fichero = new File("entrada.txt");
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


    public static void resolverCaso(int[] piezas1, int[] piezas2){
        /*Comienza así para que la fila 0 y la columna 0 sean enteras de 1*/

        int m = piezas1.length;
        int n = piezas2.length;
        int[][] matriz= new int[n][m];
        int[] maximos = new int[n];
        int[] relacionados = new int [n];
        int[] aux ;

        int maximo;


        for (int i = 0; i<m; i++){
            aux = maximos.clone();
            for (int j = 0; j<n; j++){
                if (piezas1[i] == piezas2[j]){

                    maximo = buscarMaximo(maximos,j);
                    maximo++;
                    matriz[j][i] = maximo;
                    if (maximo > maximos[j]){
                        aux[j] = maximo;
                        relacionados[j] = j;
                    }
                }
            }
            maximos = aux;
        }

        for (int i = 0; i<matriz.length; i++){
            System.out.println(Arrays.toString(matriz[i]));
        }

        System.out.println(Arrays.toString(maximos));
        System.out.println(Arrays.toString(relacionados));



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
                resolverCaso(piezas2,piezas1);
            }
            else{
                resolverCaso(piezas1,piezas2);
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
