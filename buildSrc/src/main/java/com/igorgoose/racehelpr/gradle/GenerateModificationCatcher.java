package com.igorgoose.racehelpr.gradle;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.This;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.CompileClasspath;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class GenerateModificationCatcher extends DefaultTask {

    @CompileClasspath
    public abstract ConfigurableFileCollection getInputCompileClassPath();

    @Input
    public abstract Property<String> getTargetPackage();

    @TaskAction
    public void generateProxies() throws IOException {
        String targetPackage = getTargetPackage().getOrElse("");
        Path relTargetDir = Path.of(getTargetPackage().getOrElse("").replace('.', '/'));
        List<Path> targetDirs = resolveTargetDirs(relTargetDir);
        URL[] classPathUrls = resolveClassPathUrls();
        File proxiesDir = new File(classPathUrls[0].getPath());

        try (
                URLClassLoader classLoader = new URLClassLoader(classPathUrls);
                Stream<Path> paths = targetDirs.stream()
                        .flatMap(path -> {
                            try {
                                return Files.list(path)
                                        .filter(Files::isRegularFile)
                                        .filter(it -> it.toString().endsWith(".class"));
                            } catch (Exception e) {
                                return Stream.empty();
                            }
                        })
        ) {
            paths
                    .peek(it -> System.out.printf("Processing %s%n", it))
                    .map(path -> path.getFileName().toString())
                    .map(it -> "%s.%s".formatted(targetPackage, it))
                    .map(it -> it.substring(0, it.length() - 6)) // remove .class part
                    .map(className -> loadClass(classLoader, className))
                    .filter(Objects::nonNull)
                    .filter(this::shouldBeProcessed)
                    .forEach(clazz -> generateProxy(clazz, proxiesDir));
        }
    }

    private URL[] resolveClassPathUrls() {
        return getInputCompileClassPath()
                .filter(File::isDirectory)
                .getFiles()
                .stream()
                .map(file -> {
                    try {
                        return file.toURI().toURL();
                    } catch (MalformedURLException e) {
                        System.out.printf("Failed to resolve classpath URL for %s%n", file);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toArray(URL[]::new);
    }

    private List<Path> resolveTargetDirs(Path relTargetDir) {
        return getInputCompileClassPath()
                .filter(File::isDirectory)
                .getFiles()
                .stream()
                .map(File::toPath)
                .map(path -> path.resolve(relTargetDir))
                .filter(Files::isDirectory)
                .peek(path -> System.out.printf("Adding %s for processing%n", path))
                .toList();
    }

    private Class<?> loadClass(ClassLoader classLoader, String className) {
        System.out.printf("Loading class %s\n", className);
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            System.err.printf("Could not load %s: %s%n", className, e);
            return null;
        }
    }

    private boolean shouldBeProcessed(Class<?> clazz) {
        return !clazz.isInterface()
                && !clazz.isAnnotation()
                && !clazz.isAnonymousClass()
                && !clazz.isEnum()
                && !clazz.isArray()
                && !clazz.isPrimitive();
    }

    private void generateProxy(Class<?> clazz, File targetDir) {
        try (DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(clazz)
                .name("%sModificationCatcher".formatted(clazz.getName()))
                .defineField("delegate", clazz, Visibility.PRIVATE)
                .defineConstructor(Visibility.PUBLIC)
                .withParameter(clazz, "delegate")
                .intercept(MethodDelegation.to(ConstructorDelegate.class))
                .make()) {
            dynamicType.saveIn(targetDir);
        } catch (IOException  e) {
            System.err.printf("Error while generating proxy: %s%n", e);
        }
    }

    public static class ConstructorDelegate {
        @SneakyThrows
        public static void construct(@This Object instance, @Origin Constructor<?> constructor) {
            instance.getClass().getField("delegate").set(instance, constructor.getParameters()[0]);
        }
    }
}
