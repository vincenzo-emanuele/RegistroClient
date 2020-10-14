package registro;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class RegistroClient {

    public static void main(String[] args) {

        Socket socket = null;
        String comando;
        RecordRegistro record = null;

        try{

            while(!(comando = scegliComando()).equals("Q")) {

                if(comando.equals("A")) {

                    socket = new Socket(IP_ADDRESS, CONNECTION_PORT);
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    record = inserisciDati();
                    os.writeObject(record);
                    os.flush();
                    System.out.println(is.readObject() + "\n\n");

                } else if(comando.equals("B")){

                    socket = new Socket(IP_ADDRESS, CONNECTION_PORT);
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    String nome = inserisciNome();
                    os.writeObject(new RecordRegistro(nome));
                    os.flush();
                    record = (RecordRegistro) is.readObject();

                    if(record == null){

                        System.out.println("User not found!\n\n");

                    } else {

                        System.out.println(nome + "'s address is " + record.getIndirizzo() + "\n\n");

                    }
                }

                socket.close();
            }

        } catch(Exception e){

            e.printStackTrace();

        }

    }

    public static String scegliComando(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose an operation\nA: Insert\nB: Search\nQ: Quit");
        return sc.nextLine();

    }

    public static RecordRegistro inserisciDati(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the new user:");
        String nome = sc.nextLine();
        System.out.println("Enter the address of the new user");
        String indirizzo = sc.nextLine();
        return new RecordRegistro(nome, indirizzo);

    }

    public static String inserisciNome(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the user you are looking for:");
        return sc.nextLine();

    }

    private static final String IP_ADDRESS = "localhost";
    private static final int CONNECTION_PORT = 9000;

}
