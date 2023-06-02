package com.four.simple.checklist;

import java.util.List;

import com.four.simple.task.Task;
import com.four.simple.workspace.Workspace;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ToString.Exclude
    private Workspace workspace;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Status> statuses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;
}
