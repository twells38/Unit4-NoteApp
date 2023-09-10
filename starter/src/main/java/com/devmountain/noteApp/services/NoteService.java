package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NoteDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    //adding a note
    @Transactional
    void addNote(NoteDto noteDto, Long userId);

    //for deleting a note
    @Transactional
    void deleteNoteById(Long noteId);

    //for updating note
    @Transactional
    void updateNoteById(NoteDto noteDto);

    //for finding all notes by User. It requires you to stream the List<Note> that gets returned from the repository into their NoteDto counterparts to be sent out.
    List<NoteDto> getAllNotesByUserId(Long userId);

    //finally this method is for getting a Note by the Note "id"
    Optional<NoteDto> getNoteById(Long noteId);
}
