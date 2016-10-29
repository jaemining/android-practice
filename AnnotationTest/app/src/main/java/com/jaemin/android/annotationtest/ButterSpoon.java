package com.jaemin.android.annotationtest;

import android.app.Activity;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Jaemin on 2016. 10. 27..
 */

public class ButterSpoon {

    public static void bind(MainActivity object) {
        Class<?> klass = object.getClass();

        try {
            for (Field field : klass.getClassLoader().loadClass("MainActivity").getDeclaredFields()) {
                if (field.isAnnotationPresent(com.jaemin.android.annotationtest.BindView.class)) {
                    BindView bindView = field.getAnnotations(BindView.class);
                    int viewID = bindView.viewID();

                    Button button = (Button) object.button;
                    button = (Button) object.findViewById(viewID);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
