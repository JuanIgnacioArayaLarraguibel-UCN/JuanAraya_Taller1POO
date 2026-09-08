package JuanAraya_Taller1POO;

import java.util.Scanner;

//Juan Ignacio Araya Larraguibel - 21.566.260-8 - ITI

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
			}
		}while(opcion!=7);
		scanner.close();
		
		

	}

}
