package Client;


import java.io.*;

public class Lettura extends Thread{
        BufferedReader in;
        
        
        
        public Lettura(BufferedReader in){
            this.in=in;
        }
        
        @Override
        public void run(){
            String s;
            try{
                for(;;){
                s=in.readLine();
                    System.out.println(s);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            
            
        }
        
        public void chiusura(){
            this.stop();
        }
    }
