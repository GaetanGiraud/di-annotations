package com.ggiraud.di;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * <p/>Copyright 2017 (c) UReason.  All Rights Reserved. <p/>
 *
 * @author ggiraud
 */
public class PackageInspector {

    public static Set<String> getClassesForPackage(Class baseClass) throws IOException {
        ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader()); // scans the class path used by classloader

        String packageName = baseClass.getPackage().getName();

        Set<ClassPath.ClassInfo> classes = classpath.getTopLevelClassesRecursive(packageName);
               // .stream().map(ClassPath.ClassInfo::getClass).collect(Collectors.toSet());

        return classes.stream().map(ClassPath.ClassInfo::getName).collect(Collectors.toSet());
    }

    public static boolean isBean(String className){
        return true;

    }
}
