package citi.BC;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BCmain {

    @PostConstruct
    public void main(){
        BC bc=new BC();
        Thread thread=new Thread(bc);
        thread.start();
    }
}


