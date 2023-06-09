package com.four.simple.task;

import java.util.List;

import com.four.simple.checklist.Checklist;
import com.four.simple.checklist.Status;

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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    // @Column(name = "status_id")
    // private long statusId;
    @OneToOne(cascade = CascadeType.ALL)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subtask> subtasks;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Checklist checklist;
}
