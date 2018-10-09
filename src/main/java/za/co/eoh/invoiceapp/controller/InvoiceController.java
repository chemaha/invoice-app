package za.co.eoh.invoiceapp.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.eoh.invoiceapp.domain.Invoice;
import za.co.eoh.invoiceapp.service.IInvoiceService;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    @RequestMapping("/")
    public String index(Model model) {

        return "Home page";
    }

    @RequestMapping(value  = "/invoices", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAll();
        return invoices;
    }

    @RequestMapping(value  = "/invoices/{invoiceId}", method = RequestMethod.GET)
    public Invoice getInvoice(@PathVariable("invoiceId") Long invoiceId) {
        Invoice invoice = invoiceService.getInvoice(invoiceId);
        return invoice;
    }

    //Return list with added object
    @RequestMapping(value  = "/invoices", method = RequestMethod.POST)
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
        invoiceService.addInvoice(invoice);
        return ResponseEntity.ok(invoice);
    }
}

