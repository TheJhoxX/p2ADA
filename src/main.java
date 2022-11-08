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

        //Numero de piezas de cada torre para el caso i
        String[] n = (br.readLine()).split(" ");

        System.out.println(Arrays.toString(n));
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
