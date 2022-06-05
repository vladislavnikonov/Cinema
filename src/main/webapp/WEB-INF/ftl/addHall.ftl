<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Hall</title>
</head>
<body>
<#if error??>
    <p>${error}</p>
</#if>
<h1>Add Film</h1>
<form method="POST" action="/cinema/admin/panel/saveHall" modelattribute="${hall}">
    <label for="serialNumber" >Enter serial number: </label>
    <input type="number" name="serialNumber" required id="serialNumber"/>
    <label for="seatsNumber" >Enter number of seats: </label>
    <input type="number" name="seatsNumber" required id="seatsNumber"/>
    <input type="submit" value="Create hall"/>
</form>
</body>
</html>