/*
Víctor Jorge Sibaja
Pablo Gutiérrez Martínez
 */
import java.io.*;
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

    public static void main(String args[]) throws IOException {
        FileReader fr = getEntrada();
        BufferedReader br = new BufferedReader(fr);

        String n;
        int n1,n2,caso;
        int[] piezas1,piezas2;
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

            System.out.println("Caso:" + caso);
            System.out.println(n1 + " " + n2);
            System.out.println(Arrays.toString(piezas1));
            System.out.println(Arrays.toString(piezas2));

        }

        /*Cerrar el fichero*/
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
