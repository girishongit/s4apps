package com.cg.pbs.customerprofile;

/**
 *  @author GBANGALO
 *  @Description: Root of the Application
 */
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class CustomerProfileAppl {
	
	public static void main(String[] args)
    {
        SpringApplication.run(CustomerProfileAppl.class, args);
    }
    
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("DataAnalysisApplication-");
        executor.initialize();
        return executor;
    }

}
