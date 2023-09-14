window.onload = function(){
    fetch("/odontologo")
    .then(function(respuesta){
        return respuesta.json();
    })
    .then(function(informacion) {
        console.log(informacion.data)
    })
}