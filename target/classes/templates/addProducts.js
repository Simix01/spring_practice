
async function addProduct() {

    var pName = document.getElementById("pName").value;
    var pDescription = document.getElementById("pDesc").value;
    var pPrice = parseFloat(document.getElementById("pPrice").value);
    var pQuantity = parseInt(document.getElementById("pQty").value);
    var resultText = document.getElementById("result");

    const requestBody = '{' +
        '"productName": ' + '"' + pName + '"' + ',' +
        '"productDescription": ' + '"' + pDescription + '"' + ',' +
        '"productPrice": ' + pPrice + ',' +
        '"productQuantity": ' + pQuantity + '}';

    await fetch("http://localhost:8081/api/v1/products", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        body: requestBody
    })
        .then(response => {
            if(response.ok){
                resultText.innerHTML = "product successfully added";
                resultText.style.color = "blue";
            }
            else{
                resultText.innerHTML = requestBody;
                resultText.style.color = "red";
            }
        })
        .then(data => console.log(data))
        .catch(err => console.log(err));
}