package testlistners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTranformerListenersTesyNG implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

    }

    @Override
    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

    }

    @Override
    public void transform(IDataProviderAnnotation annotation, Method method) {

    }

    @Override
    public void transform(IFactoryAnnotation annotation, Method method) {

    }

    @Override
    public void transform(IListenersAnnotation annotation, Class testClass) {

    }
}
