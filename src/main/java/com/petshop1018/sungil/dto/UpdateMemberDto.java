package com.petshop1018.sungil.dto;

import com.petshop1018.sungil.domain.Member;
import lombok.Data;

@Data
public class UpdateMemberDto {
    private String username; // Optional
    private String email; // Optional
    private String password; // Optional, 필요 시 사용
}
