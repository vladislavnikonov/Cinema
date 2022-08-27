<!DOCTYPE html>
<html lang="en">
<head>
    <title>Image</title>
</head>
<body>
<#if error??>
    <p>${error}</p>
</#if>
<img src="data:image/jpg;base64,${image}" alt="Avatar">
</body>
</html>