package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyBootCoin {

    @Id
    private String id;
    private String walletId;
    private String customerIdEmisor;
    private String customerIdReceptor;
    private BigDecimal monto;
    private String state;


}
