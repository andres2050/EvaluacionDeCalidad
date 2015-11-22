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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "tbencuestasxusuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbencuestasxusuario.findAll", query = "SELECT t FROM Tbencuestasxusuario t"),
    @NamedQuery(name = "Tbencuestasxusuario.findById", query = "SELECT t FROM Tbencuestasxusuario t WHERE t.id = :id"),
    @NamedQuery(name = "Tbencuestasxusuario.findByEstado", query = "SELECT t FROM Tbencuestasxusuario t WHERE t.estado = :estado"),
    @NamedQuery(name = "Tbencuestasxusuario.findByFecha", query = "SELECT t FROM Tbencuestasxusuario t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Tbencuestasxusuario.findByComentario", query = "SELECT t FROM Tbencuestasxusuario t WHERE t.comentario = :comentario")})
public class Tbencuestasxusuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "idencuestaxasignatura", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbencuestaxasignatura idencuestaxasignatura;
    @JoinColumn(name = "idgrupoxasignaturaxprofesor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor;
    @JoinColumn(name = "codigousuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Tbusuario codigousuario;

    public Tbencuestasxusuario() {
    }

    public Tbencuestasxusuario(Integer id) {
        this.id = id;
    }

    public Tbencuestasxusuario(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        if (!(object instanceof Tbencuestasxusuario)) {
            return false;
        }
        Tbencuestasxusuario other = (Tbencuestasxusuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbencuestasxusuario[ id=" + id + " ]";
    }
    
}
