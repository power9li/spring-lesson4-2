package com.power.spring.ioc;

import com.power.spring.annotations.MyAutowired;
import com.power.spring.annotations.MyComponent;
import com.power.spring.utils.PackageScanner;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by shenli on 2017/1/16.
 */
public class LdIocFactory {

    private static final Map<String, MyBean> nameBeanMap = new HashMap<>();
    private static final Map<Class, MyBean> clazzBeanMap = new HashMap<>();


    public void scanPackages(String[] packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String packageName : packageNames) {
            Set<Class<?>> fileClass = PackageScanner.findFileClass(packageName);
            for (Class clazz : fileClass) {
                if (clazz.isAnnotationPresent(MyComponent.class)) {
//                    Method[] declaredMethods = clazz.getDeclaredMethods();

                    MyBean mb = new MyBean();
                    MyComponent mc = (MyComponent)clazz.getAnnotation(MyComponent.class);
                    if (mc.value() != null && !mc.value().equals("")) {
                        mb.setName(mc.value());
                    }
                    else {
                        mb.setName(clazz.getSimpleName());
                    }
                    mb.setTargetObject(clazz.newInstance());
                    Type sType = clazz.getGenericSuperclass();
                    if (!sType.equals(Object.class)) {
                        ParameterizedType parameterizedType = (ParameterizedType)sType;
                        Type genericType = parameterizedType.getActualTypeArguments()[0];
                        mb.setGenericType(genericType);
                    }

                    nameBeanMap.put(mb.getName(), mb);
                    clazzBeanMap.put(clazz, mb);
                    System.out.println("clazzBeanMap.put("+clazz+","+mb+")");


                    for(; clazz != Object.class;clazz = clazz.getSuperclass()){
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {// 获取bean的属性和值
                            if (field.isAnnotationPresent(MyAutowired.class)) {
                                mb.addDependentField(field);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("\nnameBeanMap = " + nameBeanMap.size());
        assembleBeans();
    }

    private void assembleBeans() throws IllegalAccessException {
        for (Map.Entry<String, MyBean> entity : nameBeanMap.entrySet()) {
            MyBean bean = entity.getValue();
            Set<Field> dependentFields = bean.getDependentFields();
            if (dependentFields != null && dependentFields.size() > 0) {
                for (Field field : dependentFields) {

                    Class<?> type = field.getType();
                    Type fieldGenericType = field.getGenericType();

                    if(fieldGenericType instanceof ParameterizedType) {

                        for (Class beanMapClazz : clazzBeanMap.keySet()) {
                            if (type.isAssignableFrom(beanMapClazz)) {
                                MyBean dependencyBean = clazzBeanMap.get(beanMapClazz);
                                Type beanMapGenericType = dependencyBean.getGenericType();
                                if (beanMapGenericType.equals(bean.getGenericType())) {
                                    System.out.println(type + "<" + beanMapGenericType.getTypeName() + ">.isAssignableFrom(" + beanMapClazz + "<" + bean.getGenericType().getTypeName() + ">)");
                                    field.setAccessible(true);
                                    field.set(bean.getTargetObject(), dependencyBean.getTargetObject());
                                }
                            }
                        }
                    }
                    else if(fieldGenericType.equals(Integer.class)){

                    }




                }
            }
        }
    }

    public <T> T getBean(Class<? extends T> clazz) {
        MyBean bean =  clazzBeanMap.get(clazz);
        return bean ==null ? null : (T)bean.getTargetObject();
    }

    public <T> T getBean(String beanName){
        MyBean bean = nameBeanMap.get(beanName);
        return bean == null? null :(T)bean.getTargetObject();
    }

}
