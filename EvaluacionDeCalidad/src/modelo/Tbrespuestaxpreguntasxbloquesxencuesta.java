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
@Table(name = "tbrespuestaxpreguntasxbloquesxencuesta")
@NamedQueries({
    @NamedQuery(name = "Tbrespuestaxpreguntasxbloquesxencuesta.findAll", query = "SELECT t FROM Tbrespuestaxpreguntasxbloquesxencuesta t"),
    @NamedQuery(name = "Tbrespuestaxpreguntasxbloquesxencuesta.findById", query = "SELECT t FROM Tbrespuestaxpreguntasxbloquesxencuesta t WHERE t.id = :id"),
    @NamedQuery(name = "Tbrespuestaxpreguntasxbloquesxencuesta.findByFecha", query = "SELECT t FROM Tbrespuestaxpreguntasxbloquesxencuesta t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Tbrespuestaxpreguntasxbloquesxencuesta.findByRespuesta", query = "SELECT t FROM Tbrespuestaxpreguntasxbloquesxencuesta t WHERE t.respuesta = :respuesta")})
public class Tbrespuestaxpreguntasxbloquesxencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "respuesta")
    private int respuesta;
    @JoinColumn(name = "idbloquexencuesta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbbloquesxencuesta idbloquexencuesta;
    @JoinColumn(name = "idbloquexpregunta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbbloquesxpregunta idbloquexpregunta;
    @JoinColumn(name = "idencuestaxasignatura", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbencuestaxasignatura idencuestaxasignatura;
    @JoinColumn(name = "idgrupoxasignaturaxprofesor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor;
    @JoinColumn(name = "codigousuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Tbusuario codigousuario;

    public Tbrespuestaxpreguntasxbloquesxencuesta() {
    }

    public Tbrespuestaxpreguntasxbloquesxencuesta(Integer id) {
        this.id = id;
    }

    public Tbrespuestaxpreguntasxbloquesxencuesta(Integer id, int respuesta) {
        this.id = id;
        this.respuesta = respuesta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public Tbbloquesxencuesta getIdbloquexencuesta() {
        return idbloquexencuesta;
    }

    public void setIdbloquexencuesta(Tbbloquesxencuesta idbloquexencuesta) {
        this.idbloquexencuesta = idbloquexencuesta;
    }

    public Tbbloquesxpregunta getIdbloquexpregunta() {
        return idbloquexpregunta;
    }

    public void setIdbloquexpregunta(Tbbloquesxpregunta idbloquexpregunta) {
        this.idbloquexpregunta = idbloquexpregunta;
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
        if (!(object instanceof Tbrespuestaxpreguntasxbloquesxencuesta)) {
            return false;
        }
        Tbrespuestaxpreguntasxbloquesxencuesta other = (Tbrespuestaxpreguntasxbloquesxencuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbrespuestaxpreguntasxbloquesxencuesta[ id=" + id + " ]";
    }
    
}
