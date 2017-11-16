package com.ggiraud.main.calculation;

import com.ggiraud.di.annotations.Bean;

/**
 * <p>
 * <p/>Published under the MIT License. Use at your own risk! <p/>
 *
 * @author ggiraud
 */
@Bean
public class Engine {

    public Engine() {
        System.out.println("Engine initiated");
    }

    public double calculate(double x, double y) {
        return x * y;
    }
}
