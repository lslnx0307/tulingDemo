package com.lslnx.session;

import com.lslnx.binding.MapperMethod;
import com.lslnx.binding.MapperRegistry;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tulingDemo
 * @description: 读取xml加载到内存
 * @author: v-jasperli
 * @create: 2019-02-21 11:15
 **/
public class Configuration {
    private InputStream inputStream;

    private MapperRegistry mapperRegistry = new MapperRegistry();

    public void loadConfigurations() throws IOException {
        try {
            Document read = new SAXReader().read(inputStream);
            Element rootElement = read.getRootElement();
            List<Element> mappers = rootElement.element("mappers").elements();
            for (Element element : mappers) {
                if (element.attribute("resource") != null) {
                    mapperRegistry.setKnownMappers(loadXMLConfiguration(element.attribute("resource").getText()));
                }
            }
        } catch (DocumentException e) {
            System.out.println("load configuration error" + e.getMessage());
        } finally {
            inputStream.close();
        }
    }

    private Map<String, MapperMethod> loadXMLConfiguration(String source) {
        Map<String, MapperMethod> map = new HashMap<>();
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream(source);
            Document document = new SAXReader().read(in);
            Element element = document.getRootElement();
            if (element.getName().equalsIgnoreCase("mapper")) {
                String namespace = element.attribute("namespace").getText();
                List<Element> selects = element.elements("select");
                for (Element e : selects) {
                    MapperMethod mapperMethod = new MapperMethod();
                    mapperMethod.setSql(e.getText().trim());
                    mapperMethod.setType(Class.forName(e.attribute("resultType").getText()));
                    map.put(namespace + "." + e.attribute("id").getText(), mapperMethod);
                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }
}
