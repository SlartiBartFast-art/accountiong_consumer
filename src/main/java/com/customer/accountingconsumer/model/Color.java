package com.customer.accountingconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    private Long id;
    @NotBlank(message = "Coloring must be not empty")
    private String coloring;
}
