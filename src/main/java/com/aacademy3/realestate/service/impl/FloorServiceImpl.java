package com.aacademy3.realestate.service.impl;

import com.aacademy3.realestate.exception.DublicateRecordException;
import com.aacademy3.realestate.exception.ResourceNotFoundException;
import com.aacademy3.realestate.model.Floor;
import com.aacademy3.realestate.model.repository.FloorRepository;
import com.aacademy3.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;


    public FloorServiceImpl(FloorRepository floorReposidery) {
        this.floorRepository = floorReposidery;
    }

    public Set<Floor> findAll() {
        return new TreeSet<>(floorRepository.findAll());
    }

    @Override
    public Floor save(Floor floor) {
        try {
            return floorRepository.save(floor);

        } catch (DataIntegrityViolationException exception) {
            throw  new DublicateRecordException(String.format("Floor with number %d already exists." , floor.getNumber()));
        }
    }

    @Override
    public Floor findByNumber(Integer number) {
        return floorRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Floor with %d does not exists.", number)));
    }

    @Override
    public Floor findById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Floor with %d does not exists.", id)));
    }

    @Override
    public Floor update(Floor floor, Long id) {
        Floor foundFloor = findById(id);
        Floor updatedFloor = Floor.builder()
                .id(foundFloor.getId())
                .number(floor.getNumber())
                .build();
        return save(updatedFloor);
    }

    @Override
    public void delete(Long id) {
        floorRepository.deleteById(id);
    }


}
