<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Halls</title>
</head>
<body>
<h1>Halls</h1>
<div>
    <table>
        <tr>
            <th>Serial number</th>
            <th>Number of seats</th>
        </tr>

        <#list halls as hall>
            <tr>
                <td>${hall.serialNumber}</td>
                <td>${hall.seatsNumber}</td>
            </tr>
        </#list>
    </table>
</div>
<br>
<br>
<input type="button" value="Add new Hall"
       onclick="window.location.href = '/cinema/admin/panel/addHall'"/>
</body>
</html>