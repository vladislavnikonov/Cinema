<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Session</title>
</head>
<body>
<h2>${info.film.title}</h2>
<p><img src="data:image/jpg;base64,${info.film.poster}" alt="Avatar"></p>
<p> Release year: ${info.film.releaseYear} </p>
<p> Age registration: ${info.film.ageRegistration}<p/>
<p> Description: ${info.film.description}<p/>
<p> Hall: ${info.hall.serialNumber}<p/>
<p> Seats number: ${info.hall.seatsNumber}<p/>
<p> Ticket cost: ${info.ticketCost}<p/>
</body>
</html>