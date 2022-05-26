package com.serdeliverance.cryptowallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cryptocurrency {
    private Integer id;
    private String name;
    private String symbol;
}
