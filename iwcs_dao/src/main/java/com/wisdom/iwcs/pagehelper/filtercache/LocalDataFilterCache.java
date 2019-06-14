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
package com.wisdom.iwcs.pagehelper.filtercache;

import com.wisdom.iwcs.pagehelper.annotation.DataFilter;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.util.concurrent.ConcurrentHashMap;

public class LocalDataFilterCache implements DataFilterCache {

    private static final Log log = LogFactory.getLog(LocalDataFilterCache.class);
    private ConcurrentHashMap<String, ConcurrentHashMap<MethodSignature, DataFilter>> caches = new ConcurrentHashMap<>();

    @Override
    public boolean containMethodSignature(MethodSignature ms) {
        String nameSpace = getNameSpace(ms);
        ConcurrentHashMap<MethodSignature, DataFilter> cache = caches.get(nameSpace);
        if (null == cache || cache.isEmpty()) {
            return false;
        }
        boolean containsMethodSignature = cache.containsKey(ms);
        if (containsMethodSignature && log.isDebugEnabled()) {
//			log.debug(Constent.LogPrefix + "The method " + nameSpace + ms.getId() + "is hit in cache.");
        }
        return containsMethodSignature;
    }

    @Override
    public void cacheMethod(MethodSignature vm, DataFilter locker) {
        String nameSpace = getNameSpace(vm);
        ConcurrentHashMap<MethodSignature, DataFilter> cache = caches.get(nameSpace);
        if (null == cache || cache.isEmpty()) {
            cache = new ConcurrentHashMap<>();
            cache.put(vm, locker);
            caches.put(nameSpace, cache);
            if (log.isDebugEnabled()) {
//				log.debug(Constent.LogPrefix + nameSpace + ": " + vm.getId() + " is cached.");
            }
        } else {
            cache.put(vm, locker);
        }
    }

    @Override
    public DataFilter getVersionLocker(MethodSignature vm) {
        String nameSpace = getNameSpace(vm);
        ConcurrentHashMap<MethodSignature, DataFilter> cache = caches.get(nameSpace);
        if (null == cache || cache.isEmpty()) {
            return null;
        }
        return cache.get(vm);
    }

    private String getNameSpace(MethodSignature vm) {
        String id = vm.getId();
        int pos = id.lastIndexOf(".");
        return id.substring(0, pos);
    }

}
