
async function Register() {

    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var resultText = document.getElementById("result");
    var password = document.getElementById("password").value;
    const requestBody = {
        firstName : firstName,
        lastName : lastName,
        email : email,
        password: password,
    }

    await fetch("http://localhost:8081/api/v1/registration", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if(response.ok){
                resultText.innerHTML = "successfully registered, check your email to activate your account";
                resultText.style.color = "blue";
            }
            else{
                resultText.innerHTML = "email already exists or not valid";
                resultText.style.color = "red";
            }
        })
        .then(data => console.log(data))
        .catch(err => console.log(err));
}