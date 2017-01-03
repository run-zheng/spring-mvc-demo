package org.demo.spring.mvc.test;

import com.alibaba.fastjson.JSON;
import org.demo.spring.mvc.annotation.BizFlowConfig;
import org.demo.spring.mvc.annotation.BizFlowConfiger;
import org.demo.spring.mvc.composite.BizFlowConfigureDemo;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Created by jacky on 2017/1/3.
 */
public class Main {
    public static void main(String[] args ) throws IOException {
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

        MetadataReader metadataReader = factory.getMetadataReader(BizFlowConfigureDemo.class.getName());
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        System.out.println(annotationMetadata.isAnnotated(BizFlowConfiger.class.getName()));
        Set<MethodMetadata> annotatedMethods = annotationMetadata.getAnnotatedMethods(BizFlowConfig.class.getName());
        for (MethodMetadata methodMetaData : annotatedMethods ) {
            System.out.println(methodMetaData.getMethodName()
            + " " + methodMetaData.isAnnotated(BizFlowConfig.class.getName())
            + "\r\n       "
            + JSON.toJSONString(methodMetaData.getAllAnnotationAttributes(BizFlowConfig.class.getName())));
        }

    }

}
