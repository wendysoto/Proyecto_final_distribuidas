/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managedbean;

import com.tienda.entidades.Empleado;
import com.tienda.entidades.Proveedor;
import com.tienda.session.EmpleadoFacadeLocal;
import com.tienda.session.ProveedorFacadeLocal;
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
@Named(value = "proveedorManagedBean")
@ViewScoped
public class ProveedorManagedBean implements Serializable, ProveedorInterfaces<Proveedor> {
//paso 1
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;

    //variable de listacargos
    private List<Proveedor> ListaProveedor;

    private Proveedor proveedor;
    
    public ProveedorManagedBean() {
    }
    //paso2
    @PostConstruct
    public void init() {
        //lista de los cargos que estan en la BDD
        ListaProveedor = proveedorFacadeLocal.findAll();
    }

    @Override
    public void nuevo() {
       proveedor = new Proveedor();
    }

    @Override
    public void grabar() {
           try {
            if (proveedor.getNombre().equals("")) {
                System.out.println("Empleado Vacio");
                mostrarMensajeTry("NO INGRESO NOMBRE", FacesMessage.SEVERITY_ERROR);
            } else {
                if (proveedor.getCodigo()== null) {
                    proveedorFacadeLocal.create(proveedor);
                    proveedor = null;
                } else {
                    proveedorFacadeLocal.edit(proveedor);
                    proveedor = null;
                }
            }
            ListaProveedor = proveedorFacadeLocal.findAll();
            mostrarMensajeTry("INFORMACIÓN EXITOSA", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
           mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
        
    }

    @Override
    public void seleccionar(Proveedor t) {
         proveedor = t;
        
    }

    @Override
    public void eliminar(Proveedor t) {
        try {
            proveedorFacadeLocal.remove(t);
            ListaProveedor = proveedorFacadeLocal.findAll();
            mostrarMensajeTry("ELIMIADO EXITOSAMENTE", FacesMessage.SEVERITY_INFO);

        } catch (Exception e) {
            mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
         System.out.println("CANCELAR");
        ListaProveedor = proveedorFacadeLocal.findAll();
        proveedor = null;
    }

    public List<Proveedor> getListaProveedor() {
        return ListaProveedor;
    }

    public void setListaProveedor(List<Proveedor> ListaProveedor) {
        this.ListaProveedor = ListaProveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}
