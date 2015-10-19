package org.glassfish.hk2.osgiresourcelocator;


import java.util.ArrayList;
import java.util.List;

public final class MyServiceLoaderImpl extends ServiceLoader{


   <T> Iterable<? extends T> lookupProviderInstances1(Class<T> serviceType, ProviderFactory<T> factory){
    	System.err.println("totototo " +  serviceType.getCanonicalName());
    	 if (factory == null) {
    		            factory = new DefaultFactory<T>();
    	 }
    	 List<T> providers = new ArrayList<T>();
    	         for (Class c : lookupProviderClasses1(serviceType)) {
    	             try {
    	                 final T providerInstance = factory.make(c, serviceType);
    	                 if (providerInstance != null) {
    	                     providers.add(providerInstance);
    	                 } else {
    	                     System.err.println(factory + " returned null provider instance!!!");
    	                 }
    	             } catch (Exception e) {
    	                 e.printStackTrace();
    	             }
    	         }
    	         return providers;
    	 

    };
    <T> Iterable<Class> lookupProviderClasses1(Class<T> serviceClass){
    	System.err.println("toti " + serviceClass.getCanonicalName());
        List<Class> providerClasses = new ArrayList<Class>();
        System.err.println(java.util.ServiceLoader.load(serviceClass, MyServiceLoaderImpl.class.getClassLoader()));
       //return  java.util.ServiceLoader.load(serviceClass, MyServiceLoaderImpl.class.getClassLoader()).iterator();
        try {
			providerClasses.add(MyServiceLoaderImpl.class.getClassLoader().loadClass("org.jvnet.hk2.external.generator.ServiceLocatorGeneratorImpl"));
//			providerClasses.add(MyServiceLoaderImpl.class.getClassLoader().loadClass("org.glassfish.hk2.osgiresourcelocator.MyServiceLocatorGenerator"));
				} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            return providerClasses;
        
    };
    private static class DefaultFactory<T> implements ProviderFactory<T> {
    	        public T make(Class providerClass, Class<T> serviceClass) throws Exception {
    	            if (serviceClass.isAssignableFrom(providerClass)) {
    	                return (T) providerClass.newInstance();
    	            }
    	            return null;
    	        }
    	    }


}