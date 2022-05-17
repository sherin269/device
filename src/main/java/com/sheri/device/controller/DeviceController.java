package com.sheri.device.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sheri.device.dto.DeviceDto;
import com.sheri.device.dto.ParentDto;
import com.sheri.device.service.DeviceService;

@RestController
@RequestMapping("/device")
public class DeviceController
{
	@Autowired
	DeviceService deviceService;
	@PostMapping("/create")
	public DeviceDto createDevice(@RequestBody DeviceDto deviceDto)
	{
		System.out.println("START:: DeviceController.createDevice ");
		deviceService.createDevice(deviceDto);
		
		System.out.println("END:: DeviceController.createDevice ");
		return deviceDto;
	}
	@GetMapping("/find")
	public DeviceDto findDevice(@RequestBody DeviceDto deviceDto)
	{
		deviceService.findDevice(deviceDto);
		return deviceDto;
		
		
	}
	@PostMapping("/update")
	public DeviceDto updateDevice(@RequestBody DeviceDto deviceDto)
	{
		deviceService.updateDevice(deviceDto);
		return deviceDto;
		
		
	}
	

}
