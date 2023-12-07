
package tap_u4_ejercicio1_mysqlbasico;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
    Connection conexion;
    Statement transaccion;
    ResultSet cursor;
    
    String cadenaConexion = "jdbc:mysql://bdrz5e4zqfwwsmuhgyom-mysql.services.clever-cloud.com:3306/bdrz5e4zqfwwsmuhgyom?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String usuario = "ussxjgocro9f0tsf";
    String pass = "xMqMTxruNSFNeCSCnkLX";
    
    public BaseDatos(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(cadenaConexion, usuario, pass);
            transaccion = conexion.createStatement();
        }catch(SQLException e){
            
        }catch(ClassNotFoundException e){
            
        }
    }
    
    public boolean insertar(Persona p){
        String SQL_Insertar = "INSERT INTO `Persona` (`id`, `NOMBRE`, `DOMICILIO`, `TELEFONO`) VALUES (NULL, '%NOM%', '%DOM%', '%TEL%');";
        SQL_Insertar = SQL_Insertar.replace("%NOM%", p.nombre);
        SQL_Insertar= SQL_Insertar.replace("%DOM%", p.domicilio);
        SQL_Insertar = SQL_Insertar.replace("%TEL%", p.telefono);

        try{
            transaccion.execute(SQL_Insertar);
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<Persona> mostrarTodos(){
        ArrayList<Persona> datos = new ArrayList<Persona>();
        String SQL_consulta = "SELECT * FROM `Persona`";
        
        try {
            //RESULTSET = variable que maneja las tuplas resultado
            cursor = transaccion.executeQuery(SQL_consulta);
            
            if(cursor.next()){
                do{
                   Persona p = new Persona(
                           cursor.getInt(1),
                           cursor.getString(2),
                           cursor.getString(3),
                           cursor.getString(4)
                   );
                   datos.add(p);
                }while(cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    
    public Persona obtenerPorID(String IDaBuscar){
        String SQL_consulta = "SELECT * FROM `Persona` WHERE 'ID'="+IDaBuscar;
        
        try {
            //RESULTSET = variable que maneja las tuplas resultado
            cursor = transaccion.executeQuery(SQL_consulta);
            
            if(cursor.next()){
                   Persona p = new Persona(
                           cursor.getInt(1),
                           cursor.getString(2),
                           cursor.getString(3),
                           cursor.getString(4)
                   );
                   return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Persona(-1,"","","");
    }
    
    
    
    public boolean eliminar(String IDaEliminar){
        String SQL_eliminar = "DELETE FROM ´Persona´ WHERE ´ID´= "+IDaEliminar;
        
        try{
        transaccion.execute(SQL_eliminar);
        }catch(SQLException ex){
            return false;
        }
        return true;
    }
    
    public boolean actualizar(Persona p){
        //SU CODIGO ES MUY SIMILAR AL DE INSERTAR QUEDA DE TAREA
        String SQL_actualizar = "UPDATE `Persona` SET =`NOMBRE`='%NOM%', `DOMICILIO`'%DOM%', `TELEFONO`='%TEL%' WHERE `ID`="+p.id;
        SQL_actualizar = SQL_actualizar.replace("%NOM%", p.nombre);
        SQL_actualizar = SQL_actualizar.replace("%DOM%", p.domicilio);
        SQL_actualizar = SQL_actualizar.replace("%TEL%", p.telefono);

        try{
            transaccion.executeUpdate(SQL_actualizar);
        }catch(SQLException e){
            return false;
        }
        return true;
    }
        
    
}
