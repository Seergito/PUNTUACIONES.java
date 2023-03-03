package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import server.Puntuacion;

public class Hilo {

	Socket sck;
	ObjectInputStream input;
	ObjectOutputStream output;
	Ventana_Cliente v;
	ArrayList<Puntuacion> lista;
	
	public Hilo(Socket sck,Ventana_Cliente v) {
		this.sck=sck;
		this.v=v;
		
	}
	
	public void run() {
	
		while(true) {
		
			try {
				
				lista=(ArrayList<Puntuacion>) input.readObject();
				v.asignarLista(lista);
				v.rellenar_datos_tabla(lista);
				
			} catch (Exception e) {e.printStackTrace();} 
			
		}
		
		
	}
	
	
}
