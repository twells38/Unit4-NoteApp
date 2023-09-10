package com.devmountain.noteApp.dtos;

import com.devmountain.noteApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<NoteDto> noteDtoSet = new HashSet<>();

    public UserDto(User user){
        if(user.getId() != null){
            this.id = user.getId();
        }
        if(user.getUsername() != null){
            this.username = user.getUsername();
        }
        if(user.getUsername() != null){
            this.password = user.getPassword();
        }
    }
}

/*n the declaration of the class, we need to implement the Serializable interface to allow this class to be converted to a Bytestream and sent outside the application or stored in a log file.
Finally, we need to create a custom constructor that accepts a User object as its argument and maps the DTO object fields accordingly. This step provides an opportunity to include some conditional logic within the constructor to prevent null pointer exceptions,
you may hear this called “sanity checking” in your career as a software developer.
This is the class you should have ended up with, Note that we do not have any Entities stored within the DTO either as they have been replaced with the DTO version of the Entity.
Notice that the HashSet is not included in the constructor, this is a way we can avoid StackOverFlow errors later on down the line. If we were to include the Set inside the constructor we would end up with infinite recursion.
We have left the member variable there in case we have need to use the setter later on but it is highly unlikely we will need it*/
