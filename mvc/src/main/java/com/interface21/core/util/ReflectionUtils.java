package com.interface21.core.util;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class ReflectionUtils {

    /**
     * Obtain an accessible constructor for the given class and parameters.
     * @param clazz the clazz to check
     * @param parameterTypes the parameter types of the desired constructor
     * @return the constructor reference
     * @throws NoSuchMethodException if no such constructor exists
     * @since 5.0
     */
    public static <T> Constructor<T> accessibleConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException {

        Constructor<T> ctor = clazz.getDeclaredConstructor(parameterTypes);
        makeAccessible(ctor);
        return ctor;
    }

    /**
     * Make the given constructor accessible, explicitly setting it accessible
     * if necessary. The {@code setAccessible(true)} method is only called
     * when actually necessary, to avoid unnecessary conflicts.
     * @param ctor the constructor to make accessible
     * @see Constructor#setAccessible
     */
    @SuppressWarnings("deprecation")
    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }


    public static<T extends Annotation> List<Method> getMethodsWithAnnotationType(Object[] basePackage, Class<T> annotationTypes) {

        Set<Class<?>> allClasses = getAllClass(new Reflections(basePackage, Scanners.SubTypes.filterResultsBy(s -> true)));

        return allClasses.stream()
                .flatMap(clazz -> Arrays.stream(clazz.getMethods()))
                .filter(method -> method.isAnnotationPresent(annotationTypes))
                .toList();
    }

    private static Set<Class<?>> getAllClass(Reflections reflections) {
        return reflections.getSubTypesOf(Object.class);
    }
}