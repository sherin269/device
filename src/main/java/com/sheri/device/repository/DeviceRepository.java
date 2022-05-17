package com.sheri.device.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sheri.device.entity.DeviceEntity;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, Integer>
{
	@Query(value = "select * from device where machine_code =:machineCode AND serial_number=:serialNumber", nativeQuery = true)
	public DeviceEntity findByMachineCodeAndSerialNumber(@Param(value = "machineCode") String machineCode,@Param(value = "serialNumber") String serialNumber);

	@Query(value = "select * from device where machine_code =:machineCode ", nativeQuery = true)
	public DeviceEntity findByMachineCode(@Param(value = "machineCode") String machineCode);

	@Query(value = "select * from device where serial_number=:serialNumber", nativeQuery = true)
	public DeviceEntity findBySerialNumber(@Param(value = "serialNumber") String serialNumber);

}
