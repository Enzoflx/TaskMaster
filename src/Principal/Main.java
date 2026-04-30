package Principal;

import DAOS.*;
import POJOS.*;
import Utilidades.ConsoleReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static TareaDAO tareaDAO = new TareaDAO();
    private static CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static EstadoDAO estadoDAO = new EstadoDAO();
    private static Usuario usuarioLogueado = null;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        while (true) {
            if (usuarioLogueado == null) {
                mostrarBienvenida();
                mostrarMenuAcceso();
                int opcion = ConsoleReader.readInt(0, 2);
                if (opcion == 0) {
                    break;
                }
                gestionarMenuAcceso(opcion);
            } else {
                mostrarMenuFuncionalidades();
                int opcion = ConsoleReader.readInt(0, 8);
                if (opcion == 0) {
                    usuarioLogueado = null;
                    System.out.println("Sesión cerrada.");
                } else {
                    gestionarMenuFuncionalidades(opcion);
                }
            }
        }
        System.out.println("\n¡Gracias por usar TasksMaster! Hasta pronto.");
    }

    public static void mostrarBienvenida() {
        System.out.println("\n========================================");
        System.out.println("             TasksMaster                ");
        System.out.println("========================================");
    }

    public static void mostrarMenuAcceso() {
        System.out.println("\n[ MENÚ DE ACCESO ]");
        System.out.println("1. Tengo cuenta (Login)");
        System.out.println("2. Crear cuenta (Registro)");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------");
        System.out.print("Selecciona una opción: ");
    }

    private static void gestionarMenuAcceso(int opcion) {
        switch (opcion) {
            case 1:
                login();
                break;
            case 2:
                registro();
                break;
        }
    }

    private static void login() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Email: ");
        String email = ConsoleReader.readString();
        System.out.print("Contraseña: ");
        String password = ConsoleReader.readString();
        usuarioLogueado = usuarioDAO.login(email, password);
        if (usuarioLogueado != null) {
            System.out.println("¡Bienvenido, " + usuarioLogueado.getNombre() + "!");
        } else {
            System.out.println("Error: Email o contraseña incorrectos.");
        }
    }

    private static void registro() {
        System.out.println("\n--- REGISTRO ---");
        System.out.print("Nombre: ");
        String nombre = ConsoleReader.readString();
        System.out.print("Email: ");
        String email = ConsoleReader.readString();
        System.out.print("Contraseña: ");
        String password = ConsoleReader.readString();
        Usuario nuevo = new Usuario(nombre, email, password);
        if (usuarioDAO.registro(nuevo)) {
            System.out.println("Registro completado con éxito. Ya puedes iniciar sesión.");
        } else {
            System.out.println("Error: No se pudo completar el registro.");
        }
    }

    public static void mostrarMenuFuncionalidades() {
        System.out.println("\n[ MENÚ PRINCIPAL - Usuario: " + usuarioLogueado.getNombre() + " ]");
        System.out.println("1. Ver todas mis tareas");
        System.out.println("2. Crear nueva tarea");
        System.out.println("3. Editar tarea existente");
        System.out.println("4. Marcar tarea como COMPLETADA");
        System.out.println("5. Eliminar una tarea");
        System.out.println("6. Filtrar tareas por categoría");
        System.out.println("7. Ver información de mi perfil");
        System.out.println("8. Filtrar tareas por estado");
        System.out.println("0. Cerrar sesión");
        System.out.println("----------------------------------------");
        System.out.print("Selecciona una opción: ");
    }

    private static void gestionarMenuFuncionalidades(int opcion) {
        switch (opcion) {
            case 1:
                listarTareas();
                break;
            case 2:
                crearTarea();
                break;
            case 3:
                editarTarea();
                break;
            case 4:
                completarTarea();
                break;
            case 5:
                eliminarTarea();
                break;
            case 6:
                filtrarTareas();
                break;
            case 7:
                verPerfil();
                break;
            case 8:
                filtrarTareasPorEstado();
                break;
        }
    }

    private static void listarTareas() {
        System.out.println("\n--- MIS TAREAS ---");
        List<Tarea> tareas = tareaDAO.listarTareasPorUsuario(usuarioLogueado.getId());
        if (tareas.isEmpty()) {
            System.out.println("No tienes tareas registradas.");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                Tarea t = tareas.get(i);
                System.out.println(t);
            }
        }
    }

    private static void crearTarea() {
        System.out.println("\n--- NUEVA TAREA ---");
        System.out.print("Título: ");
        String titulo = ConsoleReader.readString();
        System.out.print("Descripción: ");
        String desc = ConsoleReader.readString();
        LocalDateTime fechaLimite = leerFecha();
        
        System.out.println("Selecciona Categoría:");
        List<Categoria> cats = categoriaDAO.listarCategorias();
        for (int i = 0; i < cats.size(); i++) {
            Categoria c = cats.get(i);
            System.out.println(c.getId() + ". " + c.getNombre());
        }
        int idCat = ConsoleReader.readInt();
        Categoria cat = categoriaDAO.obtenerPorId(idCat);

        System.out.println("Selecciona Estado:");
        List<Estado> ests = estadoDAO.listarEstados();
        for (int i = 0; i < ests.size(); i++) {
            Estado e = ests.get(i);
            System.out.println(e.getId() + ". " + e.getNombre());
        }
        int idEst = ConsoleReader.readInt();
        Estado est = estadoDAO.obtenerPorId(idEst);

        System.out.print("Observaciones: ");
        String obs = ConsoleReader.readString();

        Tarea t = new Tarea(0, titulo, desc, LocalDateTime.now(), fechaLimite, est, usuarioLogueado, cat, obs);
        if (tareaDAO.insertar(t)) {
            System.out.println("Tarea creada correctamente.");
        } else {
            System.out.println("Error al crear la tarea.");
        }
    }

    private static void editarTarea() {
        System.out.print("\nIntroduce el ID de la tarea a editar: ");
        int id = ConsoleReader.readInt();
        Tarea t = tareaDAO.obtenerPorId(id);
        
        if (t == null || t.getUsuario().getId() != usuarioLogueado.getId()) {
            System.out.println("Tarea no encontrada o no tienes permiso.");
            return;
        }

        System.out.print("Nuevo título [" + t.getTitulo() + "]: ");
        String titulo = ConsoleReader.readString();
        if (!titulo.isEmpty()) {
            t.setTitulo(titulo);
        }

        System.out.print("Nueva descripción [" + t.getDescripcion() + "]: ");
        String desc = ConsoleReader.readString();
        if (!desc.isEmpty()) {
            t.setDescripcion(desc);
        }

        System.out.println("¿Cambiar fecha límite? (s/n)");
        if (ConsoleReader.readString().equalsIgnoreCase("s")) {
            t.setFechaLimite(leerFecha());
        }

        System.out.println("Selecciona Nueva Categoría (0 para no cambiar):");
        List<Categoria> cats = categoriaDAO.listarCategorias();
        for (int i = 0; i < cats.size(); i++) {
            Categoria c = cats.get(i);
            System.out.println(c.getId() + ". " + c.getNombre());
        }
        int idCat = ConsoleReader.readInt();
        if (idCat != 0) {
            t.setCategoria(categoriaDAO.obtenerPorId(idCat));
        }

        System.out.println("Selecciona Nuevo Estado (0 para no cambiar):");
        List<Estado> ests = estadoDAO.listarEstados();
        for (int i = 0; i < ests.size(); i++) {
            Estado e = ests.get(i);
            System.out.println(e.getId() + ". " + e.getNombre());
        }
        int idEst = ConsoleReader.readInt();
        if (idEst != 0) {
            t.setEstado(estadoDAO.obtenerPorId(idEst));
        }

        if (tareaDAO.actualizar(t)) {
            System.out.println("Tarea actualizada con éxito.");
        } else {
            System.out.println("Error al actualizar la tarea.");
        }
    }

    private static void completarTarea() {
        System.out.print("\nIntroduce el ID de la tarea a completar: ");
        int id = ConsoleReader.readInt();
        Tarea t = tareaDAO.obtenerPorId(id);
        if (t != null && t.getUsuario().getId() == usuarioLogueado.getId()) {
            if (tareaDAO.marcarCompletada(id)) {
                System.out.println("Tarea marcada como completada.");
            } else {
                System.out.println("Error al actualizar la tarea.");
            }
        } else {
            System.out.println("Tarea no encontrada o no tienes permiso.");
        }
    }

    private static void eliminarTarea() {
        System.out.print("\nIntroduce el ID de la tarea a eliminar: ");
        int id = ConsoleReader.readInt();
        Tarea t = tareaDAO.obtenerPorId(id);
        if (t != null && t.getUsuario().getId() == usuarioLogueado.getId()) {
            if (tareaDAO.eliminar(id)) {
                System.out.println("Tarea eliminada con éxito.");
            } else {
                System.out.println("Error al eliminar la tarea.");
            }
        } else {
            System.out.println("Tarea no encontrada o no tienes permiso.");
        }
    }

    private static void filtrarTareas() {
        System.out.println("\nSelecciona Categoría para filtrar:");
        List<Categoria> cats = categoriaDAO.listarCategorias();
        for (int i = 0; i < cats.size(); i++) {
            Categoria c = cats.get(i);
            System.out.println(c.getId() + ". " + c.getNombre());
        }
        int idCat = ConsoleReader.readInt();
        
        List<Tarea> tareas = tareaDAO.listarTareasPorUsuarioYcategoria(usuarioLogueado.getId(), idCat);
        if (tareas.isEmpty()) {
            System.out.println("No se encontraron tareas en esta categoría.");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                Tarea t = tareas.get(i);
                System.out.println(t);
            }
        }
    }

    private static void verPerfil() {
        System.out.println("\n--- MI PERFIL ---");
        System.out.println("Nombre: " + usuarioLogueado.getNombre());
        System.out.println("Email:  " + usuarioLogueado.getEmail());
        System.out.println("ID:     " + usuarioLogueado.getId());
    }

    private static void filtrarTareasPorEstado() {
        System.out.println("\nSelecciona Estado para filtrar:");
        List<Estado> ests = estadoDAO.listarEstados();
        for (int i = 0; i < ests.size(); i++) {
            Estado e = ests.get(i);
            System.out.println(e.getId() + ". " + e.getNombre());
        }
        int idEst = ConsoleReader.readInt();

        List<Tarea> tareas = tareaDAO.listarTareasPorEstado(usuarioLogueado.getId(), idEst);
        if (tareas.isEmpty()) {
            System.out.println("No se encontraron tareas con ese estado.");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                Tarea t = tareas.get(i);
                System.out.println(t);
            }
        }
    }

    private static LocalDateTime leerFecha() {
        System.out.print("Fecha límite (yyyy-MM-dd HH:mm) o ENTER para ninguna: ");
        String fechaStr = ConsoleReader.readString();
        if (fechaStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Formato inválido. Se guardará sin fecha límite.");
            return null;
        }
    }
}