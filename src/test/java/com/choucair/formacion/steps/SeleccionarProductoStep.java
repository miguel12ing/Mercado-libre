package com.choucair.formacion.steps;

import au.com.bytecode.opencsv.CSVReader;
import com.choucair.formacion.pageobjects.SeleccionarProductoPage;
import net.thucydides.core.annotations.Step;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class SeleccionarProductoStep {

    SeleccionarProductoPage seleccionarProductoPage;

    String[] datos;

    public void leerCSV(String id) {
        au.com.bytecode.opencsv.CSVReader reader;
        try {
            reader = new CSVReader(new FileReader("src/test/resources/Datadriven/badeDatos.csv"));

            String[] fila;
            while ((fila = reader.readNext()) != null) {
                Logger.getLogger(fila[0]);
                if (id.equals(fila[0].trim())) {
                    datos = fila;
                }
            }
            reader.close();
        } catch (IOException e) {
            Logger.getLogger("" + e);
        }
    }

    @Step
    public void abrirnavegador() {
        seleccionarProductoPage.open();
        //seleccionarProductoPage.cerrarNavegador();

    }

    public void seleccionListTipoDeporte(String id) {
        leerCSV(id);

        seleccionarProductoPage.seleccionarTipoDeporte();
        seleccionarProductoPage.seleccionarUbicacion();
        seleccionarProductoPage.ingresarRangoPrecios(datos[2],datos[3]);
    }


    public void seleccionCasoProducto(String id) {
        leerCSV(id);

        seleccionarProductoPage.seleccionarNumeroProducto(datos[4]);
        seleccionarProductoPage.verDatosDelVendedor();
    }


    public void obtieneReportes() {

        seleccionarProductoPage.obtieneReportesConsola();
    }
}


