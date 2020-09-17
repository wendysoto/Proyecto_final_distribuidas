/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "estado_civil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCivil.findAll", query = "SELECT e FROM EstadoCivil e")
    , @NamedQuery(name = "EstadoCivil.findByCodigoEc", query = "SELECT e FROM EstadoCivil e WHERE e.codigoEc = :codigoEc")
    , @NamedQuery(name = "EstadoCivil.findByNombre", query = "SELECT e FROM EstadoCivil e WHERE e.nombre = :nombre")})
public class EstadoCivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_ec")
    private Integer codigoEc;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "codigoEc")
    private List<Empleado> empleadoList;

    public EstadoCivil() {
    }

    public EstadoCivil(Integer codigoEc) {
        this.codigoEc = codigoEc;
    }

    public Integer getCodigoEc() {
        return codigoEc;
    }

    public void setCodigoEc(Integer codigoEc) {
        this.codigoEc = codigoEc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEc != null ? codigoEc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivil)) {
            return false;
        }
        EstadoCivil other = (EstadoCivil) object;
        if ((this.codigoEc == null && other.codigoEc != null) || (this.codigoEc != null && !this.codigoEc.equals(other.codigoEc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tienda.entidades.EstadoCivil[ codigoEc=" + codigoEc + " ]";
    }
    
}
