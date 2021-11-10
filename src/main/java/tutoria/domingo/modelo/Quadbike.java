/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DANIA PRADO NAVIA
 */
@Entity
@Table(name = "quadbike")
public class Quadbike implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String  brand;
    private Integer year;    
    private String description;
    
    @ManyToOne
    @JoinColumn(name="categoryid")
    @JsonIgnoreProperties("quadbikes")
    private Categoria category;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "quadbike")
    @JsonIgnoreProperties({"quadbike","client"})
    private List<Mensaje> messages;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "quadbike")
    @JsonIgnoreProperties({"quadbike","message"})
    public List<Reservacion> reservations;

    /**
     * este es para obtener id
     * @return
     */
    public Integer getId() {
        return id;
    }
    /**
     * es para enviar la id 
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * es para enviar el nombre
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * este es para enviar el nombre
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * es para obtener la marca
     * @return
     */
    public String getBrand() {
        return brand;
    }
    /**
     * es para enviar la marca
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     * es para obtener el año
     * @return
     */
    public Integer getYear() {
        return year;
    }
    /**
     * es para enviar el año
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }
    /**
     * es para obtener la descripcion 
     * @return
     */
    public String getDescription() {
        return description;
    }
    /**
     * es para envia la descripcion
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * es para obtener la categoria
     * @return
     */
    public Categoria getCategory() {
        return category;
    }
    /**
     * es para enviar la categoria
     * @param category
     */
    public void setCategory(Categoria category) {
        this.category = category;
    }
    /**
     * es para obtener el mensaje
     * @return
     */
    public List<Mensaje> getMessages() {
        return messages;
    }
    /**
     * es para enviar el mensaje
     * @param messages
     */
    public void setMessages(List<Mensaje> messages) {
        this.messages = messages;
    }
    /**
     * es para obtener la reservacion
     * @return
     */
    public List<Reservacion> getReservations() {
        return reservations;
    }
    /**
     * es para la reservacion
     * @param reservations
     */
    public void setReservations(List<Reservacion> reservations) {
        this.reservations = reservations;
    }
    
    
    
    
    
}
