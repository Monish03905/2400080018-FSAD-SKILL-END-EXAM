package com.klef.fsad.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * ClientDemo – HQL Operations on Booking Entity
 *
 * Implements CommandLineRunner so it runs automatically
 * when the Spring Boot application starts.
 *
 * Operations
 * ──────────
 *  I.  Insert records using persistent objects (EntityManager.persist)
 *  II. View ALL records using HQL with positional parameters (no WHERE column filter)
 *
 * Package  : com.klef.fsad.exam
 * Database : fsadendexam
 */
@Component
public class ClientDemo implements CommandLineRunner {

    // Spring injects the JPA EntityManager (wraps Hibernate Session)
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) {
        printBanner("KLEF FSAD END EXAM – Spring Boot Hibernate HQL Demo");

        insertBookings();   // Operation I
        viewAllBookings();  // Operation II
    }

    // =========================================================================
    //  I.  INSERT – persist Booking objects
    // =========================================================================
    @Transactional
    public void insertBookings() {

        printBanner("I. INSERT RECORDS (Persistent Objects)");

        List<Booking> bookings = Arrays.asList(
            new Booking("Arjun Reddy",
                        LocalDate.of(2025, 6, 10),
                        "Confirmed", "Double",  2,  4500.00, "arjun.reddy@email.com"),

            new Booking("Priya Sharma",
                        LocalDate.of(2025, 6, 12),
                        "Pending",   "Suite",   3,  9200.00, "priya.sharma@email.com"),

            new Booking("Ravi Kumar",
                        LocalDate.of(2025, 6, 15),
                        "Confirmed", "Single",  1,  2100.00, "ravi.kumar@email.com"),

            new Booking("Sneha Patel",
                        LocalDate.of(2025, 6, 18),
                        "Cancelled", "Double",  2,     0.00, "sneha.patel@email.com"),

            new Booking("Mohammed Ali",
                        LocalDate.of(2025, 6, 20),
                        "Confirmed", "Suite",   4, 12500.00, "mohammed.ali@email.com")
        );

        for (Booking b : bookings) {
            // transient → persistent state via EntityManager.persist()
            entityManager.persist(b);
            System.out.printf("  [INSERTED] %-20s (auto ID = %d)%n",
                              b.getName(), b.getId());
        }

        System.out.println("\n  ✔  All 5 booking records saved to 'fsadendexam'.");
    }

    // =========================================================================
    //  II. VIEW ALL – HQL with positional parameters
    // =========================================================================
    @Transactional
    public void viewAllBookings() {

        printBanner("II. VIEW ALL RECORDS (HQL with Positional Parameters)");

        // ── 2-A : Plain HQL – FROM Booking (no WHERE clause at all) ──────────
        System.out.println("  [HQL] FROM Booking  (plain – no WHERE)");
        System.out.println("  " + "─".repeat(108));

        TypedQuery<Booking> plainQuery =
                entityManager.createQuery("FROM Booking", Booking.class);

        printRecords(plainQuery.getResultList());

        // ── 2-B : HQL with a positional parameter (?1) ───────────────────────
        //         Fetches ALL rows (id >= 0 means every record)
        System.out.println("\n  [HQL] FROM Booking b WHERE b.id >= ?1  " +
                           "(positional param ?1 = 0  →  all records)");
        System.out.println("  " + "─".repeat(108));

        TypedQuery<Booking> hqlQuery = entityManager.createQuery(
                "FROM Booking b WHERE b.id >= ?1", Booking.class);

        hqlQuery.setParameter(1, 0);   // positional parameter ?1 bound to 0

        printRecords(hqlQuery.getResultList());
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void printRecords(List<Booking> list) {
        if (list.isEmpty()) {
            System.out.println("  (no records found)");
            return;
        }
        int sno = 1;
        for (Booking b : list) {
            System.out.printf("  [%2d] %s%n", sno++, b);
        }
        System.out.printf("%n  Total records fetched : %d%n", list.size());
    }

    private void printBanner(String title) {
        String line = "═".repeat(68);
        System.out.println("\n╔" + line + "╗");
        System.out.printf( "║  %-66s║%n", title);
        System.out.println("╚" + line + "╝");
    }
}
