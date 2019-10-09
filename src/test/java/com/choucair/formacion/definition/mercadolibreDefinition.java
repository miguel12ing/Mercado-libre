package com.choucair.formacion.definition;

import com.choucair.formacion.steps.SeleccionarProductoStep;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.thucydides.core.annotations.Steps;

public class mercadolibreDefinition {
    @Steps
    SeleccionarProductoStep seleccionarProductoStep;

    String casoPrueba;
    String casoPrueba2;
    @Dado("^que necesito entrar a la categoria del producto por menor precio para el parametro \"([^\"]*)\"$")
    public void que_necesito_entrar_a_la_categoria_del_producto_por_menor_precio_para_el_parametro(String id) {

        this.casoPrueba = id;

    seleccionarProductoStep.abrirnavegador();
    seleccionarProductoStep.seleccionListTipoDeporte("001");
    }

    @Cuando("^entro al producto para el parametro \"([^\"]*)\"$")
    public void entro_al_producto_para_el_parametro(String id){

        this.casoPrueba2 = id;

     seleccionarProductoStep.seleccionCasoProducto("002");

    }

    @Entonces("^imprimo reportes$")
    public void imprimo_reportes() {

        seleccionarProductoStep.obtieneReportes();

    }

}

