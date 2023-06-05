package com.four.simple.nosql;

import com.four.simple.workspace.Workspace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "members")
public class Members {
    @Id
    private String id;
    private String name;
    private String workspace_Id;

    public Members(String name,String workspace_Id){
        this.name = name;
        this.workspace_Id = workspace_Id;
    }

}
