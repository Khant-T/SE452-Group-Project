package com.four.simple.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "completed", nullable = false)
    private int completed;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Task task;
}
