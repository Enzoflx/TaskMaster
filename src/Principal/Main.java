package Principal;

public class Main {

    public static void main(String[] args) {
        mostrarBienvenida();
        mostrarMenuAcceso();
        mostrarMenuFuncionalidades();
    }

    public static void mostrarBienvenida() {
        System.out.println("========================================");
        System.out.println("             TasksMaster                ");
        System.out.println("========================================");
    }

    public static void mostrarMenuAcceso() {
        System.out.println("\n[ MENÚ DE ACCESO ]");
        System.out.println("1. Tengo cuenta (Login)");
        System.out.println("2. Crear cuenta");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------");
    }

    public static void mostrarMenuFuncionalidades() {
        System.out.println("\n[ Selecciona una opción: ]");
        System.out.println("1. Ver todas mis tareas");
        System.out.println("2. Crear nueva tarea");
        System.out.println("3. Editar tarea existente");
        System.out.println("4. Marcar tarea como COMPLETADA");
        System.out.println("5. Eliminar una tarea");
        System.out.println("6. Filtrar tareas por categoría");
        System.out.println("7. Ver información de mi perfil");
        System.out.println("0. Cerrar sesión");
        System.out.println("----------------------------------------");
    }
}