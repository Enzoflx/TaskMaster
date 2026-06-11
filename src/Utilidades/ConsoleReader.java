package Utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase de utilidad para leer e interpretar datos ingresados por consola de forma segura.
 * Controla excepciones de entrada/salida y formatos inválidos.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class ConsoleReader {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private ConsoleReader() {
        throw new UnsupportedOperationException("Clase de utilidad");
    }

    /**
     * Lee un número entero de la consola.
     * En caso de formato inválido, solicita reintentar.
     * 
     * @return El número entero ingresado por el usuario.
     */
    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(reader.readLine().trim());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
            }
        }
    }

    /**
     * Lee un número entero de la consola que se encuentre dentro de un rango específico [inicio, fin].
     * 
     * @param inicio El valor mínimo aceptable (inclusive).
     * @param fin El valor máximo aceptable (inclusive).
     * @return El número entero ingresado que se encuentra dentro del rango.
     */
    public static int readInt(int inicio, int fin) {
        while (true) {
            int a = readInt();
            if (a>=inicio && a <=fin)
                return a;
            else
                System.out.println("Entrada no válida. Intenta de nuevo.");
        }
    }

    /**
     * Lee un número decimal (double) de la consola.
     * En caso de formato inválido, solicita reintentar.
     * 
     * @return El número decimal ingresado por el usuario.
     */
    public static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(reader.readLine().trim());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
            }
        }
    }

    /**
     * Lee una cadena de caracteres de la consola.
     * En caso de error de lectura, solicita reintentar.
     * 
     * @return La cadena de caracteres ingresada por el usuario (sin espacios al principio ni al final).
     */
    public static String readString() {
        while (true) {
            try {
                return reader.readLine().trim();
            } catch (IOException e) {
                System.out.println("Error de lectura. Intenta de nuevo.");
            }
        }
    }
}
