$("btnCadastro").click(function(event)){
    event.preventDefault();
    $.get("/cadastro", function(data){
    $(".container").html(data);
    history.pushState({},'',"/cadastro");
    $("#enviar").click(validaEnvio);
    });

    )}
