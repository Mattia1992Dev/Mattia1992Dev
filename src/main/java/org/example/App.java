package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.naming.Context;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("bean.xml");
        Ram mioPc = context.getBean("ramPc",Ram.class);
        System.out.println("Ecco i dati del mio PC");
       System.out.println("Marca " + mioPc.getComputer().getMarca());
        System.out.println("Capacità HD: " + mioPc.getComputer().getHdCapacita());
       System.out.println("Processore: "+ mioPc.getComputer().getProcessore());

        System.out.println("Ram: dimensione " + mioPc.getDimensione());
        System.out.println("Ram: Tipo "+ mioPc.getTipo());
        System.out.println("nome: " + mioPc.getNome());

        context.close();
    }
}