import Clase.Cliente;
import Clase.Habitacion;
import Clase.JsonUtiles;
import Clase.SistemaHotel;
import Enums.MetodoPago;
import Enums.MotivoNoDisponible;
import Enums.ServicioEspecialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoException;
import Excepcion.NoRegistradoException;
import Excepcion.UsuarioNoEncontradoException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SistemaHotel sistemaHotel = descargarInfo();
       // System.out.println(sistemaHotel.toString());
        menuPrincipal(sistemaHotel);
        try{
            sistemaHotel.pasarAJSONaArchivo();
            //mostrando el archivo:
            System.out.println(JsonUtiles.descargarJson());
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    // deserializacion:
    public static SistemaHotel descargarInfo(){
        SistemaHotel envolvente;
        try{
            String info=JsonUtiles.descargarJson();
            JSONObject lista = new JSONObject(info);
            envolvente = new SistemaHotel(lista);
        }catch(JSONException e){
            envolvente = new SistemaHotel();
            System.out.println("Error al abrir el archivo");
        }
        return envolvente;
    }

    // Menu principal. Va a contener el resto de los metodos necesarios para que funcione
    public static void menuPrincipal(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String nombre = "";
        String contrasenia = "";

        char seguir = 's';

        do {
            System.out.println("Hotel BellaVista.com");
            System.out.println("\n=============================================");
            System.out.println("🏨  BIENVENIDO A HOTEL BELLAVISTA  🏨");
            System.out.println("=============================================");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción...");

            try {

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        menuInterno(sistema);
                        break;
                    case 2:
                        System.out.println("Ingrese nombre de usuario:");
                        nombre = sc.nextLine();
                        System.out.println("Ingrese contrasena:");
                        contrasenia = sc.nextLine();
                        try {
                            String modo = sistema.iniciarSesion(nombre, contrasenia);
                            System.out.println("Inicio sesion correctamente como " + modo);
                            if (modo.equals("Recepcionista")) {
                                opcionRecepcionista(sistema);
                            }
                            if (modo.equals("Administrador")) {
                                opcionAdministrador(sistema);
                            }
                            if (modo.equals("Cliente")) {
                                Cliente c1 = (Cliente) sistema.getActual();
                                if (c1.getNombre() == null || c1.getNombre().isEmpty() || c1.getDni() == 0 || c1.getDomicilio() == null || c1.getDomicilio().isEmpty()){
                                    System.out.println("Complete sus datos personales antes de continuar:");
                                    System.out.print("Nombre completo: ");
                                    String nombreC = sc.nextLine();
                                    System.out.print("Dni: ");
                                    int dniC = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Domicilio: ");
                                    String domicilioC = sc.nextLine();
                                    MetodoPago metodoPagoC = menuMetodoPago();
                                    try {
                                        sistema.completarDatosCliente(nombreC, dniC, domicilioC, metodoPagoC);
                                    } catch (NoRegistradoException e) {
                                        System.out.println(e.getMessage());
                                    }

                                }
                                try
                                {
                                    opcionCliente(sistema);

                                }catch (DateTimeParseException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                            }
                        } catch (UsuarioNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Gracias por usar nuestro sistema. ¡Hasta pronto!");
                        seguir = 'n';
                        break;
                    default:
                        System.out.println("Error. Opcion no valida. Intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido (1, 2 o 3).");
                sc.nextLine();
            }
            if (opcion != 3) {
                System.out.println("\n¿Desea realizar otra accion? (s/n): ");
                seguir = sc.next().charAt(0);
            }
        } while (seguir == 's');
    }

    // Tengo que llamar en los parametros al sistema creado en el main porque sino lo que yo cargue en estos metodos no se va a guardar.
    public static void menuInterno(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        char seguir = 's';
        boolean encontrado = false;
        do {
            System.out.println("=============================================");
            System.out.println("          Registrando usuario  ");
            System.out.println("=============================================");
            System.out.println("Seleccione modo de acceso:");
            System.out.println("1. Administrador");
            System.out.println("2. Recepcionista");
            System.out.println("3. Cliente");
            System.out.println("4. salir");

            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese nombre de usuario:");
                        String nameAdmin = sc.nextLine();
                        System.out.println("Ingrese contrasena:");
                        String passAdmin = sc.nextLine();
                        try {
                            sistema.registrarUsuario(nameAdmin, passAdmin, 1);
                        } catch (DuplicadoException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 2:
                        System.out.println("Ingrese nombre de usuario:");
                        String nameRecep = sc.nextLine();
                        System.out.println("Ingrese contrasena:");
                        String passRecep = sc.nextLine();
                        try {
                            sistema.registrarUsuario(nameRecep, passRecep, 2);
                        } catch (DuplicadoException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 3:

                        System.out.println("Ingrese nombre de usuario:");
                        String nameCliente = sc.nextLine();
                        System.out.println("Ingrese contrasena:");
                        String passCliente = sc.nextLine();
                        try {
                            sistema.registrarUsuario(nameCliente, passCliente, 3);
                        } catch (DuplicadoException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 4:
                        encontrado = true;
                        break;
                    default:
                        System.out.println("Error. Opcion no valida...");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido (1,2,3 o 4).");
                sc.nextLine();
            }
            if (!encontrado) {
                System.out.println("¿Desea registrarse en otro modo?");
                seguir = sc.next().charAt(0);
            }
        } while (seguir == 's' && !encontrado);


    }
// menu con las opciones para un recepcionista
    public static void opcionRecepcionista(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        char seguir = 's';

        do {
            System.out.println("=============================================");
            System.out.println("        Recepcionista    ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Hacer CheckIn");
            System.out.println("2. Hacer CheckOut");
            System.out.println("3. Consultar disponibilidad de habitaciones");
            System.out.println("4. Buscar reserva");
            System.out.println("5. Buscar cliente");
            System.out.println("6. Ver clientes");
            System.out.println("7. Ver reservas");
            System.out.println("8. Ver habitaciones no disponibles por motivo");
            System.out.println("9. Ver habitaciones ocupadas");


            try {
                opcion = sc.nextInt();
                sc.nextLine(); //limpio el buffer
                switch (opcion) {
                    case 1:
                        //pedimos los datos al usuario
                        System.out.println("Ingrese dni del cliente:");
                        int dni = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Ingrese Id reserva:");
                        int idReserva = sc.nextInt();
                        sc.nextLine();

                        sistema.checkIn(dni, idReserva);
                        break;
                    case 2:
                        System.out.println("ingrese id reserva");
                        int idReserva2 = sc.nextInt();
                        System.out.println("ingrese dni del cliente");
                        int dniCliente = sc.nextInt();

                        sistema.checkOut(idReserva2, dniCliente);
                        break;
                    case 3:
                        //consultar disponibilidad
                        System.out.println(sistema.consultarDisponibilidad());
                        break;
                    case 4:
                        //buscar reserva
                        System.out.println("ingrese id de reserva a buscar");
                        int idRb = sc.nextInt();
                        System.out.println(sistema.buscarReserva(idRb));
                        break;
                    case 5:
                        //buscar cliente
                        System.out.println("ingrese dni del cliente a buscar");
                        int dniB = sc.nextInt();
                        System.out.println(sistema.buscarCliente(dniB)); //probar
                        break;
                    case 6:
                        System.out.println(sistema.mostrarClientes());
                        break;
                    case 7:
                        System.out.println(sistema.mostrarReservas());
                        break;
                    case 8:
                        System.out.println(sistema.verHabitacionesNoDisponiblesPorMotivo());
                        break;
                    case 9:
                        System.out.println(sistema.verHabitacionesOcupadas());
                        break;
                    default:
                        System.out.println("opcion no valida");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println(" Ingrese un número válido (1-9):");
                sc.nextLine();
            }
            System.out.println("¿Desea elegir otra opcion?");
            seguir = sc.next().charAt(0);


        } while (seguir == 's');
    }

    //menu para las opciones del administrador:
    public static void opcionAdministrador(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        char seguir = 's';

        do {
            System.out.println("=============================================");
            System.out.println("      Administrador   ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Mostrar hotel");
            System.out.println("2. Ver recepcionista");
            System.out.println("3. Cargar habitacion");
            System.out.println("4. Buscar habitacion");
            System.out.println("5. Ver todas las habitaciones del hotel");
            System.out.println("6. Eliminar habitacion");
            try {
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        //funciona
                        System.out.println(sistema.mostrarHotel());
                        break;
                    case 2:
                        System.out.println(sistema.verRecepcionista());
                        break;
                    case 3:
                        menuCargarHabitacion(sistema);
                        break;
                    case 4:
                        System.out.println("Ingrese id de la habitacion a buscar:");
                        int id = sc.nextInt();
                        System.out.println(sistema.mostrarHabitacion(id));
                        break;
                    case 5:
                        System.out.println(sistema.mostrarHabitaciones());

                        break;
                    case 6:
                        System.out.println("ingrese id de habitacion a eliminar");
                        int idHabitacionB = sc.nextInt();
                        boolean rta = sistema.eliminarHabitacion(idHabitacionB);
                        if (rta == true) {
                            System.out.println("Se elimino la habitacion");
                        } else {
                            System.out.println("No se elimino la habitacion");
                        }
                        break;
                    default:
                        System.out.println("opcion no valida");
                        break;
                }
       // esta excepcion es para que el usuario no ponga una letra o un nro invalido cuando le pedimos por teclado.
            } catch (InputMismatchException e) {
                System.out.println(" Ingrese un número válido (1-6):");
                sc.nextLine();
            }
            System.out.println("¿Desea elegir otra opcion?");
            seguir = sc.next().charAt(0);


        } while (seguir == 's');
    }

    //menu de opciones del cliente:
    public static void opcionCliente(SistemaHotel sistema1) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        char seguir = 's';
        // para que el usuario tenga una mejor experiencia le ponemos el mensaje de error apenas escriba mal la fecha:
        do {
            System.out.println("=============================================");
            System.out.println("        Cliente     ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Hacer reserva");
            System.out.println("2. Ver mis reservas");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        //hacer reserva
                        System.out.println("Habitaciones disponibles:");
                        System.out.println(sistema1.consultarDisponibilidad()); //mostramos las habitaciones que tenemos en el hotel disponibles

                        System.out.println("Ingrese idHabitacion a reservar:");
                        int idHabitacion = sc.nextInt();
                        sc.nextLine();

                        //para la fecha de ingreso
                        LocalDate hoy= LocalDate.now();
                        LocalDate checkIn=null;
                        LocalDate checkOut=null;
                        boolean fechaValida=false;

                        while(!fechaValida){
                            System.out.println("\n --- Fecha de ingreso ---");
                            System.out.println("Ingrese anio: ");
                            int anio = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Ingrese mes: ");
                            int mes = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Ingrese dia: ");
                            int dia = sc.nextInt();
                            sc.nextLine(); // limpio el buffer


                            try
                            {
                                checkIn = LocalDate.of(anio, mes, dia);
                                if(checkIn.isBefore(hoy))
                                {
                                    System.out.println("La fecha de ingreso no puede ser anterior a la fecha actual ("+ hoy +").");
                                }
                                else
                                {
                                    fechaValida=true;
                                }

                            }catch (DateTimeException e){
                                System.out.println("Fecha invalida. Intente nuevamente...");
                            }

                        }

                        fechaValida=false;
                        while(!fechaValida){
                            System.out.println("\n--- Fecha de salida ---");
                            System.out.println("Ingrese anio: ");
                            int anio = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Ingrese mes: ");
                            int mes = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Ingrese dia: ");
                            int dia = sc.nextInt();
                            sc.nextLine();

                            try
                            {
                                checkOut = LocalDate.of(anio, mes, dia);
                                if(checkOut.isBefore(checkIn))
                                {
                                    System.out.println("La fecha de salida no puede ser anterior a la de ingrero ("+ checkIn +").");
                                }
                                else {
                                    fechaValida=true;
                                }
                            }catch (DateTimeException e){
                                System.out.println("Fecha invalida. Intente nuevamente...");
                            }
                        }
                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String fechaInicio = checkIn.format(formatter);
                        String fechaSalida = checkOut.format(formatter);

                        //para calcular el precio por noche utilizo el ChronoUnit y le paso el checkIn y el checkOut
                        long noches = ChronoUnit.DAYS.between(checkIn,checkOut);

                        Habitacion habitacion=sistema1.buscarHabitacionPorId(idHabitacion);
                        if(habitacion==null)
                        {
                            System.out.println("La habitacion no existe.");
                            break;
                        }

                        //calculando el precio:
                        double precioPorNoche= habitacion.getPrecio();
                        double total= noches *  precioPorNoche;

                        //Mostrar detalle al cliente
                        System.out.println("\n===== DETALLE DE LA RESERVA =====");
                        System.out.println("Habitación ID: " + idHabitacion);
                        System.out.println("Fecha ingreso: " + checkIn);
                        System.out.println("Fecha salida: " + checkOut);
                        System.out.println("Noches: " + noches);
                        System.out.println("Precio por noche: $" + precioPorNoche);
                        System.out.println("TOTAL: $" + total);
                        System.out.println("=================================\n");

                        //para que el cliente si desea cambiar algo la puede cancelar y hacer devuelta
                        System.out.println("¿Desea confirmar la reserva? (s/n)");
                        char confirmar = sc.next().charAt(0);
                        // Crear reserva
                        try {
                            System.out.println(sistema1.hacerReserva(idHabitacion, fechaInicio, fechaSalida));
                        } catch (NoRegistradoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println(sistema1.verMisReservas());
                        break;
                    default:
                        System.out.println("opcion no valida");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido (1).");
                sc.nextLine();
            }
            System.out.println("¿Desea elegir otra opcion?");
            seguir = sc.next().charAt(0);


        } while (seguir == 's');
    }

    //menu para elegir las opciones de habitacion. tenemos 3 tipos de habitaciones
    public static void menuCargarHabitacion(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        char seguir = 's';
        MotivoNoDisponible motivoNoDisponible = null;
        MotivoNoDisponible motivoNoDisponibleD = null;
        MotivoNoDisponible motivoNoDisponibleS = null;
        do {
            System.out.println("Seleccione el tipo de habitacion a cargar: ");
            System.out.println("1. Estandar");
            System.out.println("2. Deluxe");
            System.out.println("3. Suite");
            System.out.println("4. Salir");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        //estandar
                        System.out.println("Habitacion Estandar");

                        System.out.println("Ingrese descripcion: ");
                        String descripcion = sc.nextLine();

                        System.out.println("Ingrese cantidad de personas permitidas");
                        int personasPermitidas = sc.nextInt();

                        boolean estado = menuEstadoHabitacion();
                        if (!estado) {
                            motivoNoDisponible = menuMotivoNoDisponible();
                        }

                        sistema.cargarHabitacionEstandar( descripcion, personasPermitidas, estado, motivoNoDisponible);

                        System.out.println("se agrego con exito la habitacion estandar");
                        break;
                    case 2:
                        //deluxe
                        System.out.println("Habitacion deluxe");


                        System.out.println("ingrese descripcion");
                        String descripcionD = sc.nextLine();


                        System.out.println("ingrese cantidad de personas permitidas");
                        int personasPermitidasD = sc.nextInt();

                        ServicioEspecialDeluxe especialDeluxe = menuServicioDeluxe();
                        boolean estadoD = menuEstadoHabitacion();

                        if (!estadoD) {
                            motivoNoDisponibleD = menuMotivoNoDisponible();

                        }
                        sistema.cargarHabitacionDeluxe(descripcionD, personasPermitidasD, especialDeluxe, estadoD, motivoNoDisponibleD);
                        System.out.println("se agrego con exito la habitacion deluxe");
                        break;
                    case 3:
                        //suite
                        System.out.println("Habitacion Suite");


                        System.out.println("Ingrese descripcion");
                        String descripcionS = sc.nextLine();


                        System.out.println("Ingrese cantidad de personas permitidas");
                        int personasPermitidasS = sc.nextInt();

                        ServicioEspecialSuite especialSuite = menuServicioSuite();
                        boolean estadoS = menuEstadoHabitacion();
                        if (!estadoS) {
                            motivoNoDisponibleS = menuMotivoNoDisponible();

                        }
                        sistema.cargarHabitacionSuite( descripcionS, personasPermitidasS, especialSuite, estadoS, motivoNoDisponibleS);

                        System.out.println("se agrego con exito la habitacion suite");
                        break;
                    case 4:
                        seguir = 'n';
                        break;
                    default:
                        System.out.println("opcion invalida");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println(" Ingrese un número válido (1-4).");
                sc.nextLine();
            }
            if (opcion != 4) {
                System.out.println("Desea cargar otra habitacion? (s/n)");
                seguir = sc.next().charAt(0);
                sc.nextLine();
            }
        } while (seguir == 's');

    }

    //menu del metodo de pago que va a utilizar el cliente. es un enum
    public static MetodoPago menuMetodoPago() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean valido = false;
        MetodoPago metodoPago = null;
        while (!valido) {
            try {
                System.out.println("Seleccione metodo de pago a utilizar: ");
                System.out.println("1. Efectivo");
                System.out.println("2. Debito");
                System.out.println("3. Credito");

                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        metodoPago = MetodoPago.EFECTIVO;
                        valido = true;
                        break;
                    case 2:
                        metodoPago = MetodoPago.DEBITO;
                        valido = true;
                        break;
                    case 3:
                        metodoPago = MetodoPago.CREDITO;
                        valido = true;
                        break;
                    default:
                        System.out.println("opcion invalida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida. Ingrese un numero: 1, 2 o 3");
                sc.nextLine();
            }
        }
        return metodoPago;
    }

    //menu para cuando cargamos una habitacion saber si esta disponible u ocupada por algun motivo especifico.
    public static boolean menuEstadoHabitacion() {
        Scanner sc = new Scanner(System.in);
        boolean estado = false;
        boolean entrada = false;
        int opcion = 0;
        while (!entrada) {
            try {
                System.out.println("Seleccione el estado de la habitacion");
                System.out.println("1. Disponible");
                System.out.println("2. Ocupada");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        estado = true;
                        entrada = true;
                        break;
                    case 2:
                        estado = false;
                        entrada = true;
                        sc.nextLine();

                        break;
                    default:
                        System.out.println("Opcion invalida. Ingrese opcion 1 o 2");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Ingrese un numero: 1 o 2");
                sc.nextLine();
            }
        }
        return estado;
    }

    //menu de los servicios de la categoria deluxe
    public static ServicioEspecialDeluxe menuServicioDeluxe() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean valido = false;
        ServicioEspecialDeluxe especialDeluxe = null;
        while (!valido) {
            try {
                System.out.println("Seleccione el servicio deluxe");
                System.out.println("1. Jaccuzzi");
                System.out.println("2. Vista personalizada");
                System.out.println("3. Bar");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        especialDeluxe = ServicioEspecialDeluxe.JACCUZZI;
                        valido = true;
                        break;
                    case 2:
                        especialDeluxe = ServicioEspecialDeluxe.VISTA_PERSONALIZADA;
                        valido = true;
                        break;
                    case 3:
                        especialDeluxe = ServicioEspecialDeluxe.BAR;
                        valido = true;
                        break;
                    default:
                        System.out.println("opcion no valida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida. Ingrese opciones 1, 2 o 3");
                sc.nextLine();
            }
        }
        return especialDeluxe;
    }

    //menu de los servicios para la categoria Suite
    public static ServicioEspecialSuite menuServicioSuite() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean valido = false;
        ServicioEspecialSuite especialSuite = null;
        while (!valido) {
            try {
                System.out.println("Seleccione servicio suite");
                System.out.println("1. Pileta climatizada");
                System.out.println("2. Cine interactivo");
                System.out.println("3. Helipuerto");
                System.out.println("4. Limusina");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        especialSuite = ServicioEspecialSuite.PILETA_CLIMATIZADA;
                        valido = true;
                        break;
                    case 2:
                        especialSuite = ServicioEspecialSuite.CINE_INTERACTIVO;
                        valido = true;
                        break;
                    case 3:
                        especialSuite = ServicioEspecialSuite.HELIPUERTO;
                        valido = true;
                        break;
                    case 4:
                        especialSuite = ServicioEspecialSuite.LIMUSINA;
                        valido = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida. Ingrese opciones 1, 2, 3 o 4");
            }
        }
        return especialSuite;
    }

    //cuando cargamos que la habitacion no esta disponible por algun motivo, debemos seleccionar alguno de estos 3 motivos que estan en el enum
    public static MotivoNoDisponible menuMotivoNoDisponible() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean valido = false;
        MotivoNoDisponible motivoNoDisponible = null;
        while (!valido) {
            try {
                System.out.println("Seleccione motivo de no disponibilidad de la habitacion:");
                System.out.println("1. Limpieza");
                System.out.println("2. Desinfeccion");
                System.out.println("3. Reparacion");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        motivoNoDisponible = MotivoNoDisponible.LIMPIEZA;
                        valido = true;
                        break;
                    case 2:
                        motivoNoDisponible = MotivoNoDisponible.DESINFECCION;
                        valido = true;
                        break;
                    case 3:
                        motivoNoDisponible = MotivoNoDisponible.REPARACION;
                        valido = true;
                        break;
                    default:
                        System.out.println("opcion invalida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida. Ingrese un numero: 1, 2 o 3");
                sc.nextLine();
            }
        }
        return motivoNoDisponible;
    }
}