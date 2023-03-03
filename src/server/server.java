package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.Ventana_Cliente;

public class server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket sv_sck;
		Socket sck;
		Ventana_Cliente v;
		int cliente=0;
		try {
			sv_sck=new ServerSocket(55);
			System.out.println("Servidor esperando...");
			while(true) {
				sck=sv_sck.accept();
				cliente++;
				Hilo h=new Hilo(sck,cliente);
				h.start();
					
			}
			
		} catch (Exception e) {e.printStackTrace();}
		

	}

}
