package dosya_client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class DosyaClient {

  public final static int Soket_Portu = 2020;      // Server Potu.
  public final static String Server_IP_Adresi = "172.16.1.135";  // Server IP Adresi.
  public final static String Alinacak_Dosya_Dizini = "d:/deneme2.txt";  // Alinacak Dosya Dizini ve dosya adi ve uzantisi.
  public final static int DOSYA_BOYUTU = 6022386; 
                                               
  public static void main (String [] args ) throws IOException {
    
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    
    try {
      sock = new Socket(Server_IP_Adresi, Soket_Portu);
      System.out.println("Baglaniyor...");

      // Dosya Alimi
      byte [] mybytearray  = new byte [DOSYA_BOYUTU];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream(Alinacak_Dosya_Dizini);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("Dosya indirildi.");
    }
    
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
    
  }

}