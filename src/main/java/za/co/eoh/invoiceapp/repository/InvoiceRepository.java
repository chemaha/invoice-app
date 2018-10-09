package za.co.eoh.invoiceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.eoh.invoiceapp.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
