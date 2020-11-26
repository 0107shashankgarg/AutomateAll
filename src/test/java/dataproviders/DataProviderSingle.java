package dataproviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSingle {

    @DataProvider(name = "dataProvider")
    @Test
    public Object[][] dataProvider()
    {
        return new Object[][]{{"test1"},{"test2"}};
    }
}
