package com.sheri.device;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sheri.device"})
public class DeviceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(DeviceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
