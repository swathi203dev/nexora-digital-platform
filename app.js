const form = document.querySelector("#enquiryForm");

const formStatus = document.querySelector("#formStatus");


form.addEventListener("submit", async (event) => {

    event.preventDefault();

    formStatus.textContent = "Sending enquiry...";

    const formData = new FormData(form);

    const customerData = Object.fromEntries(
        formData.entries()
    );

    try {

        const response = await fetch("/api/enquiries", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(customerData)

        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error("Enquiry submission failed");
        }

        formStatus.textContent =
            "Thank you! Your enquiry was received.";

        form.reset();

    } catch (error) {

        formStatus.textContent =
            "Unable to submit enquiry. Please try again.";

        console.error(error);

    }

});