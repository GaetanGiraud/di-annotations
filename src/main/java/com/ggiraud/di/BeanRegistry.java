package com.ggiraud.di;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <p/>Published under the MIT License. Use at your own risk! <p/>
 *
 * @author ggiraud
 */
public class BeanRegistry {
    private final Map<Class, Object> registry;

    public BeanRegistry() {
        registry = new HashMap<>();
    }

    public void register(Class beanClass, Object beanObject){
        // one can register an object only once
        assert !registry.containsKey(beanClass);

        registry.put(beanClass, beanObject);
    }

    public Object get(Class beanClass){
        return registry.get(beanClass);
    }

    // Note that there is no "remove" method. Beans are instantiated once at start-up and then should not be removed
}
