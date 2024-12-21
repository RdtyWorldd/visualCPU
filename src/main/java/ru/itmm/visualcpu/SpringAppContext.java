package ru.itmm.visualcpu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.memory.Memory;

@ComponentScan(basePackages = "ru.itmm.visualcpu")
@Configuration(proxyBeanMethods=false)
public class SpringAppContext {
//    @Bean
//    ProgramModel getProgramModel() {
//        return new ProgramModel();
//    }
    @Bean
    public Memory memory() {
        return new Memory(4, 1024);
    }
}
