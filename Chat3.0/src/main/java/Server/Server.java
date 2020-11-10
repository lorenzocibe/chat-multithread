package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket socket;
    ArrayList <ServerThread> lista= new ArrayList <>();
    BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
    
    
    public static void main(String[] args) {
        Server s= new Server();
        s.connetti();
    }
    
    public void connetti(){
        try{
            socket= new ServerSocket(1234);
            System.out.println("server avviato");
            int quantitaUtenti;
            System.out.println("numero massimo di utenti");
            quantitaUtenti=read.read();
            for(int i=0; i<quantitaUtenti; i++){
                
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
                serverThread.output.writeBytes(username +": "+ msg +"\n");
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void inviaAdUnClient(String msg, String username, String mittente){
        for(ServerThread s: lista){
            if(username.equalsIgnoreCase(s.getUsername())){
                try {
                    s.output.writeBytes(mittente+" "+msg+ " (privato)" +"\n");
                    break;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
    public void outputLista(String username){
        ArrayList <String> nomi=new ArrayList<>();
        for(ServerThread s: lista){
            if(username.equalsIgnoreCase(s.getUsername())){
                try {
                    for(ServerThread x: lista){
                        nomi.add(x.getUsername());
                    }
                    
                    for(int i=0;i<lista.size();i++){
                        s.output.writeBytes(nomi.get(i)+"\n");
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}