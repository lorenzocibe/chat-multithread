package com.mycompany.chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket socket;
    ArrayList <ServerThread> lista= new ArrayList <>();
    
    
    public static void main(String[] args) {
        Server s= new Server();
        s.connetti();
    }
    
    public void connetti(){
        try{
            socket= new ServerSocket(1234);
            System.out.println("server avviato");
            for(int i=0; i<2;i++){
                
                Socket accept = socket.accept();
                ServerThread Sthread =new ServerThread(accept, this);
                lista.add(Sthread);
                System.out.println("client connesso");
                
                lista.get(i).start();
            }
            socket.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
    }
    
    public void invioMessaggi(String msg, String username){
        for(ServerThread serverThread: lista){
            try {
                serverThread.output.writeBytes(username +" "+ msg + "\n");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}