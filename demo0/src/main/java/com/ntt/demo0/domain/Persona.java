package com.ntt.demo0.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Persona {
    private long id;
    private String nome;
    private int età;
}
