package com.quackaboutit.equipmentapp.equipment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class EquipmentTypeAnalytics {

    private Integer equipmentCount;
    private HashMap<String, Integer> carBrand;
    private List<Integer> yearActivity;
}
