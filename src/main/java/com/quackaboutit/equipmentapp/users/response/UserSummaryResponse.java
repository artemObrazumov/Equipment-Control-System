package com.quackaboutit.equipmentapp.users.response;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.Role;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;

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
public class UserSummaryResponse {
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private Role role;

    @NotBlank
    @NotNull
    private String email;

    @NotNull
    private Unit unit;

    public static UserSummaryResponse fromUserToSummaryResponse(User user){
        return new UserSummaryResponse(user.getId(), user.getUsername(),
            user.getRole(), user.getEmail(), user.getUnit());
    }
}
