            $(document).ready(function () {
                $.ajax({
                    url: "http://localhost:8080/api/courseList"
                }).then(function (data) {

                    var select = $("#monSelect");

                    $.each(data, function (index) {
                        select.append('<option value="' + data[index].course_id + '">' + data[index].title + '</option>');
                    });
                });
            });



