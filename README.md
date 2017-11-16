# Dependency Injection & Java Annotations


## Toy project to demonstrate dependency injection using java annotations

In the toy project a "naive" dependency injection mechanism using java annotations will be put in place.
It will be fully functional (but not fully optimised).

I will be using the Java EE/Spring convention of calling beans singletons instantiated using the dependency injection framework.

The following components will have to be implemented:
* A bean register where beans can be registered and retrieved
* A package inspector that inspect classes from a base package and reads their annotations
* All the wiring to link the two with each other

## 1. Package inspection 
Let's start with the beginning and see how we can inspect a package to discover which classes are available.

To make our life a little easier, we will use Guava's [classpath](https://github.com/google/guava/wiki/ReflectionExplained#classpath) functionality to find out which classes have been defined in our package.
A warning from Google: **So don't use it for mission critical production task**, which is fine as this really meant as a toy project.

