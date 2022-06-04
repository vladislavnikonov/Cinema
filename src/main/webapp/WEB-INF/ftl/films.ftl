<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
            <form name="film" action="/admin/panel/addPoster" method="post">
                <tr>
                    <td>${film.title}</td>
                    <td>${film.releaseYear}</td>
                    <td>${film.ageRegistration}</td>
                    <td>${film.description}</td>
                    <td>${film.poster}</td>
                    <td>
                        <input type="hidden" value="${film.title}" name="title">
                        <input type="submit" value="Add Poster"}/>
                    </td>
                </tr>
            </form>
        </#list>
    </table>
</div>
<br>
<br>
<input type="button" value="Add new File"
       onclick="window.location.href = '/admin/panel/addNewFile'"/>
</body>
</html>