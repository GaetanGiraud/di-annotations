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

    public void start(){
        ClassInstantiator instantiator = new ClassInstantiator();
        instantiator.instantiateAll(this.getClass());

        UI ui  = (UI) BeanRegistry.get(UI.class);

        System.out.println(ui.calculate(2,3));
    }


    public static void main(String[] args){
      Application application = new Application();

      application.start();

    }
}
