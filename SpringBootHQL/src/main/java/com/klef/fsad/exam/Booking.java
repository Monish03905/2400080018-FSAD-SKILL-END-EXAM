package com.klef.fsad.exam;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Booking – JPA Entity
 * Table  : booking  (auto-created in fsadendexam)
 * Package: com.klef.fsad.exam
 */
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String name;

    @Column(name = "booking_date", nullable = false)
    private LocalDate date;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "room_type", length = 50)
    private String roomType;

    @Column(name = "num_guests")
    private int numberOfGuests;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    // ── Constructors ─────────────────────────────────────────────

    public Booking() {}

    public Booking(String name, LocalDate date, String status,
                   String roomType, int numberOfGuests,
                   double totalAmount, String contactEmail) {
        this.name           = name;
        this.date           = date;
        this.status         = status;
        this.roomType       = roomType;
        this.numberOfGuests = numberOfGuests;
        this.totalAmount    = totalAmount;
        this.contactEmail   = contactEmail;
    }

    // ── Getters & Setters ────────────────────────────────────────

    public int getId()                     { return id; }
    public void setId(int id)              { this.id = id; }

    public String getName()                { return name; }
    public void setName(String name)       { this.name = name; }

    public LocalDate getDate()             { return date; }
    public void setDate(LocalDate date)    { this.date = date; }

    public String getStatus()              { return status; }
    public void setStatus(String status)   { this.status = status; }

    public String getRoomType()            { return roomType; }
    public void setRoomType(String r)      { this.roomType = r; }

    public int getNumberOfGuests()         { return numberOfGuests; }
    public void setNumberOfGuests(int n)   { this.numberOfGuests = n; }

    public double getTotalAmount()         { return totalAmount; }
    public void setTotalAmount(double a)   { this.totalAmount = a; }

    public String getContactEmail()        { return contactEmail; }
    public void setContactEmail(String e)  { this.contactEmail = e; }

    // ── toString ─────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format(
            "Booking { id=%-3d | name=%-20s | date=%s | status=%-12s" +
            " | room=%-8s | guests=%d | amount=%.2f | email=%s }",
            id, name, date, status, roomType,
            numberOfGuests, totalAmount, contactEmail
        );
    }
}
