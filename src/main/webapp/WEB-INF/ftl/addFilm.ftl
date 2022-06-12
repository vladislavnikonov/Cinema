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
<form enctype="multipart/form-data" method="POST" action="/cinema/admin/panel/saveFilm">
    <label for="title" >Enter title: </label>
    <input type="text" name="title" required id="title"/>
    <label for="releaseYear" >Enter year of release: </label>
    <input type="number" name="releaseYear" required id="releaseYear"/>
    <label for="ageRegistration" >Enter age restriction: </label>
    <input type="number" name="ageRegistration" required id="ageRegistration" />
    <label for="description" >Enter description: </label>
    <input type="text" name="description" required id="description" />
    <label for="file" >Choose poster: </label>
    <input type="file" name="file" id="file" accept=".jpg, .jpeg, .png"/>
    <input type="submit" value="Create film"/>
</form>
</body>
</html>