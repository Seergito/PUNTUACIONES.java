package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import cliente.Ventana_Cliente;

public class Hilo extends Thread {

	Socket sck;
	int cliente;
	ArrayList<Puntuacion> lista;
	Compartida compartida; //PARA ENVIAR A TODOS
	
		public Hilo(Socket sck,int cliente) {
			this.sck=sck;
			this.compartida=compartida;
		}

		@Override
		public void run() {
			
			try {
				ObjectInputStream input =new ObjectInputStream(sck.getInputStream());
				ObjectOutputStream output=new ObjectOutputStream(sck.getOutputStream());
				compartida.añadirSalida(output);
				
				
				while(true) {
					Puntuacion p=(Puntuacion) input.readObject();
					if(p.getNombre().equals("*")) {
						compartida.quitarSalida(output);
						break;
					}else {
						compartida.añadirPuntuacion(p);
						compartida.EnviarPuntuaciones();
					}
					
					lista.add(p);
					
					Comparador c=new Comparador();
					Collections.sort(lista,c);
					output.writeObject(lista);
				}
				
				
			} catch (Exception e) {e.printStackTrace();}
				
		}

}

