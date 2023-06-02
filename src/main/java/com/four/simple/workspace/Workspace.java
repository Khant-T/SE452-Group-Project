package com.four.simple.workspace;

import java.util.List;

import com.four.simple.checklist.Checklist;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "user_id", nullable = true)
    private long userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Checklist> checklists;
}
