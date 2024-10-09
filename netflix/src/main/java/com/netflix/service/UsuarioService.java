package com.netflix.service;

import com.netflix.entity.Plan;
import com.netflix.entity.Usuario;
import com.netflix.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public Optional<Usuario> crearUsuario(String correo, String contrasenia, String tarjeta, Plan plan) {
        if(usuarioRepository.existsByCorreo(correo)){
            return Optional.empty();
        }else {
            return Optional.of(usuarioRepository.save(Usuario.builder()
                            .correo(correo)
                            .contrasenia(contrasenia)
                            .perfiles(new ArrayList<>())
                            .tarjeta(tarjeta)
                            .plan(plan)
                    .build()));
        }
    }

    public Usuario login(String correo, String contrasenia) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
        if(usuario.isEmpty()){
            throw new Exception("El correo no se encuentra registrado");
        } else if (!usuario.get().getContrasenia().equals(contrasenia)) {
            throw new Exception("La contraseña es incorrecta");
        }else {
            return usuario.get();
        }
    }

    public Usuario traerPorId(int id){
        return usuarioRepository.findById(id).orElseThrow();
    }

    public int actualizarUsuario(int id,String correo,String contrasenia,String tarjeta, Plan plan){
        Usuario usuario = this.traerPorId(id);

        usuario.setCorreo(correo);
        usuario.setContrasenia(contrasenia);
        usuario.setTarjeta(tarjeta);
        usuario.setPlan(plan);

        return usuarioRepository.save(usuario).getIdUsuario();
    }

    public void eliminarUsuario(int id){
        usuarioRepository.deleteById(id);
    }
}
