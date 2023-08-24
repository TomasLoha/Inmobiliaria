package com.inmueble.InmobiliariaSp.servicios;

import com.inmueble.InmobiliariaSp.entidad.User;
import com.inmueble.InmobiliariaSp.enumeraciones.Rol;
import com.inmueble.InmobiliariaSp.excepciones.MiException;
import com.inmueble.InmobiliariaSp.repositorios.UserRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserServicios implements UserDetailsService{

    @Autowired
    private UserRepositorio userRepositorio;

    //metodo para crear un usuario
    @Transactional
    public void ingresar(String nombre,String apellido, String email, String password, String password2, String dni,Rol rol) throws MiException {

        validar(nombre,apellido, email, password, password2, dni);

        User usuario = new User();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setDni(dni);
//        codifico el codigo que recive la funcion
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(rol);

        userRepositorio.save(usuario);
    }

    //En caso de que ocurra algun error el programa no guardara cambios
    @Transactional
    public void modificarNoticia(String id, String nombre,String apellido, String email, String password, String password2, String dni) throws MiException {

        validar(nombre,apellido, email, password, password2, dni);

        //evalua que exista una noticia con ese id
        Optional<User> respuesta = userRepositorio.findById(id);

        //si la respuesta existe se realiza la modificacion
        if (respuesta.isPresent()) {
            User usuario = respuesta.get();

            //se cambia el titulo y el cuerpo
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setDni(dni);

            //guardamos los cambios en el repositorio
            userRepositorio.save(usuario);
        }
    }

    //metodo para validar los datos importados
    
    public void validar(String nombre,String apellido, String email, String password, String password2, String dni) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("No se ha procesado el nombre");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new MiException("No se ha procesado el apellido");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("El email no existe o es nullo");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MiException("La contrasena es incorrecta");
        }
        if (password2 == null || password2.isEmpty() || password.length() <= 5) {
            throw new MiException("La contrasena2 es incorrecta");
        }
        if (dni == null || dni.isEmpty() || dni.length() != 8) {
            throw new MiException("DNI incorrecto");
        }
        if (!password.equals(password2)) {
            throw new MiException("La contrasenas no coinciden");
        }
    }
     
    //metodo listar usuarios
    public List<User> listarUsuarios() {

        return userRepositorio.findAll();
    }

//     //configuracion pre-creada que me permite darle el permiso deseado a un usuario
    @Override
//    // el email sera el "nombre" con el que reprecento el usuario
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userRepositorio.findBy(email);
        
        if(usuario != null){
            
            List <GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); //ROLE_CLIENTS
            
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession session = attr.getRequest().getSession(true);
             
            session.setAttribute("usuariosession", usuario);
            
//            return new User(usuario.getEmail(),usuario.getPassword(),permisos);
        }else{
            return null;
        }
        return null;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
}
