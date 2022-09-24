$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/api/movieList"
    }).then(function (data) {

        var select = $("#monSelect");

        $.each(data, function (index) {
            select.append('<option value="' + data[index].movie_id + '">' + data[index].title + '</option>');
        });
    });
});

