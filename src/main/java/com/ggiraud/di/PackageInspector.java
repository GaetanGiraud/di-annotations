package com.ggiraud.di;

import com.ggiraud.di.annotations.Bean;
import com.ggiraud.di.annotations.Inject;
import com.google.common.reflect.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * <p/> <p/>
 *
 * @author ggiraud
 */
public class PackageInspector {
    Logger logger = LoggerFactory.getLogger(PackageInspector.class);

    /**
     * Get the set of classes annotated with @Bean in the (sub)packages of the base class
     * @param baseClass
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Set<Class> getBeans(Class baseClass) throws IOException, ClassNotFoundException {
        Set<Class> allClasses = getClassesForPackage(baseClass);

        return allClasses.stream().filter(PackageInspector::isBean).collect(Collectors.toSet());

    }

    /**
     * Get the total list of classes in the (sub)packages of the base class
     * @param baseClass
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Set<Class> getClassesForPackage(Class baseClass) throws IOException, ClassNotFoundException {
        ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader()); // scans the class path used by classloader

        String packageName = baseClass.getPackage().getName();

        Set<ClassPath.ClassInfo> classes = classpath.getTopLevelClassesRecursive(packageName);

        return getClasses(classes.stream().map(ClassPath.ClassInfo::getName).collect(Collectors.toSet()));
    }

    /**
     * Utility that converts a collection of Class string names into Class objects
     * @param classNames
     * @return
     * @throws ClassNotFoundException
     */
    public static Set<Class> getClasses(Collection<String> classNames) throws ClassNotFoundException {
        Set<Class> classes = new HashSet<>();

        for(String className : classNames) classes.add(getClass(className));

        return classes;
    }

    /**
     * Utility that converts a Class string names into its corresponding Class object
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    /**
     * Check if the given class has the bean annotation
     * @param myClass
     * @return
     */
    public static boolean isBean(Class myClass) {
        return myClass.isAnnotationPresent(Bean.class);
    }

    /**
     * If the constructor is annotated with @Inject, return the constructor parameters.
     * Otherwise empty list.
     * @param myClass
     * @return
     */
    public static List<Class> getInjectedDependencies(Class myClass){
        if(!isBean(myClass)) return new ArrayList<>(); // only beans allowed

        Constructor[] constructors = myClass.getConstructors();

        assert constructors.length == 1; // 1 single constructor should be defined.

        Constructor constructor = constructors[0];

        if(constructor.isAnnotationPresent(Inject.class)) {
            return Arrays.stream(constructor.getParameters()).map(Parameter::getType).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
