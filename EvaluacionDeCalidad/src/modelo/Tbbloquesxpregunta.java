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
@Table(name = "tbbloquesxpregunta")
@NamedQueries({
    @NamedQuery(name = "Tbbloquesxpregunta.findAll", query = "SELECT t FROM Tbbloquesxpregunta t"),
    @NamedQuery(name = "Tbbloquesxpregunta.findById", query = "SELECT t FROM Tbbloquesxpregunta t WHERE t.id = :id")})
public class Tbbloquesxpregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbloquexpregunta")
    private Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollection;
    @JoinColumn(name = "idbloque", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbbloque idbloque;
    @JoinColumn(name = "idpregunta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbpregunta idpregunta;

    public Tbbloquesxpregunta() {
    }

    public Tbbloquesxpregunta(Integer id) {
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

    public Tbpregunta getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Tbpregunta idpregunta) {
        this.idpregunta = idpregunta;
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
        if (!(object instanceof Tbbloquesxpregunta)) {
            return false;
        }
        Tbbloquesxpregunta other = (Tbbloquesxpregunta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbbloquesxpregunta[ id=" + id + " ]";
    }
    
}
