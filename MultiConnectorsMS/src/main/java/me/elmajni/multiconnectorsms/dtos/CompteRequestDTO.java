package me.elmajni.multiconnectorsms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompteRequestDTO {
    private Long id;
    private Date createdAt;
    private Double balance;
    private String currency;
}
