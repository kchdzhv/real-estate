package com.aacademy3.realestate.converter;

import com.aacademy3.realestate.dto.FloorDto;
import com.aacademy3.realestate.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter {

    public FloorDto toFloorDto(Floor floor) {
        return FloorDto.builder()
                .number(floor.getNumber())
                .build();
    }

    public Floor toFloor(FloorDto floorDto) {
        return  Floor.builder()
                .number(floorDto.getNumber())
                .build();
    }
}
