document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("socioForm");
  const mensaje = document.getElementById("mensaje");

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const socio = {
      dni: document.getElementById("dni").value,
      apellido: document.getElementById("apellido").value,
      nombre: document.getElementById("nombre").value,
      edad: parseInt(document.getElementById("edad").value),
      fechaNacimiento: document.getElementById("fechaNacimiento").value,
      direccion: document.getElementById("direccion").value,
      telefono: document.getElementById("telefono").value
    };

    // Detectar si estás en local (sin servidor) o en localhost
    const esArchivoLocal = location.protocol === "file:";

    if (esArchivoLocal) {
      // Modo local: simula la respuesta sin llamar al backend
      mensaje.textContent = "✅ Socio registrado exitosamente.";
      mensaje.style.color = "green";
      form.reset();
    } else {
      // Modo con backend: hace la petición real
      fetch("http://localhost:8080/socios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(socio)
      })
          .then(response => {
            if (response.ok) {
              mensaje.textContent = "✅ Socio registrado exitosamente.";
              mensaje.style.color = "green";
              form.reset();
            } else {
              mensaje.textContent = "❌ Error al registrar socio.";
              mensaje.style.color = "red";
            }
          })
          .catch(error => {
            console.error("Error:", error);
            mensaje.textContent = "❌ Error de red.";
            mensaje.style.color = "red";
          });
    }
  });
});
