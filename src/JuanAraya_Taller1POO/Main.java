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
		
		//lectura del archivo Usuarios.txt
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
			System.out.println("Hay "+usuariosTotales +" usuarios");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//lectura del archivo Registros.txt
		try {
			File archivo2 = new File("Registros.txt");
			Scanner lectura2= new Scanner(archivo2);
			registrosTotales=0;
			while(lectura2.hasNextLine()&&registrosTotales<registrosMaximos) {
				String linea= lectura2.nextLine();
				String[] partes= linea.split(";");
				registroUsuario[registrosTotales]= partes[0];
				registroFecha[registrosTotales]= partes[1];
				registroHoras[registrosTotales]= Integer.parseInt(partes[2]);
				registroActividad[registrosTotales]= partes[3];
				registrosTotales++;
				
			}
			lectura2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//empezando con los Menus
		int opcion = 0;
		do {
			System.out.println("Menu Inicial:");
			System.out.println("Seleccione una opción");
			System.out.println("1- Menu usuarios");
			System.out.println("2- Menu analisis");
			System.out.println("3- Salir");
			
			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Opcion Invalida, Ingrese un numero válido");
			}
		}while(opcion!=3);
		
		scanner.close();
		
		
	}

}
