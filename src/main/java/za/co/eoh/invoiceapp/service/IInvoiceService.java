package za.co.eoh.invoiceapp.service;

import za.co.eoh.invoiceapp.domain.Invoice;

import java.util.List;

public interface IInvoiceService {
    List<Invoice> getAll();
    Invoice getInvoice(Long id);
    void addInvoice(Invoice invoice);
}
