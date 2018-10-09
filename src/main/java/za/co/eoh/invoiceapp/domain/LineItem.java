package za.co.eoh.invoiceapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "INVOICE_ITEM")
public class LineItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id ;
    @Min(0)
    @NotNull
    @Column(name = "INVOICE_QUANTITY")
    private Long invoiceQuantity;
    @NotNull
    @Column(name = "ITEM_DESCRIPTION")
    private String description;
    @Min(0)
    @NotNull
    @Column(name = "UNIT_PRICE")
    private Double unitPrice;
    @NotNull
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_INVOICE_ID"))
    @Column(name = "FK_INVOICE_ID")
    private String invoiceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceQuantity() {
        return invoiceQuantity;
    }

    public void setInvoiceQuantity(Long invoiceQuantity) {
        this.invoiceQuantity = invoiceQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getLineItemPrice() {
        Double lineItemPrice = getUnitPrice() * getInvoiceQuantity();
        BigDecimal price = new BigDecimal(lineItemPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
        return price;
    }
}