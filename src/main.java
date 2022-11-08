/*
Víctor Jorge Sibaja
Pablo Gutiérrez Martínez
 */
import java.io.*;

public class main {

    public static BufferedReader getEntrada(){
        File fichero = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fichero = new File("./entrada.txt");
            fr = new FileReader(fichero);
            br = new BufferedReader(fr);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if (null != fr) {
                    fr.close();
                }
            }
            catch (Exception e2){
                e2.printStackTrace();
            }

        }
        return br;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = getEntrada();
        System.out.println(br.readLine());
        System.out.println(br.readLine());

    }
}
