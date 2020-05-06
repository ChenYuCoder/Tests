package com.vichen.system;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * @author vichen
 */
public class UniqueBeanNameGenerator extends AnnotationBeanNameGenerator {
  @Override
  public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
    return definition.getBeanClassName();
  }
}
