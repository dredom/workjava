package com.dredom.inject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Maps classes to implementations.
 * Providers might be a javax.inject.Provider in the case of factories.
 *
 * <p>Key is generally an interface.
 * <p>If there is no entry in providers then the packageBase is scanned for an implementation.
 * It seems there is no need for packageBase. Scan class package down hierarchy, then up hierarchy, then classpath.
 * Interface implementing entry added to providers map once found.
 * <p>If there is no entry in providers it is assumed to have a simple zero-args constructor
 * or implement Provider interface.
 *
 * @author andre
 *
 */
public class InjectResolverImpl {

    private String packageBase;

    private ConcurrentHashMap<Class<?>, Class<?>> providers;

    public InjectResolverImpl(String packageBase) {
        super();
        this.packageBase = packageBase;
    }

    public Class<?> get(Class<?> interfaceClass) {
        return providers.get(interfaceClass);
    }

}
