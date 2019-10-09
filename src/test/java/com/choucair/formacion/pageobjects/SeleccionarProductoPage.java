package com.choucair.formacion.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;


@DefaultUrl("https://www.mercadolibre.com.co/")


public class SeleccionarProductoPage extends PageObject {

    @FindBy(xpath = "//a[@class='nav-menu-categories-link']")
    WebElementFacade btnCategoria;

    @FindBy(xpath = "//a[contains(text(),'Deportes y Aire Libre')]")
    WebElementFacade selCategoria;

    @FindBy(xpath = "//a[contains(text(),'Longboard')]")
    WebElementFacade btnDeporte;

    @FindBy(xpath = "//*[@class='filter-name'][contains(text(),'Antioquia')]")
    WebElementFacade btnUbicaion;

    @FindBy(xpath = "//input[@id='fromPrice']")
    WebElementFacade txtPrecioMin;

    @FindBy(xpath = "//input[@id='toPrice']")
    WebElementFacade txtPrecioMax;

    @FindBy(xpath = "//button[@class='price-filter__action-link filter-action-btn']")
    WebElementFacade btnBuscar;

    @FindBy(xpath = "//a[contains(@class,'reputation-view-more card-block-link')]")
    WebElementFacade btnInfoVendedor;

    @FindBy(xpath = "//a[@class='feedback-offset__link show-link']")
    WebElementFacade btnOpiniones;


   //@FindBy(xpath = "//*[@class='total'][contains(text(),'517')]")
    //WebElementFacade totalOpiniones;


    public void seleccionarTipoDeporte() {

        Actions act = new Actions(getDriver());
        act.moveToElement(btnCategoria).perform();
        act.moveToElement(selCategoria).perform();
        act.moveToElement(btnDeporte).click().perform();

    }

    public void seleccionarUbicacion() {

        if (btnUbicaion.isDisplayed()) {

            btnUbicaion.click();
        }
    }

    public void ingresarRangoPrecios(String precioMin, String precioMax) {

        txtPrecioMin.sendKeys(precioMin);
        txtPrecioMax.sendKeys(precioMax);
        waitABit(1000);
        btnBuscar.click();

    }


    public void seleccionarNumeroProducto(String numeroCaso) {

        $("/html[1]/body[1]/main[1]/div[1]/div[1]/section[1]/ol[1]/li[" + numeroCaso + "]/div[1]/div[1]/div[1]").click();
        for (int i = 1; i <= 7; i++) {

            Serenity.takeScreenshot();
            Actions act = new Actions(getDriver());
            act.moveToElement($("//label[" + i + "]//img[1]")).perform();
            Serenity.takeScreenshot();

        }


    }

    public void verDatosDelVendedor() {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        btnInfoVendedor.click();
        System.out.println(getDriver().getWindowHandle());
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        System.out.println(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        System.out.println(getDriver().getWindowHandle());
        //WebElementFacade ultimaOpinion = $("//span[@class=\"rating__author\"][contains(text(),'Ronald Eduard')]");


        while (true) {
            WebElement Element = getDriver().findElement(By.linkText("Ver más opiniones"));
            js.executeScript("arguments[0].scrollIntoView();", Element);
            waitFor(btnOpiniones).waitUntilVisible();
            waitFor(btnOpiniones).waitUntilEnabled();
            waitFor(btnOpiniones).waitUntilClickable().and().click();
            waitABit(500);


            if (btnOpiniones.isPresent()) {
                System.out.println("siguiente iteracion");
            }
                else {

                    Serenity.takeScreenshot();

                    break;
                }
            }

        }

    public void obtieneReportesConsola (){

       String total = $("//*[@class='total'][contains(text(),'')]").getText();
        System.out.println(total);

        String buenas = $("//span[@id='feedback_good'][contains(text(),'Buena')]").getTextValue();
        System.out.println("===================="+buenas.substring(7,10)+"====================");

        String regulares = $("//span[@id='feedback_good'][contains(text(),'Regular')]").getTextValue();
        System.out.println("===================="+regulares.substring(9,11)+"====================");

        String malas = $("//span[@id='feedback_good'][contains(text(),'Mala')]").getTextValue();
        System.out.println("===================="+malas.substring(5) +"====================");


    }

    }
