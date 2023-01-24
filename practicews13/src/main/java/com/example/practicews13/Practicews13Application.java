package com.example.practicews13;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.practicews13.util.IOUtil.*;

@SpringBootApplication
public class Practicews13Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Practicews13Application.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsVal = appArgs.getOptionValues("dataDir");
		if(null != opsVal){
			createDir((String)opsVal.get(0));
		} else {
			System.exit(1);
		}
		app.run(args);
	}

}
