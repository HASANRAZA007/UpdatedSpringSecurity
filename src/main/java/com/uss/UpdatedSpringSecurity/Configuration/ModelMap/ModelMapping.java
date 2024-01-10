package com.uss.UpdatedSpringSecurity.Configuration.ModelMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapping {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
}
