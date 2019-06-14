/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 342252328@qq.com
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.wisdom.iwcs.pagehelper;

import com.wisdom.iwcs.pagehelper.annotation.DataFilter;
import com.wisdom.iwcs.pagehelper.filtercache.Cache;
import com.wisdom.iwcs.pagehelper.filtercache.DataFilterCache;
import com.wisdom.iwcs.pagehelper.filtercache.LocalDataFilterCache;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class DataFilterResolver {

    private static final Log log = LogFactory.getLog(DataFilterResolver.class);

    /**
     * DATA_FILTER_CACHE, mapperMap are ConcurrentHashMap, thread safe
     **/
    private static final DataFilterCache DATA_FILTER_CACHE = new LocalDataFilterCache();
    private static final Map<String, Class<?>> mapperMap = new ConcurrentHashMap<>();

    private static final DataFilter trueLocker, falseLocker;

    static {
        try {
            trueLocker = DataFilterResolver.class.getDeclaredMethod("trueVersionValue").getAnnotation(DataFilter.class);
            falseLocker = DataFilterResolver.class.getDeclaredMethod("falseVersionValue").getAnnotation(DataFilter.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("Optimistic Locker Plugin init faild." + e, e);
        }
    }

    @DataFilter(true)
    private void trueVersionValue() {
    }

    @DataFilter(false)
    private void falseVersionValue() {
    }

    static DataFilter resolve(/*MetaObject mo*/BoundSql boundSql, MappedStatement ms) {

        // if the method is not a 'update', return false
		/*MappedStatement ms = (MappedStatement) mo.getValue("mappedStatement");
		if(ms.getSqlCommandType() != SqlCommandType.UPDATE) return falseLocker;
		
		BoundSql boundSql = (BoundSql) mo.getValue("boundSql");*/
        Object paramObj = boundSql.getParameterObject();
        Class<?>[] paramCls = null;

        String id = ms.getId();
        Cache.MethodSignature vm = new Cache.MethodSignature(id, paramCls);
        DataFilter dataFilter = DATA_FILTER_CACHE.getVersionLocker(vm);
        if (null != dataFilter) return dataFilter;

        if (null == mapperMap || mapperMap.isEmpty()) {
            Collection<Class<?>> mappers = ms.getConfiguration().getMapperRegistry().getMappers();
            if (null != mappers && !mappers.isEmpty()) {
                for (Class<?> me : mappers) {
                    mapperMap.put(me.getName(), me);
                }
            }
        }

        int pos = id.lastIndexOf(".");
        String nameSpace = id.substring(0, pos);
        if (!mapperMap.containsKey(nameSpace)) {
            if (log.isDebugEnabled()) {
//				log.debug(Constent.LogPrefix + "Config info error, maybe you have not config the Mapper interface");
                throw new RuntimeException("Config info error, maybe you have not config the Mapper interface");
            }
        }
        Class<?> mapper = mapperMap.get(nameSpace);
        Method m = null;
        Method[] methods = mapper.getMethods();
        if (methods != null && methods.length > 0) {
            List<Method> theMethods = Arrays.asList(methods).stream().filter(method -> method.getName().equals(id.substring(pos + 1))).collect(Collectors.toList());
            if (theMethods != null && theMethods.size() == 1) {
                m = theMethods.get(0);
            } else {
//				throw new RuntimeException("Can not find this method");
                return null;
            }
        }
		/*try {
			m = mapper.getMethod(id.substring(pos + 1), paramCls);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("The Map type param error." + e, e);
		}*/
        dataFilter = m.getAnnotation(DataFilter.class);
        if (null == dataFilter) {
            dataFilter = trueLocker;
        }
        if (!DATA_FILTER_CACHE.containMethodSignature(vm)) {
            DATA_FILTER_CACHE.cacheMethod(vm, dataFilter);
        }
        return dataFilter;
    }
}
