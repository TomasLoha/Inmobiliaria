/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmueble.InmobiliariaSp.repositorio;

import com.inmueble.InmobiliariaSp.entidad.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oscarloha
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<User,String>{
    
    //administra la base de datos donde se almacenan todos los usuarios en general
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public User buscarPorEmail(@Param("email")String email);
    
}