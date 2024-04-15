
async function Login() {

    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var resultText = document.getElementById("result");
    const requestBody = {
        email: email,
        password: password,
    }

    await fetch("http://localhost:8081/api/v1/login", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if(response.ok){
                window.location.href = "storeHomepage.html";
            }
            else{
               resultText.innerHTML = "email or password are incorrect or account is not enabled";
               resultText.style.color = "red";
            }
        })
        .then(data => console.log(data))
        .catch(err => console.log(err));
}