
package com.inmueble.InmobiliariaSp.servicios;

import com.inmueble.InmobiliariaSp.entidad.User;
import com.inmueble.InmobiliariaSp.excepciones.MiException;
import com.inmueble.InmobiliariaSp.repositorios.UserRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicio {
    
    @Autowired
    private UserRepositorio userRepositorio;
    
    @Transactional
    public void crearUser(String id, String nombre, String email, String password, int dni) throws MiException {
        validar(id, nombre, email, password, dni);
        User user = new User();
        user.setId(id);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(password);
        user.setDni(dni);
        user.setRol(null);
        userRepositorio.save(user);
    }
    
    @Transactional
    public void modificarUser(String id, String nombre, String email, String password, int dni)throws MiException {
        Optional<User> respuesta = userRepositorio.findById(id);
    }
    
    private void validar(String id, String nombre, String email, String password, int dni) throws MiException {
        if (id.isEmpty() || id==null){
            throw new MiException("Id no puede ser nulo o estar vacio");
        }
        if (nombre.isEmpty() || nombre==null){
            throw new MiException("Nombre no puede ser nulo o estar vacio");
        }
        if (email.isEmpty() || email==null){
            throw new MiException("Email no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password==null){
            throw new MiException("Email no puede ser nulo o estar vacio");
        }
    }
}
