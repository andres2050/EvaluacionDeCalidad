/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbprofesor")
@NamedQueries({
    @NamedQuery(name = "Tbprofesor.findAll", query = "SELECT t FROM Tbprofesor t"),
    @NamedQuery(name = "Tbprofesor.findByCedula", query = "SELECT t FROM Tbprofesor t WHERE t.cedula = :cedula"),
    @NamedQuery(name = "Tbprofesor.findByNombre", query = "SELECT t FROM Tbprofesor t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tbprofesor.findByApellido", query = "SELECT t FROM Tbprofesor t WHERE t.apellido = :apellido"),
    @NamedQuery(name = "Tbprofesor.findByDireccion", query = "SELECT t FROM Tbprofesor t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Tbprofesor.findByEmail", query = "SELECT t FROM Tbprofesor t WHERE t.email = :email"),
    @NamedQuery(name = "Tbprofesor.findByTelefono", query = "SELECT t FROM Tbprofesor t WHERE t.telefono = :telefono")})
public class Tbprofesor implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "cedula")
    private BigDecimal cedula;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private Integer telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedula")
    private Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollection;

    public Tbprofesor() {
    }

    public Tbprofesor(BigDecimal cedula) {
        this.cedula = cedula;
    }

    public Tbprofesor(BigDecimal cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public BigDecimal getCedula() {
        return cedula;
    }

    public void setCedula(BigDecimal cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Collection<Tbgrupoxasignaturaxprofesor> getTbgrupoxasignaturaxprofesorCollection() {
        return tbgrupoxasignaturaxprofesorCollection;
    }

    public void setTbgrupoxasignaturaxprofesorCollection(Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollection) {
        this.tbgrupoxasignaturaxprofesorCollection = tbgrupoxasignaturaxprofesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbprofesor)) {
            return false;
        }
        Tbprofesor other = (Tbprofesor) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbprofesor[ cedula=" + cedula + " ]";
    }
    
}
