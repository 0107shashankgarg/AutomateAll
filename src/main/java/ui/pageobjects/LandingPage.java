package ui.pageobjects;

import com.codeborne.selenide.Selenide;

import utils.ui.PageUtils;



public class LandingPage extends PageUtils {


    public LandingPage open(String url) {
        System.out.println("LandingPage");
        Selenide.open(url);
        return this;
    }

}

