package JuanAraya_Taller1POO;
//Integrante: Juan Ignacio Araya Larraguibel
//RUT: 21.566.260-8
//Carrera: Ingenieria en Tecnologias de Información (ITI)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		
		//apertura del Scanner y las constantes para el maximo
		Scanner scanner = new Scanner(System.in);
		int usuariosMaximo= 10;
		int registrosMaximos= 300;
		//listas para los usuarios
		String[] usuarioNombres= new String[usuariosMaximo];
		String[] contraseñaUsuarios= new String[usuariosMaximo];
		int usuariosTotales=0;
		
		//listas para los registros
		String[] registroUsuario= new String[registrosMaximos];
		String[] registroFecha= new String[registrosMaximos];
		int[] registroHoras= new int[registrosMaximos];
		String[] registroActividad= new String[registrosMaximos];
		int registrosTotales=0;
		
		
		try {
			File archivo = new File("Usuarios.txt");
			Scanner lectura = new Scanner(archivo);
			usuariosTotales=0;
			while(lectura.hasNextLine()&&usuariosTotales<usuariosMaximo) {
				String linea= lectura.nextLine();
				String[] partes= linea.split(";");
				usuarioNombres[usuariosTotales]= partes[0];
				contraseñaUsuarios[usuariosTotales]= partes[1];
				usuariosTotales++;
				
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
