package com.sheri.device.service;

import com.sheri.device.dto.DeviceDto;
import com.sheri.device.entity.DeviceEntity;

public interface DeviceService
{
	public void createDevice(DeviceDto deviceDto);
	public DeviceEntity findDevice(DeviceDto deviceDto);
	public void updateDevice(DeviceDto deviceDto);
}
