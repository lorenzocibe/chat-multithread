package Client;

import java.io.*;
import java.net.*;

public class Client {

    Socket client;
    BufferedReader inputTastiera;
    BufferedReader in;
    DataOutputStream out;
    Scrittura scrittura;
    Lettura lettura;
    
    public static void main(String[] args) {
        
            Client c= new Client();
            c.connessione();
    }
    
    public void connessione(){
        try{
            InetAddress ip= InetAddress.getByName("localhost");
            client= new Socket(ip, 1234);
            inputTastiera = new BufferedReader(new InputStreamReader(System.in));
            out= new DataOutputStream(client.getOutputStream());
            in= new BufferedReader(new InputStreamReader(client.getInputStream()));            
            lettura= new Lettura(in);
            scrittura = new Scrittura(inputTastiera, in, out, client, lettura);
            scrittura.start();
            lettura.start();
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }   
}
