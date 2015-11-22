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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbasignatura")
@NamedQueries({
    @NamedQuery(name = "Tbasignatura.findAll", query = "SELECT t FROM Tbasignatura t"),
    @NamedQuery(name = "Tbasignatura.findByCodigo", query = "SELECT t FROM Tbasignatura t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tbasignatura.findByNombre", query = "SELECT t FROM Tbasignatura t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tbasignatura.findByFechacreacion", query = "SELECT t FROM Tbasignatura t WHERE t.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Tbasignatura.findByFechamodificacion", query = "SELECT t FROM Tbasignatura t WHERE t.fechamodificacion = :fechamodificacion")})
public class Tbasignatura implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoasignatura")
    private Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idasignatura")
    private Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idasignatura")
    private Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollection;

    public Tbasignatura() {
    }

    public Tbasignatura(String codigo) {
        this.codigo = codigo;
    }

    public Tbasignatura(String codigo, String nombre, Date fechacreacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechacreacion = fechacreacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Collection<Tbencuestaxasignatura> getTbencuestaxasignaturaCollection() {
        return tbencuestaxasignaturaCollection;
    }

    public void setTbencuestaxasignaturaCollection(Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollection) {
        this.tbencuestaxasignaturaCollection = tbencuestaxasignaturaCollection;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbasignatura)) {
            return false;
        }
        Tbasignatura other = (Tbasignatura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbasignatura[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<Tbusuarioxgrupoxasignatura> getTbusuarioxgrupoxasignaturaCollection() {
        return tbusuarioxgrupoxasignaturaCollection;
    }

    public void setTbusuarioxgrupoxasignaturaCollection(Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollection) {
        this.tbusuarioxgrupoxasignaturaCollection = tbusuarioxgrupoxasignaturaCollection;
    }
    
}
