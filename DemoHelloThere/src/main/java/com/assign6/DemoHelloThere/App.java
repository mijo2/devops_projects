package com.assign6.DemoHelloThere;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
        Mobile mb = (Mobile) factory.getBean("mobile");
    }
}
