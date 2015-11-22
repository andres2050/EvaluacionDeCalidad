/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbinformes")
@NamedQueries({
    @NamedQuery(name = "Tbinformes.findAll", query = "SELECT t FROM Tbinformes t"),
    @NamedQuery(name = "Tbinformes.findById", query = "SELECT t FROM Tbinformes t WHERE t.id = :id"),
    @NamedQuery(name = "Tbinformes.findByFechacreacion", query = "SELECT t FROM Tbinformes t WHERE t.fechacreacion = :fechacreacion")})
public class Tbinformes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @JoinColumn(name = "idencuestaxasignatura", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbencuestaxasignatura idencuestaxasignatura;
    @JoinColumn(name = "idgrupoxasignaturaxprofesor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor;

    public Tbinformes() {
    }

    public Tbinformes(Integer id) {
        this.id = id;
    }

    public Tbinformes(Integer id, Date fechacreacion) {
        this.id = id;
        this.fechacreacion = fechacreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Tbencuestaxasignatura getIdencuestaxasignatura() {
        return idencuestaxasignatura;
    }

    public void setIdencuestaxasignatura(Tbencuestaxasignatura idencuestaxasignatura) {
        this.idencuestaxasignatura = idencuestaxasignatura;
    }

    public Tbgrupoxasignaturaxprofesor getIdgrupoxasignaturaxprofesor() {
        return idgrupoxasignaturaxprofesor;
    }

    public void setIdgrupoxasignaturaxprofesor(Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor) {
        this.idgrupoxasignaturaxprofesor = idgrupoxasignaturaxprofesor;
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
        if (!(object instanceof Tbinformes)) {
            return false;
        }
        Tbinformes other = (Tbinformes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbinformes[ id=" + id + " ]";
    }
    
}
