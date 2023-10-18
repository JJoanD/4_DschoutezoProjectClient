package com.example4;

import java.io.*;
import java.net.*;

public class Client {

    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mySocket;
    ObjectInputStream inDalServer;
    FileOutputStream writer ;
    String filePath = "img_ricevuta.jpg";

    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione");
        try{
          //creazione socket
          mySocket = new Socket(nomeServer, portaServer); //possibile utilizzare anche 'InetAddress.getLocalHost' al posto di 'nomeServer'
          
          writer = new FileOutputStream(filePath) ;  // istanza per scrivere su un file binario
          inDalServer = new ObjectInputStream(mySocket.getInputStream());
        

        }catch(UnknownHostException e){
          System.err.println("Host sconosciuto");
        }catch(Exception e){
          System.out.println(e.getMessage());
          System.out.println("Errore durante la connessione");
          System.exit(1);
        }
      return mySocket;
    }

    public void comunica(){
        System.out.println("sto aspettando il file"); 
         try{
             byte[] buffer = new byte[1024];
             int lenghtRead;

             while((lenghtRead = inDalServer.read(buffer))>0){
                writer.write(buffer , 0 , lenghtRead);
             }
             System.out.println("file scaricato corretamente");
        }catch(Exception e){
            System.out.println("CHE PALLE");
        }
       
    }  
  
}
