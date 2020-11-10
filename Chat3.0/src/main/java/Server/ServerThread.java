package Server;


import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
        BufferedReader input;
        DataOutputStream output;
        Socket s;
        String username;
        String msg;
        int chiocciola;
        String Menzione;
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
            server.invioMessaggi(username+" si è connesso", "Server");
            for(;;){
                msg=input.readLine();
                chiocciola=msg.indexOf("@");
                if(msg.equals("stop")){
                    server.invioMessaggi(username+"si è disconnesso", "Server");
                    output.writeBytes("fine conversazione \n");
                    break;
                    
                }else if(chiocciola == 0){
                    
                        chiocciola=msg.indexOf(" ");
                        Menzione=msg.substring(1, chiocciola);
                        System.out.println(Menzione);
                        server.inviaAdUnClient(msg, Menzione, username);
                    
                    
                }else if(msg.equals("/lista")){
                    server.outputLista(username);
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
        
        public String getUsername(){
            return username;
        }
    }