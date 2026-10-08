document.getElementById('registrationForm').addEventListener('submit', function (e) {
    e.preventDefault(); // Stop the browser from reloading the page

    // 1. Get the values from the form
    const name = document.getElementById('name').value;
    const phone = document.getElementById('phone').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const messageDiv = document.getElementById('message');

    // Clear old message
    messageDiv.textContent = "Loading...";
    messageDiv.style.color = "blue";

    // 2. Send the HTTP POST request to our Java Server
    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, phone, email, password })
    })
        .then(async response => {
            // 3. Handle the server's response
            const responseText = await response.text();

            if (response.ok) { // Status 201 Created
                messageDiv.textContent = responseText;
                messageDiv.style.color = "green";
                document.getElementById('registrationForm').reset(); // Clear the form
            } else { // Status 400 or 409
                messageDiv.textContent = responseText;
                messageDiv.style.color = "red";
            }
        })
        .catch(error => {
            messageDiv.textContent = "Failed to connect to the server.";
            messageDiv.style.color = "red";
        });
});
