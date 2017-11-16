package com.ggiraud.di.tests;

import com.ggiraud.di.PackageInspector;
import com.ggiraud.di.tests.resources.BeanClass;
import com.ggiraud.di.tests.resources.InjectedBean;
import com.ggiraud.di.tests.resources.RegularClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


/**
 * <p>
 * <p/><p/>
 *
 * @author ggiraud
 */
public class PackageInspectionTest {

    @Test
    public void canListClassesInPackage(){
        try {
            Set<String> classesInPackage = PackageInspector.getClassesForPackage(this.getClass());

            assertTrue("Could not find current class in class list", classesInPackage.contains(this.getClass().getName()));
            assertTrue("Could not find test bean class in class list", classesInPackage.contains(BeanClass.beanClassName));
            assertTrue("Could not find test regular class in class list", classesInPackage.contains(RegularClass.regularClassName));
        } catch (IOException e) {
            assertTrue( "IO Exception while loading classes", false);
        }

    }

    @Test
    public void canGetClassName(){
        try {
            assertEquals("Could not find beanClass using the PackageInspector",loadTestClass(BeanClass.beanClassName), PackageInspector.getClass(BeanClass.beanClassName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void canFindBeans(){
            assertTrue("Cannot idenfify bean class as having the bean annotation", PackageInspector.isBean(loadTestClass(BeanClass.beanClassName)));
            assertFalse("Wrongly idenfify regular class as having the bean annotation", PackageInspector.isBean(loadTestClass(RegularClass.regularClassName)));
    }

    @Test
    public void canFindInjectedBeans(){
        List<Class> injectedClasses =  PackageInspector.getInjectedDependencies(loadTestClass(BeanClass.beanClassName));

        assertTrue("Cannot identify injected bean class in bean constructor class", injectedClasses.contains(InjectedBean.class));

    }

    private Class loadTestClass(String testClassName){
        try {
            return Class.forName(testClassName);
        } catch (ClassNotFoundException e) {
            assertTrue("Error loading the test classes", false);

            return null;
        }
    }
}
