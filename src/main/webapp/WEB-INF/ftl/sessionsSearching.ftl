<html lang="">
<head>
    <meta charset="UTF-8">
    <title>Session searching</title>
    <script type="application/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="application/javascript">
        $(document).ready(function () {
            $("#searchField").keyup(function (e) {
                $("#result").html('');
                e.preventDefault();

                let url = "/cinema/sessions/search"
                let rq = $("#searchField").val();

                $.ajax({
                    type: "GET",
                    url: url,
                    contentType: "application/json",
                    dataType: "json",
                    data: {filmName: rq},
                    success: function (data) {
                        $.each(data.sessions, function (key, value) {
                            $("#result").append('<div>' +
                                '<p><img src="data:image/png;base64,' + value.film.posterUrl + '" width="150" height="200"></p>' +
                                '<p>' + value.dateTime + '</p>' +
                                '<p><a href="/cinema/sessions/' + value.id + '">' + value.film.name + '</a></p>' +
                                '</div>')
                        });
                    },
                    error: function (jqXhr, textStatus, errorMessage) {
                        console.log("Error", errorMessage);
                        $("#result").append('<p class="error-msg">Not found any sessions</p>');
                    }
                });
            })
        })
    </script>
</head>
<body>
<input class="search" name="filmName" type="text" placeholder="Search..." id="searchField">
<br>
<div class="list-of-film" id="result">

</div>
<#--<script type="application/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>-->
<#--<script src="../js/app.js"></script>-->
</body>
</html>