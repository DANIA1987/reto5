/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tutoria.domingo.interfaces.InterfaceQuadbike;
import tutoria.domingo.modelo.Quadbike;

/**
 *
 * @author USUARIO
 */
@Repository
public class RepositorioQuadbike {
     @Autowired
    private InterfaceQuadbike crud;
    

    public List<Quadbike> getAll(){
        return (List<Quadbike>) crud.findAll();       
    }
    
    public Optional <Quadbike> getQuadbikes(int id){
        return crud.findById(id);
    }
    
    public Quadbike save(Quadbike quadbikes){
        return crud.save(quadbikes);
    }
      public void delete(Quadbike quadbikes){
        crud.delete(quadbikes);
    }
    
    
}
