package com.ggiraud.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final BeanRegistry registry;

    public ClassInstantiator(BeanRegistry registry) {
        this.registry = registry;
    }

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
            Object bean = registry.get(c);

            if(bean == null) throw new NullPointerException();

            argumentsList.add(bean);
        }

        return myClass.getConstructors()[0].newInstance(argumentsList.toArray());
    }
}
