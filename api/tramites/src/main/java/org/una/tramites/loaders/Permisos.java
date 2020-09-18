/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.loaders;

/**
 *
 * @author cordo
 */
public enum Permisos {
    USUARIO_CREAR("USU01"),
    USUARIO_MODIFICAR("USU02"),
    USUARIO_INACTIVAR("USU03"),
    USUARIO_CONSULTAR("USU04"),
    USUARIO_CONSULTAR_TODO("USU05"),
    USUARIO_ELIMINAR("USU006"),
    USUARIO_ELIMINAR_TODO("USU07"),
    DEPARTAMENTO_CREAR("DEP01"),
    DEPARTAMENTO_MODIFICAR("DEP02"),
    DEPARTAMENTO_INACTIVAR("DEP03"),
    DEPARTAMENTO_CONSULTAR("DEP04"),
    TRAMITE_REGISTRAR("TRA01"),
    TRAMITE_MODIFICAR("TRA02"),
    TRAMITE_INACTIVAR("TRA03"),
    TRAMITE_FINALIZAR("TRA04"),
    TRAMITE_CONSULTAR("TRA05"),
    TRAMITE_CONSULTAR_TODO("TRA06"),
    TRAMITE_DISEÑAR("TRD01"),
    TRANSACCION_CONSULTAR("TRU01"),
    ALERTAS_FAVORITISMO("TRU02"),
    REGISTRAR_TRAMITE_PROPIO("TAG01"),
    CONSULTAR_TRAMITE_PROPIO("TAG02"),
    REPORTE_TRAMITES_USUARIOS("REP01"),
    REPORTE_TRAMITES_FECHAS("REP02"),
    REPORTE_TRAMITES_TIPOS("REP03"),
    REPORTE_TRAMITES_ESTADOS("REP04"),
    REPORTE_TRAMITES_DEPARTEMENTOS("REP05"),
    REPORTE_TRAMITES_CLIENTE("REP06");

    private String codigo;

    Permisos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
