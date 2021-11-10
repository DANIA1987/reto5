/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutoria.domingo.modelo.Reservacion;
import tutoria.domingo.reportes.ContadorClientes;
import tutoria.domingo.reportes.StatusReservas;
import tutoria.domingo.repositorio.RepositorioReservacion;

/**
 *
 * @author DANIA PRADO NAVIA
 */
@Service
public class ServiciosReservacion {
         @Autowired
    private RepositorioReservacion metodosCrud;
    /**
     * este consulta todos los datos de la tabla reservacion 
     * @return
     */
    public List<Reservacion> getAll(){
        return metodosCrud.getAll();
    }
    /**
     * es para obtener una reservacion por ID 
     * @param reservationId
     * @return
     */
    public Optional<Reservacion> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }
    /**
     * metodo para guardar una reservacion
     * @param reservation
     * @return
     */
    public Reservacion save(Reservacion reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservacion> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    /**
     * metodo que actualiza una reservacion
     * @param reservacion
     * @return
     */
    public Reservacion update(Reservacion reservacion){
        if(reservacion.getIdReservation()!=null){
            Optional<Reservacion> e= metodosCrud.getReservation(reservacion.getIdReservation());
            if(!e.isEmpty()){

                if(reservacion.getStartDate()!=null){
                    e.get().setStartDate(reservacion.getStartDate());
                }
                if(reservacion.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if(reservacion.getStatus()!=null){
                    e.get().setStatus(reservacion.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservacion;
            }
        }else{
            return reservacion;
        }
    }
    /**
     * metodo que borra una reservacion
     * @param reservationId
     * @return
     */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public StatusReservas reporteStatusServicio (){
        List<Reservacion>completed= metodosCrud.ReservacionStatusRepositorio("completed");
        List<Reservacion>cancelled= metodosCrud.ReservacionStatusRepositorio("cancelled");
        
        return new StatusReservas(completed.size(), cancelled.size() );
    }

    public List<Reservacion> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    }

    public List<ContadorClientes> reporteClientesServicio(){
        return metodosCrud.getClientesRepositorio();
    } 
}
