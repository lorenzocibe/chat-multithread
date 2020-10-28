
package newpackage;

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
            scrittura = new Scrittura();
            lettura= new Lettura();
            scrittura.start();
            lettura.start();
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public class Scrittura extends Thread{
        
        @Override
        public void run(){
            String msg;
            
            try{
                for(;;){
                    msg=inputTastiera.readLine();
                    if(msg.equals("stop")){
                        lettura.chiusura();
                        inputTastiera.close();
                        in.close();
                        out.close();
                        client.close();
                        this.stop();
                    }else{
                        out.writeBytes(msg);
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
        
    }
    
    public class Lettura extends Thread{
        
        @Override
        public void run(){
            
            try{
                for(;;){
                in.readLine();
                
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            
            
        }
        
        public void chiusura(){
            this.stop();
        }
    }
}
