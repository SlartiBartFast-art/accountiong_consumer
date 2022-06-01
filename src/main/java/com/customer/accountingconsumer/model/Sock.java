package com.customer.accountingconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * модель описывает Носок -
 * вид одежды для нижней части ног — короткий чулок, верхний край которого не достигает колена
 * color — цвет носков, строка (например, black, red, yellow);
 * cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
 * quantity — количество пар носков, целое число больше 0.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sock {

    private Long id;
    @Min(value = 1, message = "CottonPart must be not empty and more than 1")
    private int cottonPart;
    @Min(value = 1, message = "Quantity must be not empty and more than 1")
    private int quantity;

    @Valid
    private Color color;

}
