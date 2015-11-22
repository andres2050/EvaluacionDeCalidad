/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbencuestaxasignatura")
@NamedQueries({
    @NamedQuery(name = "Tbencuestaxasignatura.findAll", query = "SELECT t FROM Tbencuestaxasignatura t"),
    @NamedQuery(name = "Tbencuestaxasignatura.findById", query = "SELECT t FROM Tbencuestaxasignatura t WHERE t.id = :id")})
public class Tbencuestaxasignatura implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idencuestaxasignatura")
    private Collection<Tbencuestasxusuario> tbencuestasxusuarioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idencuestaxasignatura")
    private Collection<Tbinformes> tbinformesCollection;
    @JoinColumn(name = "idasignatura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Tbasignatura idasignatura;
    @JoinColumn(name = "idencuesta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbencuesta idencuesta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idencuestaxasignatura")
    private Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollection;

    public Tbencuestaxasignatura() {
    }

    public Tbencuestaxasignatura(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<Tbinformes> getTbinformesCollection() {
        return tbinformesCollection;
    }

    public void setTbinformesCollection(Collection<Tbinformes> tbinformesCollection) {
        this.tbinformesCollection = tbinformesCollection;
    }

    public Tbasignatura getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(Tbasignatura idasignatura) {
        this.idasignatura = idasignatura;
    }

    public Tbencuesta getIdencuesta() {
        return idencuesta;
    }

    public void setIdencuesta(Tbencuesta idencuesta) {
        this.idencuesta = idencuesta;
    }

    public Collection<Tbrespuestaxpreguntasxbloquesxencuesta> getTbrespuestaxpreguntasxbloquesxencuestaCollection() {
        return tbrespuestaxpreguntasxbloquesxencuestaCollection;
    }

    public void setTbrespuestaxpreguntasxbloquesxencuestaCollection(Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollection) {
        this.tbrespuestaxpreguntasxbloquesxencuestaCollection = tbrespuestaxpreguntasxbloquesxencuestaCollection;
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
        if (!(object instanceof Tbencuestaxasignatura)) {
            return false;
        }
        Tbencuestaxasignatura other = (Tbencuestaxasignatura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbencuestaxasignatura[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Tbencuestasxusuario> getTbencuestasxusuarioCollection() {
        return tbencuestasxusuarioCollection;
    }

    public void setTbencuestasxusuarioCollection(Collection<Tbencuestasxusuario> tbencuestasxusuarioCollection) {
        this.tbencuestasxusuarioCollection = tbencuestasxusuarioCollection;
    }
    
}
