//grab form. username, password input for use later on fetch request

const registerForm = document.getElementById('register-form');
const registerUsername = document.getElementById('register-username');
const registerPassword = document.getElementById('register-password');

// we have a base set of headers that will be included with every request.
const headers ={
'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/v1/users'
// create a function that will handle Submitting the form.

const handleSubmit = async(e)=>{
   e.preventDefault()
   let bodyObj = {
       username : registerUsername.value,
       password: registerPassword.value
   }
   const response = await fetch(`${baseUrl}/register`,{
       method:"POST",
       body: JSON.stringify(bodyObj) //convert javascript value to JSON string
       headers: headers
   })
   .catch(err=> console.err(err.message))
   const responseArr = await response.json()
   if(response.status === 200){
   window.location.replace(responseArr[0])
   }
}
registerForm.addEventListener("submit", handleSubmit);