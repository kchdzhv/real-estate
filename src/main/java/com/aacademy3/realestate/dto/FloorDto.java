package com.aacademy3.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FloorDto {

    private Long id;


    @Range(min = -3 , max = 3 , message = "Floor must be between -3 and 164." )
    private Integer number;

}
