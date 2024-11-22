package com.quackaboutit.equipmentapp.workplace.dto;

import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import com.quackaboutit.equipmentapp.workplace.entity.WorkplaceState;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class WorkplaceResponse {
    @NotNull
    private Long id;
    
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    private WorkplaceState state;

    public static WorkplaceResponse fromWorkplacetoResponce(Workplace wrkspc){
        return new WorkplaceResponse(wrkspc.getId(), wrkspc.getLatitude(), 
        wrkspc.getLongitude(), wrkspc.getAddress(), wrkspc.getState());
    }
}
