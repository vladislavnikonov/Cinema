<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sessions</title>
</head>
<body>
<#if error??>
    <p>${error}</p>
</#if>
<div class="header">
    <h1>Sessions</h1>
</div>
<div>
    <table class="table">
        <thead>
            <tr>
                <th>Hall</th>
                <th>Film</th>
                <th>Ticket cost</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <#list sessions as session>
                <tr>
                    <td>${session.hall.serialNumber}</td>
                    <td>${session.film.title}</td>
                    <td>${session.ticketCost}</td>
                    <td>${session.sessionDate?datetime?string.short}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
<div>
    <form action="/cinema/admin/panel/saveSession" method="post">
        <select name="selectedHall">
            <optgroup>
                <#list halls as hall>
                    <option value=${hall.hallId}>${hall.serialNumber}</option>
                </#list>
            </optgroup>
        </select>
        <select name="selectedFilm">
            <optgroup>
                <#list films as film>
                    <option value=${film.filmId}>${film.title}</option>
                </#list>
            </optgroup>
        </select>
        <input name="ticketCost" type="number" value="500" required>
        <input name="sessionDate" type="datetime-local" required>
        <input type="submit" value="Create session">
    </form>
</div>
</body>
</html>