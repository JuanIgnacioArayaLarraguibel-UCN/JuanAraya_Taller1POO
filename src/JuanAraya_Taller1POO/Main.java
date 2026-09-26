package JuanAraya_Taller1POO;
//Juan Ignacio Araya Larraguibel - 21.566.260-8 - ITI
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;


public class Main {
	
	//Esto es la capacidad maxima para los vectores
	public static int alumnosMaximos =100;
	public static int solicitudesMaximas =100;
	public static int admitidosMaximos =100;
	public static int rechazadosMaximos =100;
	
	//vectores :P
	public static String[] nombreAlumnos= new String[alumnosMaximos];
	public static String[] apellidoAlumnos= new String[alumnosMaximos];
	public static String[] rutAlumnos= new String[alumnosMaximos];
	public static String[] paraleloAlumnos= new String[alumnosMaximos];
	public static int alumnosTotales=0;
	public static String[] nombreSolicitudes= new String[solicitudesMaximas];
	public static String[] apellidoSolicitudes= new String[solicitudesMaximas];
	public static int solicitudesTotales=0;
	public static String[] nombresAdmitidos= new String[admitidosMaximos];
	public static String[] apellidosAdmitidos= new String[admitidosMaximos];
	public static String[] rutsAdmitidos= new String[admitidosMaximos];
	public static String[] paralelosAdmitidos= new String[admitidosMaximos];
	public static int admitidosTotales=0;
	public static String[] nombresRechazados= new String[rechazadosMaximos];
	public static String[] apellidosRechazados= new String[rechazadosMaximos];
	public static String[] rutsRechazados= new String[rechazadosMaximos];
	public static int rechazadosTotales=0;
	
	//el control para los reportes de los Paralelos
	public static int controlC1=0;
	public static int controlC2=0;
	public static int controlRechazados=0;
	
	//booleano para notificar si se cargaron los archivos o no para no tener Error
	public static boolean cargandoArchivos= false;
	

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int opcion=0;
		
		do {
			System.out.println("Control de Grupo POO");
			System.out.println("Ingrese una opción=");
			System.out.println("1) Cargar Archivos");
			System.out.println("2) Procesar Solicitudes");
			System.out.println("3) Inscripcion Manual");
			System.out.println("4) Administración en el curso");
			System.out.println("5) Generar Reporte");
			System.out.println("6) Análisis estadistico");
			System.out.println("7) Salir");
			
			try {
				opcion = scanner.nextInt();
				scanner.nextLine();
				switch(opcion) {
				case 1:
					cargarArchivos(scanner);
					break;
				case 2:
					procesarSolicitudes();
					break;
				case 3:
					inscripcionManual(scanner);
					break;
				case 4:
					administrarCurso(scanner);
					break;
				case 5:
					generarReporte(scanner);
					break;
				case 6: 
					analisisEstadistico();
					break;
				case 7:
					System.out.println("Saliendo");
					break;
				default:
					System.out.println("Ingresar opción entre 1 a 7");
				}
			}catch(Exception e){
				System.out.println("Ingresar opción válida");
				scanner.nextLine(); //esto es para romper la cadena y no se ejecute infinitamente si se coloca una letra
			}
		}while(opcion!=7);
		scanner.close();
		
		

	}


	private static void inscripcionManual(Scanner scanner) {
		if(!cargandoArchivos) {
			System.out.println("Archivos no cargados");
			return;
		}
		
		System.out.println("Inscripcion Manual - Elegir opción");
		System.out.println("1) Por Nombre completo");
		System.out.println("2) Por Rut");
		System.out.print("Elija: ");
		
		int opcion= 0;
		
		try {
			opcion = scanner.nextInt();
			scanner.nextLine();
		}catch(Exception e){
			System.out.println("Ingresar opcion valida");
			scanner.nextLine();	
			return;
		}
		
		if(opcion==1) {
			System.out.print("Ingresar nombre: ");
			String nombre = scanner.nextLine();
			System.out.print("Ingresar apellido: ");
			String apellido = scanner.nextLine();
			inscribirPorNombre(nombre, apellido);
		}else if(opcion == 2) {
			System.out.print("Ingresar rut: ");
			String rut = scanner.nextLine();
			inscribirPorRut(rut);
		}else {
			System.out.println("Opcion invalida");
		}
		
		
	}


	public static void inscribirPorRut(String rut) {
    if (rut.isEmpty()) {
        System.out.println("El RUT no puede estar vacío.");
        return;
    }

    boolean encontrado = false;
    for (int i = 0; i < alumnosTotales; i++) {
        if (rutAlumnos[i].equalsIgnoreCase(rut)) {
            encontrado = true;
            
            // Verifica si está duplicado o no
            boolean yaAdmitido = false;
            for (int j = 0; j < admitidosTotales; j++) {
                if (rutsAdmitidos[j].equalsIgnoreCase(rut)) {
                    yaAdmitido = true;
                    break;
                }
            }
            
            if (!yaAdmitido && admitidosTotales < admitidosMaximos) {
                nombresAdmitidos[admitidosTotales] = nombreAlumnos[i];
                apellidosAdmitidos[admitidosTotales] = apellidoAlumnos[i];
                rutsAdmitidos[admitidosTotales] = rutAlumnos[i];
                paralelosAdmitidos[admitidosTotales] = paraleloAlumnos[i];
                admitidosTotales++;
                System.out.println("[OK] " + nombreAlumnos[i] + " " + apellidoAlumnos[i] + " admitido en " + paraleloAlumnos[i]);
            } else if (yaAdmitido) {
                System.out.println("El alumno ya está en el grupo.");
            } else {
                System.out.println("No hay espacio para más admitidos.");
            }
            break;
        }
    }
    
    if (!encontrado) {
        // Registrar en rechazados
        if (rechazadosTotales < rechazadosMaximos) {
            nombresRechazados[rechazadosTotales] = "";
            apellidosRechazados[rechazadosTotales] = "";
            rutsRechazados[rechazadosTotales] = rut;
            rechazadosTotales++;
            System.out.println("El RUT " + rut + " no pertenece a ningún paralelo del curso.");
            System.out.println("No tenemos su nombre, por lo que se registrará solo el RUT en los rechazados.");
        } else {
            System.out.println("No hay espacio para más rechazados.");
        }
    }
}


	private static void inscribirPorNombre(String nombre, String apellido) {
		if(nombre.isEmpty()||apellido.isEmpty()) {
			System.out.println("Nombre y apellido están vacios");
			return;
		}
		boolean encontrado = false;
		for(int i = 0;i<alumnosTotales;i++) {
			if(nombreAlumnos[i].equalsIgnoreCase(nombre)&&apellidoAlumnos[i].equalsIgnoreCase(apellido)) {
				encontrado=true;
				
				boolean admitido = false;
				for(int j=0;j<admitidosTotales;j++) {
					if(nombresAdmitidos[j].equalsIgnoreCase(nombre)&&apellidosAdmitidos[j].equalsIgnoreCase(apellido)) {
						admitido = true;
						break;
					}
				}
				
				if(!admitido&&admitidosTotales<admitidosMaximos) {
					nombresAdmitidos[admitidosTotales]=nombreAlumnos[i];
					apellidosAdmitidos[admitidosTotales]=apellidoAlumnos[i];
					rutsAdmitidos[admitidosTotales]=rutAlumnos[i];
					paralelosAdmitidos[admitidosTotales]=paraleloAlumnos[i];
					admitidosTotales++;
					System.out.println("Admitido: "+nombre+" "+apellido+" = "+" admitido en "+paraleloAlumnos[i]);
				}else if(admitido) {
					System.out.println("ya está el alumno");
				}else {
					System.out.println("no hay espacio");
				}
				break;
			}
		}
		if(!encontrado) {
			if(rechazadosTotales<rechazadosMaximos) {
				nombresRechazados[rechazadosTotales]=nombre;
				apellidosRechazados[rechazadosTotales]=apellido;
				rutsRechazados[rechazadosTotales]= "";
				rechazadosTotales++;
				System.out.println("Rechazado: "+nombre+" "+apellido+ " = No pertence a ningun paralelo");
				
			}else {
				System.out.println("no hay espacio");
			}
		}
		
	}


	private static void analisisEstadistico() {
		if(!cargandoArchivos) {
			System.out.println("Archivos no están cargados :'v");
			return;
		}
		
		System.out.println("Analisis estadistico=");
		int intentosTotales= admitidosTotales +rechazadosTotales;
		if(intentosTotales==0) {
			System.out.println("No hay intentos");
			return;
		}
		
		double porcentajeRechazo= (double)rechazadosTotales/intentosTotales*100;
		double porcentajeRechazadoRedondeo= Math.round(porcentajeRechazo*10)/10.0;//el round es para redondear super facil
		System.out.println("Intentos de ingreso totales: "+intentosTotales);
		System.out.println("Rechazados: "+rechazadosTotales+" "+porcentajeRechazadoRedondeo+"%");
		//para ver los paralelos
		int c1= 0;
		int c2= 0;
		for(int i=0;i<admitidosTotales;i++) {
			if(paralelosAdmitidos[i].equals("C1")) {
				c1++;
			}else {
				c2++;
			}
		}
		System.out.println("Admitidos por paralelo= C1: "+c1+" ; C2: "+c2);
		if(admitidosTotales>0) {
			double porcentajeC1= (double)c1/admitidosTotales*100;
	        double porcentajeC2= (double)c2/admitidosTotales*100;
	        double porcentajeC1Redondeado= Math.round(porcentajeC1*10)/10.0;
	        double porcentajeC2Redondeado= Math.round(porcentajeC2*10)/10.0;
	        System.out.println("Porcentaje C1: " + porcentajeC1Redondeado + "% ; C2: " + porcentajeC2Redondeado + "%");
		}
		
		double admisionTasa= (double) admitidosTotales/intentosTotales*100;
		double admisionTasaRedondeada= Math.round(admisionTasa*10)/10.0;
		System.out.println("Tasa de admision: "+admisionTasaRedondeada+"%");
		
		int anonimo=0;
		for(int i=0;i<rechazadosTotales;i++) {
			if(nombresRechazados[i].isEmpty()&&apellidosRechazados[i].isEmpty()) {
				anonimo++;
			}
		}
		System.out.println("Rechazados sin nombre solo rut: "+anonimo);
		
	}


	private static void generarReporte(Scanner scanner) {

		if(!cargandoArchivos) {
			System.out.println("Debe cargar los archivos primero");
			return;
		}
		System.out.println("Generar Reporte=");
		System.out.println("1) Reporte C1");
		System.out.println("2) Reporte C2");
		System.out.println("3) Reporte de Rechazados");
		System.out.print("Ingrese opción: ");
		
		int opcion = 0;
		try {
			opcion= scanner.nextInt();	
			scanner.nextLine();
		}catch(Exception e) {
			System.out.println("Ingresar opción válida");
			return;
		}
		if(opcion ==1) {
			controlC1++;
			generarReporteParalelo("C1",controlC1);
		} else if (opcion == 2) {
	        controlC2++;
	        generarReporteParalelo("C2", controlC2);
	    } else if (opcion == 3) {
	        controlRechazados++;
	        generarReporteRechazados(controlRechazados);
	    } else {
	        System.out.println("Opción inválida.");
	    }
		//Ya para esto pedí ayuda a la IA para ir con el BufferedWriter
		
	}
	
	public static void generarReporteParalelo(String paralelo, int version) {
	    String nombreArchivo = "Reporte" + paralelo + "-V" + version + ".txt";
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
	        bw.write("=== Miembros del grupo - Paralelo " + paralelo + " ===");
	        bw.newLine();
	        boolean hayMiembros = false;
	        for (int i = 0; i < admitidosTotales; i++) {
	            if (paralelosAdmitidos[i].equalsIgnoreCase(paralelo)) {
	                bw.write(nombresAdmitidos[i] + " " + apellidosAdmitidos[i] + " - " + rutsAdmitidos[i]);
	                bw.newLine();
	                hayMiembros = true;
	            }
	        }
	        if (!hayMiembros) {
	            bw.write("(No hay miembros en este paralelo)");
	            bw.newLine();
	        }
	        System.out.println("Reporte generado: " + nombreArchivo);
	    } catch (IOException e) {
	        System.out.println("Error al generar reporte: " + e.getMessage());
	    }
	}

	public static void generarReporteRechazados(int version) {
	    String nombreArchivo = "Rechazados-V" + version + ".txt";
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
	        bw.write("Solicitudes rechazadas");
	        bw.newLine();
	        for (int i = 0; i < rechazadosTotales; i++) {
	            if (nombresRechazados[i].isEmpty() && apellidosRechazados[i].isEmpty()) {
	                bw.write("Sin nombre registrado, RUT: " + rutsRechazados[i]);
	            } else {
	                bw.write(nombresRechazados[i] + " " + apellidosRechazados[i] + " - No pertenece a ningún paralelo del curso");
	            }
	            bw.newLine();
	        }
	        System.out.println("Reporte generado: " + nombreArchivo);
	    } catch (IOException e) {
	        System.out.println("Error al generar reporte: " + e.getMessage());
	    }
	}


	private static void procesarSolicitudes() {
		if(!cargandoArchivos) {
			System.out.println("Debe cargar los archivos primero");
			return; 
		}
		System.out.println("Procesando solicitudes...");
		admitidosTotales=0;
		rechazadosTotales=0;
		
		for(int i=0; i<solicitudesTotales;i++ ) {
			String nombre = nombreSolicitudes[i];
			String apellido = apellidoSolicitudes[i];
			boolean encontrado= false;
			int indiceAlumno= -1;
			for(int j=0; j< alumnosTotales; j++) {
				if(nombreAlumnos[j].equalsIgnoreCase(nombre)&&apellidoAlumnos[j].equalsIgnoreCase(apellido)) {
					encontrado= true;
					indiceAlumno=j;
					break;
				}
			}
			if(encontrado) {
				//al ser True pasa el if
				boolean yaAdmitido=false; //esto para evitar que salgan Duplicados
				for(int a=0;a<admitidosTotales;a++) {
					if(nombresAdmitidos[a].equalsIgnoreCase(nombre)&&apellidosAdmitidos[a].equalsIgnoreCase(apellido)) {
						yaAdmitido= true;
						break;
					}
				}
				if(!yaAdmitido&&admitidosTotales<admitidosMaximos) {
					nombresAdmitidos[admitidosTotales]= nombreAlumnos[indiceAlumno];
					apellidosAdmitidos[admitidosTotales]= apellidoAlumnos[indiceAlumno];
					rutsAdmitidos[admitidosTotales]= rutAlumnos[indiceAlumno];
					paralelosAdmitidos[admitidosTotales]= paraleloAlumnos[indiceAlumno];
					admitidosTotales++;
					System.out.println("ADMITIDO: "+nombre+" "+apellido+" en paralelo "+paraleloAlumnos[indiceAlumno]);
					
				}else if(yaAdmitido) {
					System.out.println("DUPLICADO: "+nombre+" "+apellido+" está ya admitido");
				}else {
					System.out.println("ERROR no hay espacio");
				}
			}else {
				//parte de rechazados
				if(rechazadosTotales<rechazadosMaximos) {
					nombresRechazados[rechazadosTotales]=nombre;
					apellidosRechazados[rechazadosTotales]=apellido;
					rutsRechazados[rechazadosTotales]="";
					rechazadosTotales++;
					System.out.println("RECHAZADO: "+nombre+" "+apellido+" no es parte de ningun paralelo");
				}
			}
		}
		System.out.println("Resumen: "+admitidosTotales+" admitidos - "+ rechazadosTotales+" rechazados");
		
	}


	private static void cargarArchivos(Scanner scanner) {
		System.out.println("Cargando Archivos...");
		cargarAlumnos();
		cargarSolicitudes();
		if(alumnosTotales > 0||solicitudesTotales>0) {
			cargandoArchivos= true;
			System.out.println("Archivos cargados exitosamente YEI");
			System.out.println("-"+alumnosTotales+" alumnos en la lista");
			System.out.println("-"+solicitudesTotales+" solicitudes de ingreso");
		}else {
			System.out.println("No se cargaron los archivos UnU");
		}
		
		
		
	}


	private static void cargarAlumnos() {
		try (Scanner scannerArchivo = new Scanner(new File("Alumnos.txt"))){
			alumnosTotales=0;
			while(scannerArchivo.hasNextLine() && alumnosTotales<alumnosMaximos) {
				String linea= scannerArchivo.nextLine();
				String[] partes= linea.split(";");
				nombreAlumnos[alumnosTotales]= partes[0];
				apellidoAlumnos[alumnosTotales]= partes[1];
				rutAlumnos[alumnosTotales]= partes[2];
				paraleloAlumnos[alumnosTotales]= partes[3].toUpperCase(); //esto pa evitar problemas con mayuscula y minuscula
				alumnosTotales++;
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("txt de Alumnos.txt no encontrado :/");
		}
		
	}
	
	private static void cargarSolicitudes() {
		try (Scanner scannerArchivo = new Scanner(new File("Solicitudes.txt"))){
			solicitudesTotales=0;
			while(scannerArchivo.hasNextLine() && solicitudesTotales < solicitudesMaximas) {
				String linea = scannerArchivo.nextLine();
				String[] partes = linea.split("-");
				nombreSolicitudes[solicitudesTotales]= partes[0];
				apellidoSolicitudes[solicitudesTotales]= partes[1];
				solicitudesTotales++;
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("txt de Solicitudes.txt no encontrado OnO");
		}
		
	}

	public static void administrarCurso(Scanner scanner) {
	    if (!cargandoArchivos) {
	        System.out.println("Debe cargar los archivos primero");
	        return;
	    }

	    int opcion = 0;
	    do {
	        System.out.println("Administración del curso=");
	        System.out.println("1) Cambiar paralelo de un alumno");
	        System.out.println("2) Eliminar alumno del curso");
	        System.out.println("3) Inscribir alumno nuevo");
	        System.out.println("4) Regresar");
	        System.out.print("Ingrese una opción: ");
	        
	        try {
	            opcion = scanner.nextInt();
	            scanner.nextLine();
	            switch (opcion) {
	                case 1:
	                    cambiarParalelo(scanner);
	                    break;
	                case 2:
	                    eliminarAlumno(scanner);
	                    break;
	                case 3:
	                    inscribirAlumnoNuevo(scanner);
	                    break;
	                case 4:
	                    System.out.println("Regresando...");
	                    break;
	                default:
	                    System.out.println("Ingresar opción valida");
	            }
	        } catch (Exception e) {
	            System.out.println("Ingresar opción valida");
	            scanner.nextLine();//otro para saltar y no tenga while infinito
	            
	        }
	    } while (opcion != 4);
	}


	public static void inscribirAlumnoNuevo(Scanner scanner) {
	    if (alumnosTotales >= alumnosMaximos) {
	        System.out.println("No hay espacio");
	        return;
	    }

	    System.out.print("Ingrese nombre: ");
	    String nombre = scanner.nextLine();
	    System.out.print("Ingrese apellido: ");
	    String apellido = scanner.nextLine();
	    System.out.print("Ingrese RUT: ");
	    String rut = scanner.nextLine();
	    System.out.print("Ingrese paralelo (C1/C2): ");
	    //esto es para no tener problemas con mayusculas ni minusculas
	    String paralelo = scanner.nextLine().toUpperCase();

	    if (nombre.isEmpty() || apellido.isEmpty() || rut.isEmpty()) {
	        System.out.println("Todos los campos son obligatorios.");
	        return;
	    }
	    
	    if (!paralelo.equals("C1") && !paralelo.equals("C2")) {
	        System.out.println("Paralelo inválido.");
	        return;
	    }
	    
	    if (buscarAlumnoPorRut(rut) != -1) {
	        System.out.println("El RUT ya está registrado.");
	        return;
	    }

	    nombreAlumnos[alumnosTotales] = nombre;
	    apellidoAlumnos[alumnosTotales] = apellido;
	    rutAlumnos[alumnosTotales] = rut;
	    paraleloAlumnos[alumnosTotales] = paralelo;
	    alumnosTotales++;

	    guardarAlumnos();
	    System.out.println("Alumno inscrito en el curso");
	}


	private static void eliminarAlumno(Scanner scanner) {
		System.out.println("Ingresar rut de alumno a eliminar");
		String rut = scanner.nextLine();
		
		int indice= buscarAlumnosPorRut(rut);
		if(indice== -1) {
			System.out.println("rut no encontrado");
			return;
		}
		System.out.println("Eliminando a: "+nombreAlumnos[indice]+" "+apellidoAlumnos[indice]);
		
		for(int i= indice;i<alumnosTotales-1;i++) {
			nombreAlumnos[i]= nombreAlumnos[i+1];
			apellidoAlumnos[i]= apellidoAlumnos[i+1];
	        rutAlumnos[i]= rutAlumnos[i+1];
	        paraleloAlumnos[i]= paraleloAlumnos[i+1];
		}
		alumnosTotales--;
		
		for (int i = 0; i < admitidosTotales; i++) {
	        if (rutsAdmitidos[i].equalsIgnoreCase(rut)) {
	            for (int j = i; j < admitidosTotales-1; j++) {
	                nombresAdmitidos[j]= nombresAdmitidos[j+1];
	                apellidosAdmitidos[j]= apellidosAdmitidos[j+1];
	                rutsAdmitidos[j]= rutsAdmitidos[j+1];
	                paralelosAdmitidos[j]= paralelosAdmitidos[j+1];
	            }
	            admitidosTotales--;
	            break;
	        }
	    }
		guardarAlumnos();
		System.out.println("Alumno eliminado");
	}


	public static int buscarAlumnosPorRut(String rut) {
	    for (int i = 0; i < alumnosTotales; i++) {
	        if (rutAlumnos[i].equalsIgnoreCase(rut)) {
	            return i;
	        }
	    }
	    return -1;
	}


	public static void cambiarParalelo(Scanner scanner) {
	    System.out.print("Ingrese RUT del alumno: ");
	    String rut = scanner.nextLine();
	    
	    // Buscar el alumno por RUT
	    int indice = buscarAlumnoPorRut(rut);
	    if (indice == -1) {
	        System.out.println("Alumno no encontrado.");
	        return;
	    }

	    System.out.println("Alumno: " + nombreAlumnos[indice] + " " + apellidoAlumnos[indice] +
	                       " (actualmente en " + paraleloAlumnos[indice] + ")");
	    
	    System.out.print("Nuevo paralelo (C1/C2): ");
	    String nuevoParalelo = scanner.nextLine().trim().toUpperCase();

	    if (!nuevoParalelo.equals("C1") && !nuevoParalelo.equals("C2")) {
	        System.out.println("Paralelo inválido.");
	        return;
	    }

	    paraleloAlumnos[indice] = nuevoParalelo;

	    for (int i = 0; i < admitidosTotales; i++) {
	        if (rutsAdmitidos[i].equalsIgnoreCase(rut)) {
	            paralelosAdmitidos[i] = nuevoParalelo;
	            break;
	        }
	    }//tuve que rehacer esto debido a que se bugueó

	    // Guardar cambios en el archivo
	    guardarAlumnos();
	    System.out.println("Paralelo actualizado! Cambios guardados en Alumnos.txt");
	}


	public static int buscarAlumnoPorRut(String rut) {
    for (int i = 0; i < alumnosTotales; i++) {
        if (rutAlumnos[i].equalsIgnoreCase(rut)) {
            return i;
        }
    }
    return -1;
}
	//otra parte con el BufferedWriter...
	public static void guardarAlumnos() {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Alumnos.txt"))) {
	        for (int i = 0; i < alumnosTotales; i++) {
	            bw.write(nombreAlumnos[i] + ";" + apellidoAlumnos[i] + ";" +
	                     rutAlumnos[i] + ";" + paraleloAlumnos[i]);
	            bw.newLine();
	        }
	    } catch (IOException e) {
	        System.out.println("Error al guardar Alumnos.txt: " + e.getMessage());
	    }
	}
	
	


}
