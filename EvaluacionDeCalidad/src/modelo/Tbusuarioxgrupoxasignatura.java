/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbusuarioxgrupoxasignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbusuarioxgrupoxasignatura.findAll", query = "SELECT t FROM Tbusuarioxgrupoxasignatura t"),
    @NamedQuery(name = "Tbusuarioxgrupoxasignatura.findById", query = "SELECT t FROM Tbusuarioxgrupoxasignatura t WHERE t.id = :id")})
public class Tbusuarioxgrupoxasignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "codigoasignatura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Tbasignatura codigoasignatura;
    @JoinColumn(name = "idgrupo", referencedColumnName = "identificacion")
    @ManyToOne(optional = false)
    private Tbgrupo idgrupo;
    @JoinColumn(name = "codigousuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Tbusuario codigousuario;

    public Tbusuarioxgrupoxasignatura() {
    }

    public Tbusuarioxgrupoxasignatura(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tbasignatura getCodigoasignatura() {
        return codigoasignatura;
    }

    public void setCodigoasignatura(Tbasignatura codigoasignatura) {
        this.codigoasignatura = codigoasignatura;
    }

    public Tbgrupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Tbgrupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Tbusuario getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Tbusuario codigousuario) {
        this.codigousuario = codigousuario;
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
        if (!(object instanceof Tbusuarioxgrupoxasignatura)) {
            return false;
        }
        Tbusuarioxgrupoxasignatura other = (Tbusuarioxgrupoxasignatura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbusuarioxgrupoxasignatura[ id=" + id + " ]";
    }
    
}
