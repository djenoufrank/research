$(document).ready(function(){
    $("#monSelect").change(function(){
        $.ajax({
            url: "http://localhost:8080/api/courseList"
        }).then(function (data) {
            var selected=$("#monSelect").children("option:selected").val();//val cest la value qui a ete donner ds lisCourse.js
            $.each(data, function (index) {
                if(data[index].course_id==selected){
                    $("#detail").empty();
                    $("#detail").append('<span>sigle : ' + data[index].course_id + '</span></br>');
                    $("#detail").append('<span> intitulé : ' + data[index].title + '</span></br>');
                    $("#detail").append('<span> nb crédits : ' + data[index].credits + '</span></br>');
                }
            });                 
        });
    });
});