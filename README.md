Usamos como base el App que envio el profesor como apoyo, para luego ir agregando y quitando funciones segun lo requerido, como primer cambio quitamos la opcion para 
poder sacar fotos con la camara trasera del dispositico solo dejando sacar fotograficas abriendo directamente la camara frontal para una posible foto de perfil.
Luego de eso creamos la herramienta/boton o funcion para poder crear un evento en el calendario del dispositivo, en este caso algo predeterminado como una reunion, para todo esto usamos android 15 y la version 8.13.0 de AGP

1. Abrir pagina web especifica: en este caso hacemos que el codigo abra la pagina web de nuestro proyecto en github
  <img width="1382" height="969" alt="image" src="https://github.com/user-attachments/assets/6bcfdeff-99ae-466e-b6cc-d6a47ad17c38" />

2.Enviar Correo Electronico: usamos el que ya venia con el proyecto base
  <img width="1383" height="969" alt="image" src="https://github.com/user-attachments/assets/e5a3ed92-7475-40f7-8995-75a321cd2c17" />

3.Agregar un Evento al calendario: Al tocar el boton de agregar un evento al calendario, esta abria tu app de calendario de tu celular agregando automaticamente un evento llamado reunion del proyecto
  <img width="1374" height="969" alt="image" src="https://github.com/user-attachments/assets/6de74e38-592e-45ee-a497-2333da2c2e3a" />

4.Seleccioanr Imagen de la galeria: al abrir la vista de la camara mostrara la opcion de seleccionar una imagen desde tu galeria o tomarla tu con tu camara frontal (es lo siguiente)
  <img width="1370" height="965" alt="image" src="https://github.com/user-attachments/assets/94741e3f-a5c6-4565-93f0-2f48298a7d93" />

5.Tomar fotografia con la camara frontal: esta seria un dos en uno, lo hice de manera que al tocar la opcion de tomar foto te lleve a otra vista donde veas una preview de lo que ve tu camara frontal y un boton para sacar la foto, foto la cual se guardara en la galeria
  <img width="1375" height="998" alt="image" src="https://github.com/user-attachments/assets/aeb3b22f-8a1c-4040-9f5d-6652162cd146" />

// Crear APK desde el Android studio
1. Ir al a la barra de herramientas de android studio (Main Menu ALT+\)
2. Dirigirse a la seccion Build
3. Abrir la opcion "Generate App Bundles or APKs y dentro de ella seleccionas "Generate APKs"
4. El APK del app se encontrara en la direccion dentro de la carpeta de tu proyecto AndroidStudioProjects/*Nombre de tu App*/app/build/outputs/apk/debug/
