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

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbbloquesxencuesta")
@NamedQueries({
    @NamedQuery(name = "Tbbloquesxencuesta.findAll", query = "SELECT t FROM Tbbloquesxencuesta t"),
    @NamedQuery(name = "Tbbloquesxencuesta.findById", query = "SELECT t FROM Tbbloquesxencuesta t WHERE t.id = :id")})
public class Tbbloquesxencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbloquexencuesta")
    private Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollection;
    @JoinColumn(name = "idbloque", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbbloque idbloque;
    @JoinColumn(name = "idencuesta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbencuesta idencuesta;

    public Tbbloquesxencuesta() {
    }

    public Tbbloquesxencuesta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<Tbrespuestaxpreguntasxbloquesxencuesta> getTbrespuestaxpreguntasxbloquesxencuestaCollection() {
        return tbrespuestaxpreguntasxbloquesxencuestaCollection;
    }

    public void setTbrespuestaxpreguntasxbloquesxencuestaCollection(Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollection) {
        this.tbrespuestaxpreguntasxbloquesxencuestaCollection = tbrespuestaxpreguntasxbloquesxencuestaCollection;
    }

    public Tbbloque getIdbloque() {
        return idbloque;
    }

    public void setIdbloque(Tbbloque idbloque) {
        this.idbloque = idbloque;
    }

    public Tbencuesta getIdencuesta() {
        return idencuesta;
    }

    public void setIdencuesta(Tbencuesta idencuesta) {
        this.idencuesta = idencuesta;
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
        if (!(object instanceof Tbbloquesxencuesta)) {
            return false;
        }
        Tbbloquesxencuesta other = (Tbbloquesxencuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbbloquesxencuesta[ id=" + id + " ]";
    }
    
}
