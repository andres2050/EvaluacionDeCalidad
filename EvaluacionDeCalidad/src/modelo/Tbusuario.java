/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tbusuario")
@NamedQueries({
    @NamedQuery(name = "Tbusuario.findAll", query = "SELECT t FROM Tbusuario t"),
    @NamedQuery(name = "Tbusuario.findByCodigo", query = "SELECT t FROM Tbusuario t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tbusuario.findByNombre", query = "SELECT t FROM Tbusuario t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tbusuario.findByApellido", query = "SELECT t FROM Tbusuario t WHERE t.apellido = :apellido"),
    @NamedQuery(name = "Tbusuario.findByDireccion", query = "SELECT t FROM Tbusuario t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Tbusuario.findByEmail", query = "SELECT t FROM Tbusuario t WHERE t.email = :email"),
    @NamedQuery(name = "Tbusuario.findByTelefono", query = "SELECT t FROM Tbusuario t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Tbusuario.findByContrasena", query = "SELECT t FROM Tbusuario t WHERE t.contrasena = :contrasena")})
public class Tbusuario implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigousuario")
    private Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigousuario")
    private Collection<Tbencuestasxusuario> tbencuestasxusuarioCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private BigDecimal codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "contrasena")
    private String contrasena;
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    @ManyToOne
    private Tbtipo idtipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigousuario")
    private Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollection;

    public Tbusuario() {
    }

    public Tbusuario(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Tbusuario(BigDecimal codigo, String nombre, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Tbtipo getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Tbtipo idtipo) {
        this.idtipo = idtipo;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbusuario)) {
            return false;
        }
        Tbusuario other = (Tbusuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tbusuario[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<Tbusuarioxgrupoxasignatura> getTbusuarioxgrupoxasignaturaCollection() {
        return tbusuarioxgrupoxasignaturaCollection;
    }

    public void setTbusuarioxgrupoxasignaturaCollection(Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollection) {
        this.tbusuarioxgrupoxasignaturaCollection = tbusuarioxgrupoxasignaturaCollection;
    }

    @XmlTransient
    public Collection<Tbencuestasxusuario> getTbencuestasxusuarioCollection() {
        return tbencuestasxusuarioCollection;
    }

    public void setTbencuestasxusuarioCollection(Collection<Tbencuestasxusuario> tbencuestasxusuarioCollection) {
        this.tbencuestasxusuarioCollection = tbencuestasxusuarioCollection;
    }
    
}
