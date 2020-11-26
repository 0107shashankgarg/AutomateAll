package testlistners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListnerTestNG implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        System.out.println("onStart function started "  + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("onStart function started "  + suite.getName());

    }
}
