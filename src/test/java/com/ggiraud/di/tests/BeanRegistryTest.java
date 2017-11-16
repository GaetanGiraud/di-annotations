package com.ggiraud.di.tests;

import com.ggiraud.di.BeanRegistry;
import com.ggiraud.di.tests.resources.BeanClass;
import com.ggiraud.di.tests.resources.InjectedBean;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * <p/>Published under the MIT License. Use at your own risk! <p/>
 *
 * @author ggiraud
 */
public class BeanRegistryTest {
    @Test
    public void RegisterBeanTest(){
        BeanRegistry beanRegistry = new BeanRegistry();

        BeanClass bean = new BeanClass(new InjectedBean());

        beanRegistry.register(BeanClass.class, bean);

        assertEquals("Error registering bean in registry" , bean, beanRegistry.get(BeanClass.class));

    }
}
