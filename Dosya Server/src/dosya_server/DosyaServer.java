package dosya_server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DosyaServer {

  public final static int Soket_Portu = 2020;  //Server Portu:2020.
  public final static String Gonderilecek_Dosya_Dizini = "d:/deneme1.txt";  // Gonderilecek dosya konumu.

  public static void main (String [] args ) throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    
    try {
      servsock = new ServerSocket(Soket_Portu);
      while (true) {
        System.out.println("Baglanti icin bekleniyor...");
        try {
          sock = servsock.accept();
          System.out.println("Baglanti saglandi.");
          
          //Dosya Gonderim 
          File myFile = new File (Gonderilecek_Dosya_Dizini);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Gonderiliyor.");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Tamamlandi.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }
}