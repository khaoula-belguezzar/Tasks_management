$(document).ready(function () {
    $('.table .delBtn').on('click', function (event){
        event.preventDefault();
        var href=$(this).attr('href');
        $('#deleteModal #Confdel').attr('href', href);
        $('#deleteModal').modal();
    });
});