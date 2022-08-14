<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Films</title>
</head>
<body>
<h1>Films</h1>
<div>
    <table>
        <tr>
            <th>Title</th>
            <th>Release year</th>
            <th>Age registration</th>
            <th>Description</th>
            <th>Poster</th>
        </tr>

        <#list films as film>
            <tr>
                <td>${film.title}</td>
                <td>${film.releaseYear}</td>
                <td>${film.ageRegistration}</td>
                <td>${film.description}</td>
                <td>${film.poster}</td>
            </tr>
        </#list>
    </table>
</div>
<br>
<br>
<input type="button" value="Add new Film"
       onclick="window.location.href = '/cinema/admin/panel/addFilm'"/>
</body>
</html>