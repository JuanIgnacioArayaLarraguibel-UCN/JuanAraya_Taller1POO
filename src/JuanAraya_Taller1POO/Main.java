package JuanAraya_Taller1POO;
//Integrante: Juan Ignacio Araya Larraguibel
//RUT: 21.566.260-8
//Carrera: Ingenieria en Tecnologias de Información (ITI)

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		
		//apertura del Scanner y las constantes para el maximo
		Scanner scanner = new Scanner(System.in);
		int usuariosMaximo= 10;
		int registrosMaximos= 300;
		String usuarioActual="";
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
			// por si no ingresan un numero
			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Opcion Invalida, Ingrese un numero válido");
			}
			
			if(opcion ==1) {
				System.out.print("Usuario: ");
				String usuario = scanner.nextLine();
				System.out.print("Contraseña: ");
				String contraseña = scanner.nextLine();
				boolean validar= false;
				
				for(int i=0;i<usuariosTotales;i++) {
					if(usuarioNombres[i].equals(usuario)&&contraseñaUsuarios[i].equals(contraseña)) {
						validar = true;
						usuarioActual= usuario;
						break;
					}
				}
				if(validar) {
					System.out.println("Bienevenid@ "+usuarioActual);
					int opcionUsuario = 0;
					do {
						System.out.println("Menú Usuarios");
                        System.out.println("1) Registrar actividad");
                        System.out.println("2) Modificar actividad");
                        System.out.println("3) Eliminar actividad");
                        System.out.println("4) Cambiar contraseña");
                        System.out.println("5) Salir");
                        System.out.print("Selecciones una opción: ");
                        
                        try {
                            opcionUsuario = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("No valido");
                            opcionUsuario = 0;
                        }
                        
                        if(opcionUsuario==1) {
                        	if(registrosTotales<registrosMaximos) {
                        		System.out.print("Ingrese fecha en formato DD/MM/AAAA: ");
                        		String fecha= scanner.nextLine();
                        		System.out.print("Ingrese hora: ");
                        		int horas;
                        		try {
                        			horas = Integer.parseInt(scanner.nextLine());
                        			System.out.print("Ingresar actividad: ");
                        			String actividad = scanner.nextLine();
                        			registroUsuario[registrosTotales]= usuarioActual;
                        			registroFecha[registrosTotales]= fecha;
                        			registroHoras[registrosTotales]=horas;
                        			registroActividad[registrosTotales]=actividad;
                        			registrosTotales++;
                        			
                        			try {
                        				BufferedWriter writer = new BufferedWriter(new FileWriter("Registros.txt"));
                        				for(int i = 0; i<registrosTotales;i++) {
                        					writer.write(registroUsuario[i]+ ";"+registroFecha[i]+";"+registroHoras[i]+";"+registroActividad[i]);
                        					writer.newLine();
                        				}
                        				writer.close();
                        				System.out.println("Todo registrado");
                        			}catch(IOException e) {
                        				System.out.println("Error");
                        			}
                        		}catch (NumberFormatException e) {
                        			System.out.println("Hora invalida");
                        		}
                        	}else {
                        		System.out.println("Limite de "+registrosMaximos+" alcanzado");
                        	}
                        }
                        
					}while(opcionUsuario==5);
				} else {
					System.out.println("Usuario y/o contraseña incorrecto/s :c");
				}
			}
		}while(opcion!=3);
		
		scanner.close();
		
		
	}

}
