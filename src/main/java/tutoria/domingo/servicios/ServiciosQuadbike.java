/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutoria.domingo.modelo.Quadbike;
import tutoria.domingo.repositorio.RepositorioQuadbike;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosQuadbike {
     @Autowired
    private RepositorioQuadbike metodosCrud;
    
    public List<Quadbike> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Quadbike> getQuadbikes(int idQuadbikes){
        return metodosCrud.getQuadbikes(idQuadbikes);
    }
    
    public Quadbike save(Quadbike quadbikes){
        if(quadbikes.getId()==null){
            return metodosCrud.save(quadbikes);
        }else{
            Optional<Quadbike> evt=metodosCrud.getQuadbikes(quadbikes.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(quadbikes);
            }else{
                return quadbikes;
            }
        }
    }
    public Quadbike update(Quadbike quadbikes){
        if(quadbikes.getId()!=null){
            Optional<Quadbike> e=metodosCrud.getQuadbikes(quadbikes.getId());
            if(!e.isEmpty()){
                if(quadbikes.getName()!=null){
                    e.get().setName(quadbikes.getName());
                }
                if(quadbikes.getBrand()!=null){
                    e.get().setBrand(quadbikes.getBrand());
                }
                if(quadbikes.getYear()!=null){
                    e.get().setYear(quadbikes.getYear());
                }
                if(quadbikes.getDescription()!=null){
                    e.get().setDescription(quadbikes.getDescription());
                }
                if(quadbikes.getCategory()!=null){
                    e.get().setCategory(quadbikes.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return quadbikes;
            }
        }else{
            return quadbikes;
        }
    }


    public boolean deleteQuadbikes(int quadbikesId) {
        Boolean aBoolean = getQuadbikes(quadbikesId).map(quadbikes -> {
            metodosCrud.delete(quadbikes);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
