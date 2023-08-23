import React from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { useForm } from "react-hook-form";
import { useEffect } from 'react';
import UserService from '../services/UserService';

export const FormUser = () => {

  const date = new Date().getFullYear()

  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm();

  const navigate = useNavigate()


  const onSubmit = (data) => {
      const user = {
      nombre = data.nombre,
      email = data.email,
      password = data.password,
      dni = data.dni,
      rol = data.rol
  }
    UserService.createUsers(user)
      .then(
        navigate("/usuarios")
      )
  }

  return (
    <div className='container mt-4'>
      <div className='row justify-content-center text-center'>
        <div className="col-md-4">
          <form onSubmit={handleSubmit(onSubmit)}>
            <p className="h3 mb-3 fw-normal">Registrarse</p>

            <div className="form-floating mb-2">
              <input type="text" className="form-control" id="floatingInputName" placeholder=""
                {...register("nombre", {
                  required: {
                    value: true,
                    message: "El nombre es obligatorio",
                  },
                  minLength: {
                    value: 2,
                    message: "El nombre debe tener al menos 2 caracteres",
                  },
                })}
              />
              {errors.nombre && <div className='alert alert-danger mt-2 py-2'>{errors.nombre.message}</div>}
              <label htmlFor="floatingInputName">Nombre</label>
            </div>



            <div className="form-floating mb-3">
              <input type="email" className="form-control" id="floatingInputEmail" placeholder=""
                {...register("email", {
                  required: {
                    value: true,
                    message: "El email es obligatorio",
                  },
                  pattern: {
                    value: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
                    message: "Email inválido",
                  },
                })}
              />
              {errors.email && <div className='alert alert-danger mt-2 py-2'>{errors.email.message}</div>}
              <label htmlFor="floatingInputEmail">Email</label>
            </div>

            <div className="form-floating mb-3">
              <input type="password" className="form-control" id="floatingPassword" placeholder="Password"
                {...register("password", {
                  required: {
                    value: true,
                    message: "Ingresar contraseña",
                  },
                })}
              />
              {errors.password && <div className='alert alert-danger mt-2 py-2'>{errors.password.message}</div>}
              <label htmlFor="floatingPassword">Contraseña</label>
            </div>

            <div className="form-floating mb-3">
              <input type="password" className="form-control" id="confirmarPassword" placeholder="Password"
                {...register("confirmarPassword", {
                  required: {
                    value: true,
                    message: "Confirmar contraseña",
                  },
                  validate: (value) =>
                    value == watch("password") || "Las contraseñas no coinciden",
                })}
              />
              {errors.confirmarPassword && (
                <div className='alert alert-danger mt-2 py-2'>{errors.confirmarPassword.message}</div>)}
              <label htmlFor="confirmarPassword">Confirmar contraseña</label>
            </div>

            <div className="form-floating mb-2">
              <input type="text" className="form-control" id="floatingInputDni" placeholder=""
                {...register("dni", {
                  required: {
                    value: true,
                    message: "El DNI es obligatorio",
                  },
                  minLength: {
                    value: 7,
                    message: "El tamaño minimo es de 7 numeros",
                  },
                })}
              />
              {errors.dni && <div className='alert alert-danger mt-2 py-2'>{errors.dni.message}</div>}
              <label htmlFor="floatingInputDni">Dni</label>
            </div>

            <div className="form-floating mb-3">
              <select {...register("rol")} className="form-select" id="rol  " aria-label="Floating label select example">
                <option value="CLIENT">Cliente</option>
                <option value="ENTE">Propietario</option>
              </select>
              <label htmlFor="rol">Tipo de usuario</label>
            </div>


            <div className='d-grid gap-2 mt-4'>
              <button className="btn btn-outline-primary py-2" type="submit" value='Save'>Registrar</button>
              <Link to={'/'} className='btn btn-outline-secondary py-2 me-1'>
                Cancelar
              </Link>
            </div>

            <hr className='featurette-divider' />

            <p className="mb-3 fs-6 fw-normal">¿Ya tienes una cuenta?
              <span> <Link to={"/ingresar"} className='link-underline link-underline-opacity-0'>Inicia sesión</Link></span></p>


            <p className="mt-4 mb-4 text-body-secondary">&copy; {date}</p>
          </form>
        </div>
      </div>

    </div>
  )
}