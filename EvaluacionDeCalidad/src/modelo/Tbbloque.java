/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbbloque")
@NamedQueries({
    @NamedQuery(name = "Tbbloque.findAll", query = "SELECT t FROM Tbbloque t"),
    @NamedQuery(name = "Tbbloque.findById", query = "SELECT t FROM Tbbloque t WHERE t.id = :id"),
    @NamedQuery(name = "Tbbloque.findByNombre", query = "SELECT t FROM Tbbloque t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tbbloque.findByFechacreacion", query = "SELECT t FROM Tbbloque t WHERE t.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Tbbloque.findByFechamodificacion", query = "SELECT t FROM Tbbloque t WHERE t.fechamodificacion = :fechamodificacion")})
public class Tbbloque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.DATE)
    private Date fechamodificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbloque")
    private Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbloque")
    private Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollection;

    public Tbbloque() {
    }

    public Tbbloque(Integer id) {
        this.id = id;
    }

    public Tbbloque(Integer id, String nombre, Date fechacreacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechacreacion = fechacreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public Collection<Tbbloquesxpregunta> getTbbloquesxpreguntaCollection() {
        return tbbloquesxpreguntaCollection;
    }

    public void setTbbloquesxpreguntaCollection(Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollection) {
        this.tbbloquesxpreguntaCollection = tbbloquesxpreguntaCollection;
    }

    public Collection<Tbbloquesxencuesta> getTbbloquesxencuestaCollection() {
        return tbbloquesxencuestaCollection;
    }

    public void setTbbloquesxencuestaCollection(Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollection) {
        this.tbbloquesxencuestaCollection = tbbloquesxencuestaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbbloque)) {
            return false;
        }
        Tbbloque other = (Tbbloque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbbloque[ id=" + id + " ]";
    }
    
}
