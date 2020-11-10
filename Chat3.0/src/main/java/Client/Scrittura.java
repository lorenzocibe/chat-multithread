package Client;

import java.io.*;
import java.net.*;

public class Scrittura extends Thread{
    BufferedReader inputTastiera;
    BufferedReader in;
    DataOutputStream out;
    Socket client;
    Lettura lettura;

    public Scrittura(BufferedReader inputTastiera, BufferedReader in, DataOutputStream out, Socket client, Lettura lettura) {
        this.inputTastiera = inputTastiera;
        this.in = in;
        this.out = out;
        this.client = client;
        this.lettura=lettura;
    }
    
    
    
        
        @Override
        public void run(){
            String msg;
            
            try{
                for(;;){
                    msg=inputTastiera.readLine();
                    if(msg.equals("stop")){
                        out.writeBytes(msg+ "\n");
                        lettura.chiusura();
                        inputTastiera.close();
                        in.close();
                        out.close();
                        client.close();
                        this.stop();
                    }else{
                        out.writeBytes(msg+ "\n");
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
        
    }