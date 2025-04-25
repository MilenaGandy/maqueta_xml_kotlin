# Instrucciones para ingresar a la Maqueta de Interfaz gráfica

## Requisitos Previos

* Tener **Android Studio** instalado en el computador.
* Tener el **Android SDK** configurado (generalmente se gestiona a través de Android Studio).
* Tener un **Emulador de Android** configurado y listo para usar (creado desde el Device Manager de Android Studio) O un **dispositivo físico Android** conectado con la depuración USB habilitada.
* Haber **descargado y descomprimido** los archivos del proyecto adjunto a este documento.

## Pasos para Ejecutar

1.  **Abrir el Proyecto:**
    * Iniciar Android Studio.
    * Seleccionar la opción "**Open**" o "**Open an Existing Project**" (NO "Import Project").
    * Navegar hasta la carpeta donde se descomprimió los archivos del proyecto.
    * Selecciona la **carpeta raíz** del proyecto (la que contiene archivos como `settings.gradle.kts` y la carpeta `app`).
    * Hacer clic en "**Open**" o "**OK**". Es posible que Android Studio te pregunte si confía en el proyecto; seleccionar "**Trust Project**".

2.  **Sincronización de Gradle:**
    * Android Studio comenzará automáticamente a sincronizar el proyecto con Gradle (`Gradle Sync`). Este proceso descarga las dependencias necesarias definidas en los archivos `build.gradle.kts`.
    * Esperar a que este proceso termine. Puede ver el progreso en la parte inferior de la ventana de Android Studio o en la pestaña "**Build**".
    * **Nota:** Si faltara alguna versión específica del SDK o de las Build Tools (el proyecto usa `compileSdk = 35` y `buildToolsVersion = "35.0.0"`), Android Studio generalmente mostrará un error con un enlace para instalar los componentes faltantes. Sigue esas instrucciones si aparecen.

3.  **Seleccionar Dispositivo:**
    * Asegúrase de que tu emulador esté iniciado o tu dispositivo físico esté conectado y reconocido por Android Studio.
    * En la barra de herramientas superior de Android Studio, selecciona el emulador o dispositivo deseado en el menú desplegable de dispositivos.

4.  **Ejecutar la Aplicación:**
    * Haz clic en el botón verde "**Run 'app'**" en la barra de herramientas superior (o ve al menú `Run` -> `Run 'app'`).

5.  **Instalación y Lanzamiento:**
    * Android Studio compilará el código, construirá la aplicación (APK) y la instalará en el dispositivo/emulador seleccionado.
    * La aplicación debería iniciarse automáticamente mostrando la **Pantalla de Bienvenida**.

## Pantalla de Bienvenida

Es la pantalla de inicio de la aplicación para usuarios no autenticados. Su función principal es presentar la identidad de la aplicación (logo/nombre) y dirigir al usuario hacia las dos acciones primarias: iniciar sesión (si ya tiene cuenta) o registrarse (si es nuevo).

* **Archivo de Layout:** `res/layout/activity_welcome.xml`
* **Activity Kotlin:** `MainActivity.kt`

**Funcionalidades Aplicadas:**

* El botón "**Iniciar Sesión**" dirige a la pantalla de login.

## Pantalla de Login

Es la pantalla que permite que usuarios ya registrados accedan a la aplicación mediante sus credenciales.

Para el ejercicio debe ingresar las siguientes credenciales ficticias:

* **Usuario:** `agente@itops.com` (*Nota: En el código implementamos `test@test.com`*)
* **Contraseña:** `12345678` (*Nota: En el código implementamos `12345`*)

* **Archivo de Layout:** `res/layout/activity_login.xml`
* **Activity Kotlin:** `LoginActivity.kt`

**Funcionalidades Aplicadas:**

* Validación de Credenciales (Ficticias)
* Verificación del formato del correo y la contraseña (*Nota: Solo se implementó validación de vacío y credenciales exactas*)
* Visualización de alertas personalizadas (*Nota: Se implementó con Toast y errores en TextInputLayout*)
* Al hacer clic en "**entrar**" dirige a vista principal de lista tickets.

## Pantalla Principal de Tickets

Es la pantalla central de la aplicación después del inicio de sesión. Presenta al usuario una vista general de los tickets de soporte.

* **Archivo layout XML:** `res/layout/activity_main_app.xml` (Define la estructura general con Toolbar, búsqueda, lista, FAB y barra inferior).
* **Archivo Kotlin:** `MainAppActivity.kt` (Controla la lógica, configura la lista, el buscador, el FAB y el botón de salir).
* **Archivo layout filas:** `res/layout/list_item_ticket.xml` (Define cómo se ve cada ticket individual en la lista).
* **Adaptador:** `TicketAdapter.kt` (Gestiona los datos y la creación/vinculación de las filas de la lista, implementa `Filterable`).
* **Modelo Datos:** `Ticket.kt` (Define la estructura de datos para un ticket).

**Funcionalidades Aplicadas:**

* Permite la búsqueda rápida de tickets por ID dentro de la lista mostrada.
* Permite al usuario cerrar la sesión y volver a la pantalla de bienvenida al dar clic en el ícono "**Exit**" en el header.
* Ofrece acceso rápido para la creación de un nuevo ticket (mediante el Botón de Acción Flotante - FAB "**+**").

*Nota. Tomado del desarrollo app del sistema helpdesk.*

## Pantalla Crear Nuevo Ticket

Es la pantalla ofrece al usuario una interfaz dedicada para registrar un nuevo ticket de soporte en el sistema.

* **Archivo layout XML:** `res/layout/activity_create_ticket.xml` (Define la estructura del formulario con Toolbar, ScrollView, campos de texto, Spinners y botones).
* **Archivo Kotlin:** `CreateTicketActivity.kt` (Controla la lógica de la pantalla, carga el layout y maneja la navegación básica de la Toolbar).

**Funcionalidades Aplicadas:**

* El icono de "**Salir**" en la Toolbar está funcional y navega de vuelta a la pantalla de Bienvenida.
