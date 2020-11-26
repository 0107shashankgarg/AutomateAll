package dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviderMultiple {

    @DataProvider(name = "DataProviderMultiple")
    public Object[][] DataProviderMultiple()
    {
        return new Object[][]{{1,2,3,4,},{5,6,7,8}};
    }
}
