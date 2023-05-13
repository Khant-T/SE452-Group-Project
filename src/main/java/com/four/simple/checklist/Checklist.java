package com.four.simple.checklist;

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
@Table(name = "Lists")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;

    @JoinColumn(name = "workspace_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Workspace workspaceId;
}
