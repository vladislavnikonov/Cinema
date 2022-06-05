<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Film</title>
</head>
<body>
<#if error??>
    <p>${error}</p>
</#if>
<h1>Add Film</h1>
<form method="POST" action="/cinema/admin/panel/saveFilm" modelattribute="${film}">
    <label for="title" >Enter title: </label>
    <input type="text" name="title" required id="title"/>
    <label for="releaseYear" >Enter year of release: </label>
    <input type="number" name="releaseYear" required id="releaseYear"/>
    <label for="ageRegistration" >Enter age restriction: </label>
    <input type="number" name="ageRegistration" required id="ageRegistration" />
    <label for="description" >Enter description: </label>
    <input type="text" name="description" required id="description" />
    <label for="poster" >Choose poster: </label>
    <input type="file" name="poster" id="poster" accept=".jpg, .jpeg, .png"/>
    <input type="submit" value="Create film"/>
</form>
</body>
</html>