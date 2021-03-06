package com.aacademy3.realestate.controller;

import com.aacademy3.realestate.converter.FloorConverter;
import com.aacademy3.realestate.dto.FloorDto;
import com.aacademy3.realestate.model.Floor;
import com.aacademy3.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/floors")
public class FloorController {

    private final FloorService floorService;
    private final FloorConverter floorConverter;

    @Autowired
    public FloorController(FloorService floorService,
                           FloorConverter floorConverter) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDto>> findAll() {
        Set<FloorDto> floorDtos = new HashSet<>();
        Set<Floor> floors = floorService.findAll();

        for (Floor floor : floors) {
            FloorDto floorDto = floorConverter.toFloorDto(floor);
            floorDtos.add(floorDto);
        }


        return ResponseEntity.ok(floorService
                .findAll()
                .stream()
                .map(floorConverter::toFloorDto)
                .collect(Collectors.toSet())
        );
    }

        @PostMapping
        public ResponseEntity<FloorDto> save(@RequestBody @Valid FloorDto floorDto) {
            Floor floor = floorConverter.toFloor(floorDto);
            Floor savedFloor = floorService.save(floor);
            return ResponseEntity.ok(floorConverter.toFloorDto(savedFloor));

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
            floorService.delete(id);
            return ResponseEntity.ok().build();

        }


    }

