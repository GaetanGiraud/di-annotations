package com.ggiraud.di.tests;

import com.ggiraud.di.BeanRegistry;
import com.ggiraud.di.ClassInstantiator;
import com.ggiraud.di.tests.resources.BeanClass;
import com.ggiraud.di.tests.resources.InjectedBean;
import com.ggiraud.di.tests.resources.InjectedBean2;
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
    private BeanRegistry registry;

    @Before
    public void init(){
        registry = new BeanRegistry();
        classInstantiator = new ClassInstantiator(registry);

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

        registry.register(iBean);
        registry.register(iBean2);

        Object o = classInstantiator.instantiate(BeanClass.class);

        assertEquals("Bean with injected beans should be instantiated", BeanClass.class, o.getClass());

        assertEquals("Bean should have the first injected bean as a field", iBean, ((BeanClass) o).getInjectedBean());
        assertEquals("Bean should have the second injected bean as a field", iBean2, ((BeanClass) o).getInjectedBean2());
    }
}
