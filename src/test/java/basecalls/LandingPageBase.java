package basecalls;

import org.testng.annotations.BeforeMethod;
import ui.pageobjects.*;



public class LandingPageBase extends UIBase{

   @BeforeMethod
       public void openURL()
   {
       System.out.println("working");
       Page.landingPage().open(baseCfg.baseUrl());

   }

}
