package com.tienda.managedbean;

import com.tienda.session.EstadoCivilFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import java.util.List;
import com.tienda.entidades.EstadoCivil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

@Named(value = "estadoCivilManagedBean")
@ViewScoped
public class EstadoCivilManagedBean implements Serializable, EstadoCivilInterfaces<EstadoCivil>{

    //paso1
    @EJB
    private EstadoCivilFacadeLocal estadoCivilFacadeLocal;

    //variable de listacargos
    private List<EstadoCivil> ListaEstadoCivil;

    private EstadoCivil estadoCivil;
    /**
     * Creates a new instance of EstadoCivilManagedBean
     */
    public EstadoCivilManagedBean() {
    }
    
    //paso2
    @PostConstruct
    public void init() {
        //lista de los cargos que estan en la BDD
        ListaEstadoCivil = estadoCivilFacadeLocal.findAll();
    }

    @Override
    public void nuevo() {
        estadoCivil = new EstadoCivil();
    }

    @Override
    public void grabar() {
        try {
            if (estadoCivil.getNombre().equals("")) {
                System.out.println("Estado Civil Vacio");
                mostrarMensajeTry("NO INGRESO NOMBRE", FacesMessage.SEVERITY_ERROR);
            } else {
                if (estadoCivil.getCodigoEc()== null) {
                    estadoCivilFacadeLocal.create(estadoCivil);
                    estadoCivil = null;
                } else {
                    estadoCivilFacadeLocal.edit(estadoCivil);
                    estadoCivil = null;
                }
            }
            ListaEstadoCivil = estadoCivilFacadeLocal.findAll();
            mostrarMensajeTry("INFORMACIÓN EXITOSA", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
           mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
              }

    @Override
    public void seleccionar(EstadoCivil e) {
        estadoCivil = e;
    }

    @Override
    public void eliminar(EstadoCivil c) {
        try {
            estadoCivilFacadeLocal.remove(c);
            ListaEstadoCivil = estadoCivilFacadeLocal.findAll();
            mostrarMensajeTry("ELIMIADO EXITOSAMENTE", FacesMessage.SEVERITY_INFO);

        } catch (Exception e) {
            mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        } }

    @Override
    public void cancelar() {
        System.out.println("CANCELAR");
        ListaEstadoCivil = estadoCivilFacadeLocal.findAll();
        estadoCivil = null;  }
    
    public List<EstadoCivil> getListaEstadoCivil() {
        return ListaEstadoCivil;
    }

    public void setListaEstadoCivil(List<EstadoCivil> ListaEstadoCivil) {
        this.ListaEstadoCivil = ListaEstadoCivil;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
}
