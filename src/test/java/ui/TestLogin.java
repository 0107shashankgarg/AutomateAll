/*
 * sh for new user and login page validation with valid and invalid credentials.
 * Test cases are reading data from DataProviderClass and performing action.
 * please refer DataProviderClass for different sets of input data
 * Test cases will pass when valid credentials are provided
 * Test cases will fail when invalid credentials are given
 */

package ui;




import com.sun.javafx.scene.control.Logging;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import dataproviders.*;



public class TestLogin {

    private static final Logger LOG = LogManager.getLogger(TestLogin.class);


    @Description("Test Description: Login test with wrong username and wrong password.")
    @Test
    public void m() {

        System.out.println("test");

    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProvder()
    {
        return new Object[][]{{"First test"}, {"Second test"}};
    }

    @Test(dataProvider = "data-provider")
    public void dataprov(String val)
    {
        System.out.println("passed parameter is" + val);
        LOG.info("Logging");
    }


    @Test(dataProvider = "dataProvider", dataProviderClass = DataProviderSingle.class)
    public  void dataprovSingle(String val)
    {
        System.out.println(val);
    }

    @Test(dataProvider = "DataProviderMultiple", dataProviderClass = DataProviderMultiple.class)
    public void dataprovMultiple(int a , int b[])
    {
        System.out.println(a);
        for (int ele:b
             ) {
            System.out.println(ele);
        }
    }
}

