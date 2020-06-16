//package parcial2;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Ejemplo1Receptor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress grupo;
		int puerto;
		MulticastSocket socket;

		
		if (args.length!=2)
			System.out.println("Se requieres 3 args");
		else {
			try {
				grupo=InetAddress.getByName(args[0]);
				puerto=Integer.parseInt(args[1]);
				socket = new MulticastSocket(puerto);
				socket.joinGroup(grupo);
				byte[] almacen= new byte[100];
				DatagramPacket paquete= new DatagramPacket(almacen,almacen.length);
				socket.receive(paquete);
				System.out.println(new String(almacen));
				socket.close();
			}
			catch(Exception se) {
				se.printStackTrace( ) ;
			}
		}

	}

}
