package za.co.eoh.invoiceapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "INVOICE")
public class Invoice {

    @Id
    @Column(name = "INVOICE_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id ;
    @NotNull
    @Column(name = "INVOICE_CLIENT")
    private String client;
    @Min(0)
    @NotNull
    @Column(name = "VAR_RATE")
    private Long varRate;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "INVOICE_DATE")
    private Date invoiceDate;
    @OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LineItem> lineItems = new HashSet<LineItem>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVarRate() {
        return varRate;
    }

    public void setVarRate(Long varRate) {
        this.varRate = varRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getSubTotal() {
        double subTotalPrice = 0.0;
        for (LineItem lineItem : lineItems) {
            subTotalPrice += lineItem.getLineItemPrice().doubleValue();
        }

        return new BigDecimal(subTotalPrice);
    }

    public BigDecimal getVAT() {
        double VATRate = 0.15;
        double VATTotal = getSubTotal().doubleValue() * VATRate;

        return new BigDecimal(VATTotal).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTotal() {
        double totalPrice = getSubTotal().doubleValue() + getVAT().doubleValue();
        return new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
