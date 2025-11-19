package model;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.UUID;

@Builder
@Data
public class Payment {
    private UUID id;
    private UUID studentId;
    private Double amount;
    private Time paymentDate;
    private Status status;
    private PaymentType paymentType;
    public enum PaymentType {
        BILL,BANK
    }
    public enum Status {
        PAID,UNPAID
    }
}
