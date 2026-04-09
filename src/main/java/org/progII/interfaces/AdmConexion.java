package org.progII.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface AdmConexion {

    default Connection ObtenerConexion () {

        String dbDriver= "com.mysql.cj.jdbc.Driver";


        String dbCadenaConexion = "jdbc:mysql://127.0.0.1:3306/consultorio";

        String dbUsuario="root";

        String dbPass="root";

        Connection conn =null;


        try {
            //Código propenso a fallar
            Class.forName(dbDriver);

            conn = DriverManager.getConnection (dbCadenaConexion, dbUsuario, dbPass);
        }
        catch (ClassNotFoundException e) {
            //Código para manejar la excepción
            System.out.println("No se encontró el Driver de la BD");
            throw new RuntimeException(e); //Si se aparece otro fallo

        } catch (SQLException e){
            System.out.println("No se pudo conectar la Base de Datos");
            throw new RuntimeException(e);
        }
        System.out.println("Conexión exitosa a la Base de Datos");
        return conn; }

}
