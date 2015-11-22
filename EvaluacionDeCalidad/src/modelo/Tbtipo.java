/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "tbtipo")
@NamedQueries({
    @NamedQuery(name = "Tbtipo.findAll", query = "SELECT t FROM Tbtipo t"),
    @NamedQuery(name = "Tbtipo.findById", query = "SELECT t FROM Tbtipo t WHERE t.id = :id"),
    @NamedQuery(name = "Tbtipo.findByPrivilegios", query = "SELECT t FROM Tbtipo t WHERE t.privilegios = :privilegios")})
public class Tbtipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "privilegios")
    private String privilegios;
    @OneToMany(mappedBy = "idtipo")
    private Collection<Tbusuario> tbusuarioCollection;

    public Tbtipo() {
    }

    public Tbtipo(Integer id) {
        this.id = id;
    }

    public Tbtipo(Integer id, String privilegios) {
        this.id = id;
        this.privilegios = privilegios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }

    public Collection<Tbusuario> getTbusuarioCollection() {
        return tbusuarioCollection;
    }

    public void setTbusuarioCollection(Collection<Tbusuario> tbusuarioCollection) {
        this.tbusuarioCollection = tbusuarioCollection;
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
        if (!(object instanceof Tbtipo)) {
            return false;
        }
        Tbtipo other = (Tbtipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbtipo[ id=" + id + " ]";
    }
    
}
