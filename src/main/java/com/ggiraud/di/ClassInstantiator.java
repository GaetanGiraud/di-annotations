package com.ggiraud.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * <p/>Published under the MIT License. Use at your own risk! <p/>
 *
 * @author ggiraud
 */
public class ClassInstantiator {
    private final Logger logger = LoggerFactory.getLogger(PackageInspector.class);

    /**
     * Instantiate all beans belonging to the baseClass package and its sub-package
     * @param baseClass
     */
    public void instantiateAll(Class baseClass){
        try {
            logger.info("Starting loading beans for package {}", baseClass.getPackage());
            Set<Class> classes = PackageInspector.getBeans(baseClass);

            for(Class c : classes){
                if(!BeanRegistry.isInstantiated(c)) instantiate(c);
            }

        } catch (IOException e) {
            logger.error("Could not instantiate beans for package {}.", this.getClass().getPackage());
        } catch (ClassNotFoundException e) {
            logger.error("Could not instantiate beans for package {}.", this.getClass().getPackage());
        }
    }

    /**
     * Instantiate one object of a class and register it in the bean registry
     * @param myClass
     * @return
     */
    public Object instantiate(Class myClass){
        try {
            return instantiateClass(myClass);
        } catch (Exception e) {
           logger.error("Could not instantiate bean of class {}, error was {}", myClass.getName(), e.getMessage());
           return null;
        }
    }

    private Object instantiateClass(Class myClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Class> injectedClasses = PackageInspector.getInjectedDependencies(myClass);

        List<Object> argumentsList = new ArrayList<>();

        for(Class c : injectedClasses){
            Object bean = BeanRegistry.get(c);

            if(bean == null) {
                bean = instantiate(c);
            }
            argumentsList.add(bean);
        }

        Object instantiatedBean = myClass.getConstructors()[0].newInstance(argumentsList.toArray());

        BeanRegistry.register(instantiatedBean);

        return instantiatedBean;
    }
}
