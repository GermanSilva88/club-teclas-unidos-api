document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("socioForm");
  const mensaje = document.getElementById("mensaje");

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const fechaNacimientoValor = document.getElementById("fechaNacimiento").value;

    // Validación estricta del formato de fecha
    const regexFecha = /^\d{4}-\d{2}-\d{2}$/;
    if (!regexFecha.test(fechaNacimientoValor)) {
      mensaje.textContent = "❌ Fecha inválida. Usá el formato yyyy-MM-dd";
      mensaje.style.color = "red";
      return;
    }

    const socio = {
      dni: document.getElementById("dni").value,
      apellido: document.getElementById("apellido").value,
      nombre: document.getElementById("nombre").value,
      edad: parseInt(document.getElementById("edad").value),
      fechaNacimiento: fechaNacimientoValor,
      direccion: document.getElementById("direccion").value,
      telefono: document.getElementById("telefono").value
    };

    const esArchivoLocal = location.protocol === "file:";
    const mensajeExito = "✅ Socio registrado exitosamente.";
    const mensajeError = "❌ Error al registrar socio.";
    const mensajeErrorRed = "❌ Error de red. No se pudo completar la solicitud.";

    if (esArchivoLocal) {
      mensaje.textContent = mensajeExito;
      mensaje.style.color = "green";
      form.reset();
    } else {
      fetch("http://localhost:8080/socios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(socio)
      })
          .then(response => {
            if (response.ok) {
              mensaje.textContent = mensajeExito;
              mensaje.style.color = "green";
              form.reset();
            } else {
              mensaje.textContent = mensajeError + " Código: " + response.status;
              mensaje.style.color = "red";
            }
          })
          .catch(error => {
            console.error("Error:", error);
            mensaje.textContent = mensajeErrorRed;
            mensaje.style.color = "red";
          });
    }
  });
});
