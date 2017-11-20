package com.ggiraud.main.calculation;

import com.ggiraud.di.annotations.Bean;
import com.ggiraud.di.annotations.Inject;

/**
 * <p>
 * <p/>Published under the MIT License. Use at your own risk! <p/>
 *
 * @author ggiraud
 */
@Bean
public class UI {
    private Engine engine;

    @Inject
    public UI(Engine engine) {
        System.out.println("UI initiated");
        this.engine = engine;
    }

    public void calculate(double x, double y){
        System.out.println("2 by 3 is " + engine.calculate(x, y));
    }
}
