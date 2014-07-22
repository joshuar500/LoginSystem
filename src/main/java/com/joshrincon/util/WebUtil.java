package com.joshrincon.util;


import com.mysql.jdbc.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by on 7/21/2014.
 */
public class WebUtil {
    public static <T> T request2bean(HttpServletRequest request, Class<T> type) {
        Enumeration names = request.getParameterNames();
        T bean;
        try {
            bean = type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        HashMap map = new HashMap();
        while(names.hasMoreElements()) {
            String name = (String) names.nextElement();
            map.put(name, request.getParameter(name));
        }
        try {
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void copyBean(Object dest, Object src) {
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> tClass, Object value) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = (String) value;
                if(!StringUtils.isNullOrEmpty(dateStr)){
                    try {
                        return (T) df.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }, Date.class);

        try {
            BeanUtils.copyProperties(dest, src);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
