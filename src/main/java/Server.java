import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
            for(int i=0; i<2;i++){
                lista.add(new ServerThread(socket.accept()));
                
                lista.get(i).start();
            }
            socket.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
    public class ServerThread extends Thread{
        BufferedReader input;
        DataOutputStream output;
        Socket s;
        String username;
        String msg;
        
        public ServerThread(Socket s){
            this.s=s;
        }
        
        @Override
        public void run(){
            try{
            input= new BufferedReader(new InputStreamReader(s.getInputStream()));
            output= new DataOutputStream(s.getOutputStream());
            
            output.writeBytes("inserire l'username");
            username=input.readLine();
            
            for(;;){
                msg=input.readLine();
                
                if(msg.equals("stop")){
                    output.writeBytes("fine conversazione");
                    break;
                    
                }else{
                    output.writeBytes(username+": "+msg);
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
}
