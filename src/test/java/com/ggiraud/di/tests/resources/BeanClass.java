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

    @Inject
    public BeanClass(InjectedBean injectedBean) {
        this.injectedBean = injectedBean;

    }
}
