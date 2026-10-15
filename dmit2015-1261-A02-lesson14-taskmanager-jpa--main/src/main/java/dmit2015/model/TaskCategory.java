package dmit2015.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import net.datafaker.Faker;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;


@Entity
//@Table(schema = "CustomSchemaName", name="CustomTableName")
@Getter
@Setter
public class TaskCategory implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    // @Column(name = "ColumnName", nullable = false)
    private String id;


    @NotBlank
    @Column (nullable = false, unique = true)
    private  String name;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks= new ArrayList<>();


    public TaskCategory(){

    }

    public TaskCategory(String name){
        this.name= name;
    }



}