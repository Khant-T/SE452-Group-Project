package com.four.simple.task;

import java.util.List;

import com.four.simple.checklist.Checklist;
import com.four.simple.checklist.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    @OneToOne
    private Status status;

    @OneToMany
    private List<Subtask> subtasks;

    @ManyToOne
    @ToString.Exclude
    private Checklist checklist;
}
