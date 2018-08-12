package py.com.poraplz.cursomc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class CursomcApplication implements ApplicationListener {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {

	}
}
