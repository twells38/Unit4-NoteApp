// a logout method that clears that cookie
const cookieArr = document.cookie.split("=");
const userId = cookieArr[1];

//Dom Elements

const submitForm = document.getElementById("note-form");
const noteContainer = document.getElementById("note-container");

//modal elements

let noteBody = document.getElementById("note-body");
let updateNoteBtn = document.getElementById("update-note-button");

const headers ={
 'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/notes/"

// form that submits new notes
const handleSubmit = async (e)=>{
   e.preventDefault()
   let bodyObj ={
    body:document.getElementById("note-input").value
   }
   await addNote(bodyObj);
   document.getElementById('note-input').value = '' //clear input
 }

//addNote method
 async function addNote(obj){
   const response = await fetch(`${baseUrl}user/${userId}`,{
    method:"POST",
    body:JSON.stringify(obj),
    headers: headers
   })
   .catch(err => console.error(err.message))
   if(response.status == 200){
   return getNotes(userId);
   }
 }

//getNotes method to retrieve all the notes that are associated with the user when the page loads, create cards for them, and append them to a container to hold them
async function getNotes(userId){
 await fetch(`${baseUrl}user/${userId}`,{
  method:"GET",
  headers:headers
 })
 .then(response => response.json())
 .then(data => createNoteCards(data))
 .catch(err => console.error(err))
}

//to be able to update a note which will involve a separate GET request for just that note so we can populate our modal with it
async function getNoteById(noteId){
  await fetch(`${baseUrl}/${noteId}`, {
  method: "GET",
  headers:headers
  })
  .then(res => res.json())
  .then(data => populateModal(data))
  .catch(err => console.error(err.message))
}
async function handleNoteEdit(noteId){
  let bodyObj ={
       id:noteId,
       body: noteBody.value
  }
  await fetch(`${baseUrl}`,{
       method: "PUT",
       body:JSON.stringify(bodyObj),
       headers:headers
  })
  .catch(err => console.error(err))
  return getNotes(userId)
}

//handleDelete method to be able to delete a note
async function handleDelete(noteId){
    await fetch(`${baseUrl}/${noteId}`,{
    method: "DELETE",
    headers:headers
    })
    .catch(err => console.error(err))
    return getNotes(userId)
}

//a logout method that clears that cookie
function handleLogout(){
  let c = document.cookie.split(";");
  for(let i in c){
  document.cookie = /^[^=]+/.exec(c[i])[0]+'=;expires=Thu, 01 Jan 1970 00:00:00 GMT';
  }
}

//These methods utilized some helper functions that we made as well, the first you’ll see is called “createNoteCards” and it accepts an array of objects. It then loops through the array and creates a little note card for each item and appends it to our container for the notes.
const createNoteCards = (array) => {
    noteContainer.innerHTML = ''
    array.forEach(obj => {
        let noteCard = document.createElement("div")
        noteCard.classList.add("m-2")
        noteCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getNoteById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#note-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        noteContainer.append(noteCard);
    })
}

// “populateModal” method which accepts an object as an argument and uses that object to populate the fields within the modal as well as assign a custom “data-” tag to the “Save” button element
const populateModal = obj =>{
    noteBody.innerText = ''
    noteBody.innerText = obj.body
    updateNoteBtn.setAttribute('data-note-id', obj.id)
}

//invoke methods

getNote(userId)

submitForm.addEventListener("submit", handleSubmit)

updateNoteBtn.addEventListener("click", e=>{
  let noteId = e.target.getAttribute('data-note-id');
  handleNoteEdit(noteId);
})

<a class="btn btn-danger navbar-btn" href="./login.html" onclick="handleLogout()">Logout</a>