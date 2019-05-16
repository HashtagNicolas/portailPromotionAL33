package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Reservation {

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name="date_of_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name="date_of_withdrawal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date withdrawalDate;

    @Column(name = "quantity_requested")
    private Double quantityRequested;

    @Embedded
    private Client client;

    public Reservation(Long reservationId, Date dateCreation, Double quantityRequested, Client client) {
        this.reservationId = reservationId;
        this.dateCreation = dateCreation;
        this.quantityRequested = quantityRequested;
        this.client = client;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public Double getQuantityRequested() {
        return quantityRequested;
    }

    public void setQuantityRequested(Double quantityRequested) {
        this.quantityRequested = quantityRequested;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
