package za.co.eoh.invoiceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.eoh.invoiceapp.domain.Invoice;
import za.co.eoh.invoiceapp.domain.LineItem;
import za.co.eoh.invoiceapp.repository.InvoiceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Invoice> getAll() {
        return repository.findAll();
    }

    @Override
    public Invoice getInvoice(Long id) {
        return repository.getOne(id);
    }

    @Override
    @Transactional
    public void addInvoice(Invoice invoice) {
        Query query = em.createNativeQuery("INSERT INTO INVOICE (INVOICE_CLIENT, VAR_RATE, INVOICE_DATE) " +
                " VALUES(?,?,?)");
        query.setParameter(1, invoice.getClient());
        query.setParameter(2, invoice.getVarRate());
        query.setParameter(3, invoice.getInvoiceDate());
        query.executeUpdate();
        for (LineItem lineItem : invoice.getLineItems()) {
            Query query1 = em.createNativeQuery("INSERT INTO INVOICE_ITEM (INVOICE_QUANTITY, ITEM_DESCRIPTION, UNIT_PRICE, FK_INVOICE_ID) " +
                    " VALUES(?,?,?,(SELECT LAST_INSERT_ID() as id FROM INVOICE))");
            query1.setParameter(1, lineItem.getInvoiceQuantity());
            query1.setParameter(2, lineItem.getDescription());
            query1.setParameter(3, lineItem.getUnitPrice());
            query.executeUpdate();
        }
    }
}
