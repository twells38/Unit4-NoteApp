package com.devmountain.noteApp.entities;
import com.devmountain.noteApp.dtos.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="Notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Note(NoteDto noteDto){
       if(noteDto.getBody() != null){
           this.body = noteDto.getBody();
       }
    }
}

/*Lombok is pretty powerful, and it can simplify the code to make it easier to read.
Just know the @Data annotation in large applications can have memory overhead issues and there are other annotations you can use to replace @Data with.
IntelliJ as well as the Lombok documentation are great resources to learn more*/

/*At this point let’s show you a nifty dependency we included when we initialized our project.
The dependency is called Lombok and looks at the member variables and can generate all of our getters and setters for us,
as well as the constructors all through 3 annotations.
Underneath the @Table annotation we can add the following: @Data, @AllArgsConstructor, @NoArgsConstructor and it simplifies our class down to the following:*/

/*@ManyToOne creates the association within Hibernate*/

/*@JsonBackReference prevents infinite recursion when you deliver the resource up as JSON through the RESTful API endpoint you will create*/
