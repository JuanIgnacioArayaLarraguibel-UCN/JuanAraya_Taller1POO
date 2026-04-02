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
		int usuariosMaximo= 3;
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
                        }else if(opcionUsuario==2) {
                        	//rehaciendo la modificacion de actividad
                        	int[] indiceUsuario= new int[registrosMaximos];
                        	int contadorIndice=0;
                        	for(int i=0;i<registrosTotales;i++) {
                        		if(registroUsuario[i].equals(usuarioActual)) {
                        			indiceUsuario[contadorIndice]= i;
                        			contadorIndice++;
                        			
                        		}
                        	}
                        	if(contadorIndice>0) {
                        		System.out.println("Seleccione la actividad a modificar:");
                        		System.out.println("0)Volver");
                        		for(int i=0;i<contadorIndice;i++) {
                        			int idx = indiceUsuario[i];
                        			System.out.println((i+1)+") "+registroUsuario[idx]+";"+registroFecha[idx]+";"+registroHoras[idx]+";"+registroActividad[idx]);
                        		}
                        		System.out.print("Seleccionar: ");
                        		int seleccion=0;
                        		try {
                        			seleccion = Integer.parseInt(scanner.nextLine());
                        			if(seleccion ==0) {
                        				
                        			}else if(seleccion >= 1&& seleccion<= contadorIndice) {
                        				int indiceReal= indiceUsuario[seleccion-1];
                        				
                        				System.out.println("Seleccionar formato a modificar:");
                        				System.out.println("0) Volver");
                        				System.out.println("1) Fecha");
                        				System.out.println("2) Horas");
                        				System.out.println("3) Tipo actividad");
                        				System.out.print("Elija: ");
                        				
                        				int opcionModificar=0;
                        				try {
                        					opcionModificar= Integer.parseInt(scanner.nextLine());
                        					
                        					if(opcionModificar==1) {
                        						System.out.print("Ingrese fecha en el formato DD/MM/AA: ");
                        						registroFecha[indiceReal]= scanner.nextLine();
                        						System.out.println("Actividad modificada");
                        						
                        						try {
                        							BufferedWriter writer= new BufferedWriter(new FileWriter("Registros.txt"));
                        							for(int i=0;i<registrosTotales;i++) {
                        								writer.write(registroUsuario[i]+";"+registroFecha[i]+";"+registroHoras[i]+";"+registroActividad[i]);
                        								writer.newLine();
                        							}
                        							writer.close();
                        							
                        						}catch(IOException e) {
                        							System.out.println("Error");
                        						}
                        					}else if(opcionModificar==2) {
                        						System.out.print("Ingrese horas: ");
                        						try {
                        							registroHoras[indiceReal]=Integer.parseInt(scanner.nextLine());
                        							System.out.println("Actividad modificada");
                        							
                        							try {
                            							BufferedWriter writer= new BufferedWriter(new FileWriter("Registros.txt"));
                            							for(int i=0;i<registrosTotales;i++) {
                            								writer.write(registroUsuario[i]+";"+registroFecha[i]+";"+registroHoras[i]+";"+registroActividad[i]);
                            								writer.newLine();
                            							}
                            							writer.close();
                            							
                            						}catch(IOException e) {
                            							System.out.println("Error");
                            						}
                        						}catch(NumberFormatException e) {
                        							System.out.println("Invalido");
                        						}
                        					}else if(opcionModificar==3) {
                        						System.out.print("Ingrese nueva actividad: ");
                        						registroActividad[indiceReal]=scanner.nextLine();
                        						System.out.println("Actividad modificada");
                        						
                        						try {
                        							BufferedWriter writer= new BufferedWriter(new FileWriter("Registros.txt"));
                        							for(int i=0;i<registrosTotales;i++) {
                        								writer.write(registroUsuario[i]+";"+registroFecha[i]+";"+registroHoras[i]+";"+registroActividad[i]);
                        								writer.newLine();
                        							}
                        							writer.close();
                        							
                        						}catch(IOException e) {
                        							System.out.println("Error");
                        						}
                        					}else if(opcionModificar==0) {
                        						
                        					}else {
                        						System.out.println("Ingrese una opcion valida");
                        					}
                        					
                        				}catch(NumberFormatException e) {
                        					System.out.println("Invalido");
                        				}
                        			}else {
                        				System.out.println("Invalido");
                        			}
                        		}catch(NumberFormatException e) {
                        			System.out.println("Numero invalido");
                        		}
                        	} else {
                        		System.out.println("No hay actividades para modificar");
                        	}
                        } else if(opcionUsuario==3) {
                        	int[] indiceUsuario = new int[registrosMaximos];
                        	int contadorIndice=0;
                        	for(int i=0;i<registrosTotales;i++) {
                        		if(registroUsuario[i].equals(usuarioActual)) {
                        			indiceUsuario[contadorIndice]=1;
                        			contadorIndice++;
                        		}
                        	}
                        	if(contadorIndice>0) {
                        		System.out.println("Que actividad desea eliminar?");
                        		System.out.println("0) Volver");
                        		for(int i =0;i<contadorIndice;i++) {
                        			int idx = indiceUsuario[i];
                        			System.out.println((i+1)+") "+registroUsuario[idx]+";"+registroFecha[idx]+";"+registroHoras[idx]+";"+registroActividad[idx]);
                        		}
                        		System.out.print("Elija:");
                        		int seleccion;
                        		try {
                        			seleccion = Integer.parseInt(scanner.nextLine());
                        			if(seleccion==0) {
                        				
                        			}else if(seleccion>=1&&seleccion<=contadorIndice) {
                        				int indiceReal = indiceUsuario[seleccion-1];
                        				for(int i=indiceReal;i<registrosTotales-1;i++) {
                        					registroUsuario[i]=registroUsuario[i+1];
                        					registroFecha[i]=registroFecha[i+1];
                        					registroHoras[i]=registroHoras[i+1];
                        					registroActividad[i]=registroActividad[i+1];
                        				}
                        				registrosTotales--;
                        				
                        				try {
                        					BufferedWriter writer= new  BufferedWriter(new FileWriter("Registros.txt"));
                        					for(int i=0;i<registrosTotales;i++) {
                        						writer.write(registroUsuario[i]+";"+registroFecha[i]+";"+registroHoras[i]+";"+registroActividad[i]);
                        						writer.newLine();
                        					}
                        					writer.close();
                        					System.out.println("Actividad eliminada");
                        				} catch(IOException e) {
                        					System.out.println("Error");
                        				}
                        			}else {
                        				System.out.println("Seleccion invalida");
                        			}
                        			
                        		}catch(NumberFormatException e) {
                        			System.out.println("Numero invalido");
                        		}
                        	}else {
                        		System.out.println("No hay actividades pa eliminar");
                        	}
                        }else if(opcionUsuario==4) {
                        	//el cambio de contraseña
                        	System.out.print("Ingresar nueva contraseña: ");
                        	String nuevaContraseña= scanner.nextLine();
                        	
                        	for(int i=0;i<usuariosTotales;i++) {
                        		if(usuarioNombres[i].equals(usuarioActual)) {
                        			contraseñaUsuarios[i]=nuevaContraseña;
                        			break;
                        		}
                        	}
                        	try {
                        		BufferedWriter writer= new BufferedWriter(new FileWriter("Usuarios.txt"));
                        		for(int i =0;i<usuariosTotales;i++) {
                        			writer.write(usuarioNombres[i]+";"+contraseñaUsuarios[i]);
                        			writer.newLine();
                        		}
                        		writer.close();
                        		System.out.println("Contraseña cambiada");
                        		
                        	}catch(IOException e) {
                        		System.out.println("Error");
                        	}
                        }else if(opcionUsuario==5) {
                        	System.out.println("Saliendo del menu usuarios");
                        }else {
                        	System.out.println("opcion invalida");
                        }
                        
					}while(opcionUsuario==5);
				} else {
					System.out.println("Usuario y/o contraseña incorrecto/s :c");
				}
			}else if(opcion==2) {
				//aca esta el menu de analisis
				int opcionAnalisis = 0;
				System.out.println("Menu Analisis");
				System.out.println("1) Actividad mas realizada global");
				System.out.println("2) Actividad mas realizada por usuario");
				System.out.println("3) Usuario con mayor procastinacion");
				System.out.println("4) Ver todas las actividades");
				System.out.println("5) Salir");
				System.out.print("Seleccione: ");
				
				try {
					opcionAnalisis= Integer.parseInt(scanner.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error no valido");
					opcionAnalisis=0;
				}
				
				if(opcionAnalisis==1) {
					//la actividad mas realizada en global
					if(registrosTotales>0) {
						String[] actividades = new String[registrosMaximos];
						int[] conteos= new int[registrosMaximos];
						int totalActividadesUnicas=0;
						
						for(int i=0;i<registrosTotales;i++) {
							String actividadActual=registroActividad[i];
							boolean encontrada=false;
							for(int j=0;j<totalActividadesUnicas;j++) {
								if(actividades[j].equals(actividadActual)) {
									conteos[j]+=registroHoras[i];
									encontrada=true;
									break;
								}
							}
							if(!encontrada) {
								actividades[totalActividadesUnicas]=actividadActual;
								conteos[totalActividadesUnicas]=registroHoras[i];
								totalActividadesUnicas++;
							}
						}
						int horasMaximas=-1;
						String actividadMaxima="";
						for(int i=0;i<totalActividadesUnicas;i++) {
							if(conteos[i]>horasMaximas) {
								horasMaximas=conteos[i];
								actividadMaxima=actividades[i];
							}
						}
						System.out.println("La actividad mas realizada en global es:");
						System.out.println(actividadMaxima+" en "+horasMaximas+" horas");
					}else {
						System.out.println("no hay actividades para evaluar");
					}
					
				}else if(opcionAnalisis==2) {
					//actividad por usuario
					if(registrosTotales>0) {
						System.out.println("Actividades realizadas por usuario: ");
						for(int i=0;i<usuariosTotales;i++) {
							String usuario = usuarioNombres[i];
							String[] actividades = new String[registrosMaximos];
							int[] conteos= new int[registrosMaximos];
							int totalActividadesUnicas=0;
							
							for(int j=0;j<registrosTotales;j++) {
								if(registroUsuario[j].equals(usuario)) {
									String actividadActual= registroActividad[j];
									boolean encontrada = false;
									
									for(int z=0;z<totalActividadesUnicas;z++) {
										if(actividades[z].equals(actividadActual)) {
											conteos[z]+=registroHoras[z];
											encontrada=true;
											break;
										}
									}
									if(!encontrada) {
										actividades[totalActividadesUnicas]=actividadActual;
										conteos[totalActividadesUnicas]=registroHoras[j];
										totalActividadesUnicas++;
									}
								}
							}
							
							if(totalActividadesUnicas>0) {
								int horasMaximas= -1;
								String actividadMaxima="";
								for(int j=0;j<totalActividadesUnicas;j++) {
									if(conteos[j]>horasMaximas) {
										horasMaximas=conteos[j];
										actividadMaxima=actividades[j];
									}
								}
								System.out.println(usuario+" >>> "+actividadMaxima+" con "+horasMaximas+" horas");
							}else {
								System.out.println("no hay actividades");
							}
							
						}
					}
				}
				
			}
		}while(opcion!=3);
		
		scanner.close();
		
		
	}

}
