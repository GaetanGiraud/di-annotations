package com.ggiraud.di.tests;

import com.ggiraud.di.BeanRegistry;
import com.ggiraud.di.ClassInstantiator;
import com.ggiraud.di.tests.resources.BeanClass;
import com.ggiraud.di.tests.resources.InjectedBean;
import com.ggiraud.di.tests.resources.InjectedBean2;
import com.ggiraud.di.tests.resources.RegularClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * <p>
 * <p/>Published under the MIT License. Use at your own risk! <p/>
 *
 * @author ggiraud
 */
public class ClassInstantiationTest {
    private ClassInstantiator classInstantiator;

    @Before
    public void init(){
        BeanRegistry.reset();
        classInstantiator = new ClassInstantiator();

    }

    @Test
    public void TestSimpleInstantiationClass(){
       Object o = classInstantiator.instantiate(InjectedBean.class);

       assertEquals("Bean with no constructor parameter should be instantiated",  o.getClass(), InjectedBean.class);
    }

    @Test
    public void TestInstantiationMultipleParameters(){
        InjectedBean iBean = new InjectedBean();
        InjectedBean2 iBean2 = new InjectedBean2();

        BeanRegistry.register(iBean);
        BeanRegistry.register(iBean2);

        Object o = classInstantiator.instantiate(BeanClass.class);

        assertEquals("Bean with injected beans should be instantiated", BeanClass.class, o.getClass());

        assertEquals("Bean should have the first injected bean as a field", iBean, ((BeanClass) o).getInjectedBean());
        assertEquals("Bean should have the second injected bean as a field", iBean2, ((BeanClass) o).getInjectedBean2());
    }

    @Test
    public void TestRecursiveInstantiationMultipleParameters(){
        Object o = classInstantiator.instantiate(BeanClass.class);

        assertEquals("Bean with injected beans should be instantiated", BeanClass.class, o.getClass());

        assertNotNull("Injected bean should be instantiated automatically", BeanRegistry.get(InjectedBean.class));
        assertNotNull("Injected bean 2  should be instantiated automatically", BeanRegistry.get(InjectedBean2.class));


    }

    @Test
    public void TestInstantiateAll(){
        classInstantiator.instantiateAll(this.getClass());

        assertTrue("Bean with injected beans should be instantiated", BeanRegistry.isInstantiated(BeanClass.class));

        assertTrue("Injected bean should be instantiated automatically", BeanRegistry.isInstantiated(InjectedBean.class));
        assertTrue("Injected bean 2  should be instantiated automatically", BeanRegistry.isInstantiated(InjectedBean2.class));

        assertFalse("Regular bean should not be instantiated automatically", BeanRegistry.isInstantiated(RegularClass.class));
    }
}
