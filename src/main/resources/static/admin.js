async function loadEnquiries() {
    const status = document.querySelector("#status");
    const table = document.querySelector("#enquiryTable");

    status.textContent = "Loading enquiries...";
    table.innerHTML = "";

    try {
        const response = await fetch("/api/enquiries");

        if (!response.ok) {
            throw new Error("Unable to load enquiries");
        }

        const enquiries = await response.json();

        if (enquiries.length === 0) {
            status.textContent = "No enquiries received yet.";
            return;
        }

        enquiries.forEach(enquiry => {
            const row = document.createElement("tr");

            [
                enquiry.id,
                enquiry.name,
                enquiry.email,
                enquiry.company || "-",
                enquiry.service || "-",
                enquiry.message,
                new Date(enquiry.createdAt).toLocaleString()
            ].forEach(value => {
                const cell = document.createElement("td");
                cell.textContent = value;
                row.appendChild(cell);
            });

            table.appendChild(row);
        });

        status.textContent =
            `${enquiries.length} customer enquiries found.`;

    } catch (error) {
        status.textContent = "Error loading enquiries.";
        console.error(error);
    }
}

loadEnquiries();
