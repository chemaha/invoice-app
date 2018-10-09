package za.co.eoh.invoiceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class InvoiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceAppApplication.class, args);
    }
}
