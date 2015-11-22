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
@Table(name = "tbgrupo")
@NamedQueries({
    @NamedQuery(name = "Tbgrupo.findAll", query = "SELECT t FROM Tbgrupo t"),
    @NamedQuery(name = "Tbgrupo.findByIdentificacion", query = "SELECT t FROM Tbgrupo t WHERE t.identificacion = :identificacion"),
    @NamedQuery(name = "Tbgrupo.findByFechacreacion", query = "SELECT t FROM Tbgrupo t WHERE t.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Tbgrupo.findByFechamodificacion", query = "SELECT t FROM Tbgrupo t WHERE t.fechamodificacion = :fechamodificacion")})
public class Tbgrupo implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
    private Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "identificacion")
    private Integer identificacion;
    @Basic(optional = false)
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.DATE)
    private Date fechamodificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
    private Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollection;

    public Tbgrupo() {
    }

    public Tbgrupo(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public Tbgrupo(Integer identificacion, Date fechacreacion) {
        this.identificacion = identificacion;
        this.fechacreacion = fechacreacion;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
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

    public Collection<Tbgrupoxasignaturaxprofesor> getTbgrupoxasignaturaxprofesorCollection() {
        return tbgrupoxasignaturaxprofesorCollection;
    }

    public void setTbgrupoxasignaturaxprofesorCollection(Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollection) {
        this.tbgrupoxasignaturaxprofesorCollection = tbgrupoxasignaturaxprofesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificacion != null ? identificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbgrupo)) {
            return false;
        }
        Tbgrupo other = (Tbgrupo) object;
        if ((this.identificacion == null && other.identificacion != null) || (this.identificacion != null && !this.identificacion.equals(other.identificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbgrupo[ identificacion=" + identificacion + " ]";
    }

    @XmlTransient
    public Collection<Tbusuarioxgrupoxasignatura> getTbusuarioxgrupoxasignaturaCollection() {
        return tbusuarioxgrupoxasignaturaCollection;
    }

    public void setTbusuarioxgrupoxasignaturaCollection(Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollection) {
        this.tbusuarioxgrupoxasignaturaCollection = tbusuarioxgrupoxasignaturaCollection;
    }
    
}
