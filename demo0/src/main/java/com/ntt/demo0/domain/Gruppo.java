package com.ntt.demo0.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gruppo {
    private List<Persona> people = new ArrayList<Persona>();

}
