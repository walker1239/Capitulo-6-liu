//package grupos;

import java.io.*;
import java.net.*;

class HiloLector implements Runnable{
	static final int MAX_LON =30;
	private InetAddress grupo;
	private int puerto;
	
	public HiloLector(InetAddress grupo, int puerto) {
		this.grupo=grupo;
		this.puerto=puerto;
	}
	public void run() {
		try {
			MulticastSocket socket =new MulticastSocket(puerto);
			socket.joinGroup(grupo);
			while (true) {
				byte[] datos = new byte[MAX_LON];
				DatagramPacket paquete= new DatagramPacket(datos,datos.length,grupo,puerto);
				socket.receive(paquete);
				String s= new String (paquete.getData());
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

public class Ejemplo2EmisorReceptor{
	public static void main(String[] args) {
		InetAddress grupo = null;
		int puerto= 0;
		MulticastSocket socket=null;
		String caracteres;
		byte[] datos=null;
		
		if (args.length!=3)
			System.out.println("Se requieres 3 args");
		else {
			try {
				grupo=InetAddress.getByName(args[0]);
				puerto=Integer.parseInt(args[1]);
				caracteres=args[2];
				datos=caracteres.getBytes();
				DatagramPacket paquete= new DatagramPacket(datos, datos.length,grupo,puerto);
				Thread elHilo= new Thread(new HiloLector(grupo,puerto));
				elHilo.start();
				System.out.println("Presiones intro");
				InputStreamReader is = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is) ;
				br.readLine() ;
				socket = new MulticastSocket(puerto);
				socket.setTimeToLive(1);
				socket.send(paquete);
				socket.close();
			}
			catch(Exception se) {
				se.printStackTrace( ) ;
			}
		}
		while(true);
	}
	
	
}