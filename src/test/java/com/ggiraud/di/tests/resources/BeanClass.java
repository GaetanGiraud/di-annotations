package com.ggiraud.di.tests.resources;

import com.ggiraud.di.annotations.Bean;
import com.ggiraud.di.annotations.Inject;

/**
 * <p>
 * <p/>All Rights Reserved. <p/>
 *
 * @author ggiraud
 */
@Bean
public class BeanClass {
    public static final String beanClassName = "com.ggiraud.di.tests.resources.BeanClass";

    private final InjectedBean injectedBean;
    private final InjectedBean2 injectedBean2;

    @Inject
    public BeanClass(InjectedBean injectedBean, InjectedBean2 injectedBean2) {
        this.injectedBean = injectedBean;
        this.injectedBean2 = injectedBean2;

    }

    public InjectedBean getInjectedBean() {
        return injectedBean;
    }

    public InjectedBean2 getInjectedBean2() {
        return injectedBean2;
    }
}
