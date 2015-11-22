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
@Table(name = "tbgrupoxasignaturaxprofesor")
@NamedQueries({
    @NamedQuery(name = "Tbgrupoxasignaturaxprofesor.findAll", query = "SELECT t FROM Tbgrupoxasignaturaxprofesor t"),
    @NamedQuery(name = "Tbgrupoxasignaturaxprofesor.findById", query = "SELECT t FROM Tbgrupoxasignaturaxprofesor t WHERE t.id = :id"),
    @NamedQuery(name = "Tbgrupoxasignaturaxprofesor.findByCupo", query = "SELECT t FROM Tbgrupoxasignaturaxprofesor t WHERE t.cupo = :cupo")})
public class Tbgrupoxasignaturaxprofesor implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupoxasignaturaxprofesor")
    private Collection<Tbencuestasxusuario> tbencuestasxusuarioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cupo")
    private int cupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupoxasignaturaxprofesor")
    private Collection<Tbinformes> tbinformesCollection;
    @JoinColumn(name = "idasignatura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Tbasignatura idasignatura;
    @JoinColumn(name = "idgrupo", referencedColumnName = "identificacion")
    @ManyToOne(optional = false)
    private Tbgrupo idgrupo;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Tbprofesor cedula;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupoxasignaturaxprofesor")
    private Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollection;

    public Tbgrupoxasignaturaxprofesor() {
    }

    public Tbgrupoxasignaturaxprofesor(Integer id) {
        this.id = id;
    }

    public Tbgrupoxasignaturaxprofesor(Integer id, int cupo) {
        this.id = id;
        this.cupo = cupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
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

    public Tbgrupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Tbgrupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Tbprofesor getCedula() {
        return cedula;
    }

    public void setCedula(Tbprofesor cedula) {
        this.cedula = cedula;
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
        if (!(object instanceof Tbgrupoxasignaturaxprofesor)) {
            return false;
        }
        Tbgrupoxasignaturaxprofesor other = (Tbgrupoxasignaturaxprofesor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbgrupoxasignaturaxprofesor[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Tbencuestasxusuario> getTbencuestasxusuarioCollection() {
        return tbencuestasxusuarioCollection;
    }

    public void setTbencuestasxusuarioCollection(Collection<Tbencuestasxusuario> tbencuestasxusuarioCollection) {
        this.tbencuestasxusuarioCollection = tbencuestasxusuarioCollection;
    }
    
}
