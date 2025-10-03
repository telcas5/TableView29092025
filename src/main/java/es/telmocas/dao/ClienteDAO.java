package es.telmocas.dao;

import es.telmocas.conexion.Conexion;
import es.telmocas.modelos.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Insertar
    public void insertarCliente(Persona persona) {
        String sql = "INSERT INTO cliente (nombre, apellido, cumpleaños) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setDate(3, Date.valueOf(persona.getCumpleanos()));

            stmt.executeUpdate();
            System.out.println("Cliente insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar
    public void eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener todos
    public List<Persona> obtenerTodos() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, cumpleaños FROM cliente";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("cumpleaños").toLocalDate()
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}