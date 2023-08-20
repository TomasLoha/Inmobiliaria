/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmueble.InmobiliariaSp.servicios;

import com.inmueble.InmobiliariaSp.entidad.User;
import com.inmueble.InmobiliariaSp.excepciones.MiException;
import com.inmueble.InmobiliariaSp.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author oscarloha
 */
@Service
//implements UserDetailsService
public class UserServicios  {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    
    //metodo para crear un usuario
    @Transactional
    public void ingresar(String nombre, String email, String password, String password2, String dni) throws MiException{
        
        validar(nombre,email,password,password2,dni);
        
        User usuario = new User();
        
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(email);
        usuario.setDni(email);
//        codifico el codigo que recive la funcion
//        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
//        usuario.setRol(Rol.USER);
        
        usuarioRepositorio.save(usuario);
    }
    
    //metodo para validar los datos importados
     public void validar(String nombre, String email, String password, String password2,String dni) throws MiException{
        
        if(nombre == null || nombre.isEmpty()){
            throw new MiException("No se a procesado el nombre");
        }
        if(email == null || email.isEmpty()){
            throw new MiException("El email no existe o es nullo");
        }
        if(password == null || password.isEmpty() || password.length() <= 5){
            throw new MiException("La contrasena es incorrecta");
        }
        if(password2 == null || password2.isEmpty() || password.length() <= 5){
            throw new MiException("La contrasena2 es incorrecta");
        }
        if(dni == null || dni.isEmpty() || dni.length() != 8){
            throw new MiException("DNI incorrecto");
        }
        if(!password.equals(password2)){
            throw new MiException("La contrasenas no coinciden");
        }
        
        
    }    
     
     
     //metodo listar usuarios
     public List<User> listarUsuarios(){

        List<User> user = new ArrayList();
        user = usuarioRepositorio.findAll();
        
        //Devuelve una lista con todas las noticias
        return (user);
    }

//     //configuracion pre-creada que me permite darle el permiso deseado a un usuario
//    @Override
//    // el email sera el "nombre" con el que reprecento el usuario
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User usuario = usuarioRepositorio.buscarPorEmail(email);
//        
//        if(usuario != null){
//            
//            List <GrantedAuthority> permisos = new ArrayList();
//            
//            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); //ROLE_USER
//            
//            permisos.add(p);
//            
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            
//            HttpSession session = attr.getRequest().getSession(true);
//             
//            session.setAttribute("usuariosession", usuario);
//            
//            return new User(usuario.getEmail(),usuario.getPassword(),permisos);
//        }else{
//            return null;
//        }
//    }
}
