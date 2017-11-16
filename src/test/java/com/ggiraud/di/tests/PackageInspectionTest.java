package com.ggiraud.di.tests;

import com.ggiraud.di.PackageInspector;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;


/**
 * <p>
 * <p/>Copyright 2017 (c) UReason.  All Rights Reserved. <p/>
 *
 * @author ggiraud
 */
public class PackageInspectionTest {

    @Test
    public void CanListClassesInPackage(){
        try {
            Set<String> classesInPackage = PackageInspector.getClassesForPackage(this.getClass());

            assertTrue("Could not find current class in class list", classesInPackage.contains(this.getClass().getName()));
            assertTrue("Could not find test bean class in class list", classesInPackage.contains("com.ggiraud.di.tests.resources.BeanTest"));

        } catch (IOException e) {
            assertTrue( "IO Exception while loading classes", false);
        }


    }
}
