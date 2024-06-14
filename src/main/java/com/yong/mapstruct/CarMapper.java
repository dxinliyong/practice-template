package com.yong.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author: liyong
 * @Date: 2024/6/14 15:33
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    @Mapping(target = "numOfDrivers", source = "drivers")
    @Mapping(target = "nn", source = "mm")
    CarDto carToCarDto(Car car);
}