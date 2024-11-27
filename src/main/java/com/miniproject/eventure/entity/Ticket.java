package com.miniproject.eventure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tickets_id_gen")
    @SequenceGenerator(name = "tickets_id_gen", sequenceName = "tickets_id_seq", allocationSize = 1)
    @Column(name = "ticket_id")
    private Long ticketId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, foreignKey = @ForeignKey(name = "FK_event"))
    private Event event;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ticket_type"))
    private TicketType ticketType;

    @NotNull
    @DecimalMin("0.0")
    @Column(nullable = false)
    private Double price;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer totalCapacity;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer totalAvailable;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Constructors, getters, and setters
}

