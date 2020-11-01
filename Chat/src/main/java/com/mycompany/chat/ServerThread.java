package com.mycompany.chat;


import java.io.*;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class ServerThread extends Thread{
        BufferedReader input;
        DataOutputStream output;
        Socket s;
        String username;
        String msg;
        Server server;
        public ServerThread(Socket s, Server server){
            this.s=s;
            this.server=server;
        }
        
        @Override
        public void run(){
            try{
            input= new BufferedReader(new InputStreamReader(s.getInputStream()));
            output= new DataOutputStream(s.getOutputStream());
            
            output.writeBytes("inserire l'username \n");
            username=input.readLine();
            
            for(;;){
                msg=input.readLine();
                
                if(msg.equals("stop")){
                    output.writeBytes("fine conversazione \n");
                    break;
                    
                }else{
                    server.invioMessaggi(msg, username);
                }
            }
            input.close();
            output.close();
            s.close();
               
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }