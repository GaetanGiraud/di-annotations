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
    private static final Map<Class, Object> registry = new HashMap<>();

    public static void register( Object beanObject){
        register(beanObject.getClass(), beanObject);
    }

    public static void register(Class beanClass, Object beanObject){
        // one can register an object only once
        assert !registry.containsKey(beanClass);

        registry.put(beanClass, beanObject);
    }

    public static Object get(Class beanClass){
        return registry.get(beanClass);
    }

    public static boolean isInstantiated(Class c){
        return registry.containsKey(c);
    }

    public static void reset() {
        registry.clear();
    }
    // Note that there is no "remove" method. Beans are instantiated once at start-up and then should not be removed
}
