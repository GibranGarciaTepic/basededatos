/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tap_u4_ejercicio1_mysqlbasico;

public class Persona {
    int id;
    String nombre;
    String domicilio;
    String telefono;
    
    public Persona(int i, String n, String d, String t){
        id = i;
        nombre = n;
        domicilio = d;
        telefono = t;
    }   
    public String[] toReglon(){
        String[] vector = new String[4];
        vector[0]=""+id;
        vector[1]=nombre;
        vector[2]=domicilio;
        vector[3]=telefono;
        
        return vector;
    }
            
}
