package com.four.simple.task;

import java.util.List;

import com.four.simple.checklist.Checklist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @Column(name = "status_id")
    private long statusId;

    @OneToMany
    private List<Subtask> subtasks;

    @ManyToOne
    @ToString.Exclude
    private Checklist checklist;
}
