package com.tienda.managedbean;

import com.tienda.entidades.Empleado;
import com.tienda.session.EmpleadoFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author andre
 */
@Named(value = "empleadoManagedBean")
@ViewScoped
public class EmpleadoManagedBean implements Serializable, EmpleadoInterfaces<Empleado>{
    
    //paso1
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;

    //variable de listacargos
    private List<Empleado> ListaEmpleado;

    private Empleado empleado;
    /**
     * Creates a new instance of EstadoCivilManagedBean
     */

    /**
     * Creates a new instance of EmpleadoManagedBean
     */
    public EmpleadoManagedBean() {
    }
    
    //paso2
    @PostConstruct
    public void init() {
        //lista de los cargos que estan en la BDD
        ListaEmpleado = empleadoFacadeLocal.findAll();
    }

    @Override
    public void nuevo() {
        empleado = new Empleado();
    }

    @Override
    public void grabar() {
        try {
            if (empleado.getNombre().equals("")) {
                System.out.println("Empleado Vacio");
                mostrarMensajeTry("NO INGRESO NOMBRE", FacesMessage.SEVERITY_ERROR);
            } else {
                if (empleado.getCodigo()== null) {
                    empleadoFacadeLocal.create(empleado);
                    empleado = null;
                } else {
                    empleadoFacadeLocal.edit(empleado);
                    empleado = null;
                }
            }
            ListaEmpleado = empleadoFacadeLocal.findAll();
            mostrarMensajeTry("INFORMACIÓN EXITOSA", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
           mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Empleado t) {
        empleado = t;
    }

    @Override
    public void eliminar(Empleado t) {
        try {
            empleadoFacadeLocal.remove(t);
            ListaEmpleado = empleadoFacadeLocal.findAll();
            mostrarMensajeTry("ELIMIADO EXITOSAMENTE", FacesMessage.SEVERITY_INFO);

        } catch (Exception e) {
            mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        } }
    
    @Override
    public void cancelar() {
        System.out.println("CANCELAR");
        ListaEmpleado = empleadoFacadeLocal.findAll();
        empleado = null;
    }

    public List<Empleado> getListaEmpleado() {
        return ListaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> ListaEmpleado) {
        this.ListaEmpleado = ListaEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    

    
    
}
