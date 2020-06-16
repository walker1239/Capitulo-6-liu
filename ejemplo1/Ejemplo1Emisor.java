//
import java.io.*;
import java.net.*;



public class Ejemplo1Emisor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress grupo;
		int puerto;
		MulticastSocket socket;

		
		if (args.length!=3)
			System.out.println("Se requieres 3 args");
		else {
			try {
				grupo=InetAddress.getByName(args[0]);
				puerto=Integer.parseInt(args[1]);
				socket = new MulticastSocket(puerto);
				socket.setTimeToLive(32);
				
				String msj=args[2];
				DatagramPacket paquete= new DatagramPacket(msj.getBytes(),msj.length(),grupo,puerto);
				socket.send(paquete);
				socket.close();
			}
			catch(Exception se) {
				se.printStackTrace( ) ;
			}
		}

	}

}
