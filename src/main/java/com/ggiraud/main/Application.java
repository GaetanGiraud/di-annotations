package com.ggiraud.main;

import com.ggiraud.di.BeanRegistry;
import com.ggiraud.di.ClassInstantiator;
import com.ggiraud.main.calculation.UI;

/**
 * <p>
 * <p/>Published under the MIT License. Use at your own risk! <p/>
 *
 * @author ggiraud
 */
public class Application {

    public static void main(String[] args){
        ClassInstantiator instantiator = new ClassInstantiator();
        instantiator.instantiateAll(Application.class);

        UI ui  = (UI) BeanRegistry.get(UI.class);

        ui.calculate(2,3);
    }
}
