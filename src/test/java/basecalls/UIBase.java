package basecalls;


import config.ConfigMapping;
import org.aeonbits.owner.ConfigCache;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class UIBase  extends WedriverSetup{


    protected  ConfigMapping baseCfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());


   @BeforeClass
   public void openURL1()
   {
       System.out.println("parent");
   }

    @AfterClass
    private void checkAndQuitDriver()
    {
      if(getWebDriver()!=null)
        {
            getWebDriver().quit();
        }
    }

}