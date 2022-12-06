package org.example;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Computer {

    private String processore;
    private String marca;
    private int hdCapacita;
    private Ram ram;

}