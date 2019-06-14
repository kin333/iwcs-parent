package com.wisdom.iwcs.common.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/22 10:57
 */
public class XmlToBeanUtils {


    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xmlPath, Class<T> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xmlPath));
    }
}
