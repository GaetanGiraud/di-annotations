package com.ggiraud.di;

import com.ggiraud.di.annotations.Bean;
import com.ggiraud.di.annotations.Inject;
import com.google.common.reflect.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * <p/> <p/>
 *
 * @author ggiraud
 */
public class PackageInspector {
    Logger logger = LoggerFactory.getLogger(PackageInspector.class);

    public static Set<String> getClassesForPackage(Class baseClass) throws IOException {
        ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader()); // scans the class path used by classloader

        String packageName = baseClass.getPackage().getName();

        Set<ClassPath.ClassInfo> classes = classpath.getTopLevelClassesRecursive(packageName);

        return classes.stream().map(ClassPath.ClassInfo::getName).collect(Collectors.toSet());
    }

    public static Class getClass(String className){
        return getClass(className);
    }

    public static boolean isBean(Class myClass) {
        return myClass.isAnnotationPresent(Bean.class);
    }

    public static Set<Class> getInjectedDependencies(Class myClass){
        if(!isBean(myClass)) return null; // only beans allowed

        Constructor[] constructors = myClass.getConstructors();

        assert constructors.length == 1; // 1 single constructor should be defined.

        Constructor constructor = constructors[0];

        if(constructor.isAnnotationPresent(Inject.class)) {
            return Arrays.stream(constructor.getParameters()).map(Parameter::getType).collect(Collectors.toSet());
        }
        return null;


    }

}
