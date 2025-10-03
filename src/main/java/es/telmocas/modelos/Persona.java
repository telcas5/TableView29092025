package es.telmocas.modelos;

import java.time.LocalDate;

/**
 * Representa una persona con un identificador, nombre, apellidos y fecha de cumpleaños.
 * <p>
 * Incluye constructores, getters y un método {@link #toString()} para representar
 * la información de la persona en formato de texto.
 * </p>
 *
 * @author Telmo Castillo
 * @version 1.0
 */
public class Persona {

    /**
     * Identificador único de la persona.
     */
    private final int id;

    /* ATOMICINTEGER */




    /**
     * Nombre de la persona.
     */
    private final String nombre;

    /**
     * Apellidos de la persona.
     */
    private final String apellidos;

    /**
     * Fecha de cumpleaños de la persona.
     */
    private final LocalDate cumpleanos;

    /**
     * Constructor principal de la clase Persona.
     *
     * @param id Identificador único de la persona.
     * @param nombre Nombre de la persona.
     * @param apellidos Apellidos de la persona.
     * @param cumpleanos Fecha de cumpleaños de la persona.
     */
    public Persona(int id, String nombre, String apellidos, LocalDate cumpleanos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cumpleanos = cumpleanos;
    }

    /**
     * Obtiene el identificador de la persona.
     *
     * @return id de la persona.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos de la persona.
     *
     * @return apellidos de la persona.
     */
    public String getApellido() {
        return apellidos;
    }

    /**
     * Obtiene la fecha de cumpleaños de la persona.
     *
     * @return fecha de cumpleaños de la persona.
     */
    public LocalDate getCumpleanos() {
        return cumpleanos;
    }

    /**
     * Representación en texto de la persona.
     *
     * @return una cadena con los valores de id, nombre, apellidos y cumpleaños.
     */
    @Override
    public String toString() {
        return "Persona {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", cumpleanos=" + cumpleanos +
                '}';
    }
}
