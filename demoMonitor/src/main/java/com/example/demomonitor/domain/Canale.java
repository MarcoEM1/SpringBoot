package com.example.demomonitor.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Canale {
    private String nome;
    private int numero;
    private boolean stato;
}
