package com.sheri.device.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheri.device.dto.DeviceDto;
import com.sheri.device.entity.DeviceEntity;
import com.sheri.device.repository.DeviceRepository;
import com.sheri.device.service.DeviceService;
@Service
public class DeviceServiceImpl implements DeviceService
{
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public void createDevice(DeviceDto deviceDto) 
	{
		
		System.out.println("START:: DeviceServiceImpl.createDevice ");
		validateDevice(deviceDto);
		
		if(!deviceDto.hasError())
		{
			DeviceEntity deviceEntity= modelMapper.map(deviceDto,DeviceEntity.class );
			
			deviceRepository.save(deviceEntity);
		}
		
		System.out.println("END:: DeviceServiceImpl.createDevice ");
		
	}
	
	private void  validateDevice(DeviceDto deviceDto)
	{
		if(!validateSerialNumber(deviceDto.getSerialNumber()))
		{
			deviceDto.addError("serial.number.invalid","ER003","The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry.");
			
		}
		 if(deviceDto.getMachineCode()==null ||deviceDto.getMachineCode().trim().equals(""))
		{
			deviceDto.addError("machine.code.invalid", "ER001", "The machine code is incorrect. Check the Machine code you provided and try again.");
			
		}
		
	}
	
	private boolean validateSerialNumber(String serialNumber)
	{
		
		
		if(serialNumber.matches("[0-9a-zA-Z]{2}[-][0-9a-zA-Z]{4}"))
		{
			return true;
		}
		else if(serialNumber.matches("[0-9a-zA-Z]{7}[-][0-9a-zA-Z]{5}"))
		{
			return true;
		}
		else if(serialNumber.matches("[0-9a-zA-Z]{1}[-][0-9a-zA-Z]{8}"))
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	
	@Override
	public DeviceEntity findDevice(DeviceDto deviceDto)
	{
		DeviceEntity deviceEntity=null;
		if(deviceDto.getMachineCode()!=null &&  !deviceDto.getMachineCode().trim().equals("")
				&& (deviceDto.getSerialNumber()==null ||deviceDto.getSerialNumber().equals("")))
		{
			deviceEntity= deviceRepository.findByMachineCode(deviceDto.getMachineCode());
			if(deviceEntity==null)
			{
				deviceDto.addError("machine.code.not.found", "ER002", "The machine code does not match our records.");
			}
			else
			{
				deviceDto.setDeviceName(deviceEntity.getDeviceName());
				deviceDto.setSerialNumber(deviceEntity.getSerialNumber());
				return deviceEntity;
			}
	
				
		}
		if(deviceDto.getSerialNumber()!=null && !deviceDto.getSerialNumber().trim().equals("")
				&& (deviceDto.getMachineCode()==null ||deviceDto.getMachineCode().equals("")))
		{
			deviceEntity= deviceRepository.findBySerialNumber(deviceDto.getSerialNumber());
			if(deviceEntity==null)
			{
				deviceDto.addError("serial.number.not.found", "ER004", "The serial number does not match our records.");
			}
			else
			{
				deviceDto.setDeviceName(deviceEntity.getDeviceName());
				deviceDto.setMachineCode(deviceEntity.getMachineCode());;
				return deviceEntity;
			}
	
				
		}
		if(deviceDto.getSerialNumber()!=null && !deviceDto.getSerialNumber().trim().equals("")
				&& (deviceDto.getMachineCode()!=null && !deviceDto.getMachineCode().equals("")))
		{
			 deviceEntity=deviceRepository.findByMachineCodeAndSerialNumber(deviceDto.getMachineCode(), deviceDto.getSerialNumber());
			if(deviceEntity==null)
			{
				deviceDto.addError("machine.code.not.found", "ER002", "The machine code does not match our records.");
				deviceDto.addError("serial.number.not.found", "ER004", "The serial number does not match our records.");
				
			}
			else
			{
				deviceDto.setDeviceName(deviceEntity.getDeviceName());
				return deviceEntity;
			}
		}
		return null;
	}
	
	@Override
	public void updateDevice(DeviceDto deviceDto) 
	{
		String newDeviceName=deviceDto.getDeviceName();
		DeviceEntity deviceEntity=findDevice(deviceDto);
		if(deviceEntity!=null)
		{
			deviceEntity.setDeviceName(newDeviceName);
			deviceRepository.save(deviceEntity);
			deviceDto.setDeviceName(newDeviceName);
		}
		
	}
}
