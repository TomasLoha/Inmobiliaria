package com.inmueble.InmobiliariaSp.entidad;

import com.inmueble.InmobiliariaSp.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {

    //clase creada para administrar los permisos
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String email;
    private String password;
    private String dni;

    //indica el rol y nivel de permisos que tendra este usuario
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public User() {
    }

    /*
    public User(String id, String nombre, String email, String password, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.dni = dni;
    }
     */
    public User(String nombre, String email, String password, String dni, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", dni=" + dni + '}';
    }

}
