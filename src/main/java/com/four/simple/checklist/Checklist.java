package com.four.simple.checklist;

import java.util.List;

import com.four.simple.task.Task;
import com.four.simple.workspace.Workspace;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Workspace workspace;

    @OneToMany
    private List<Status> statuses;

    @OneToMany
    private List<Task> tasks;
}
