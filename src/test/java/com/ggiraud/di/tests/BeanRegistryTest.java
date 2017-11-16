package com.ggiraud.di.tests;

import com.ggiraud.di.BeanRegistry;
import com.ggiraud.di.tests.resources.BeanClass;
import com.ggiraud.di.tests.resources.InjectedBean;
import com.ggiraud.di.tests.resources.InjectedBean2;
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
        BeanClass bean = new BeanClass(new InjectedBean(), new InjectedBean2());

        BeanRegistry.register(BeanClass.class, bean);

        assertEquals("Error registering bean in registry" , bean, BeanRegistry.get(BeanClass.class));

    }
}
