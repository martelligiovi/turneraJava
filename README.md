# Turnera

 ![Build](https://img.shields.io/badge/build-passing-brightgreen.svg) ![Cobertura de Pruebas](https://img.shields.io/badge/tests-100%25-success.svg)

## Descripción

Este proyecto es un sistema de administración de turnos para un centro médico. En este sistema se gestionan doctores, pacientes y turnos. Permite el uso de tres tipos de usuarios:

- Médico: Puede ver sus turnos.
- Paciente: Puede ver sus turnos.
- Administrador: Gestiona el CRUD (Crear, Leer, Actualizar, Eliminar) de las tres entidades (Pacientes, Médicos y Turnos) y también puede generar reportes.

## Tabla de Contenidos
- [Requisitos previos](#Requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Características](#características)
- [Documentación de la API](#documentación-de-la-api)
- [Autores](#autores)

## Requisitos previos

ninguno

### Configuración de la base de datos

ya viene con su BD

## Instalación

Instrucciones paso a paso sobre cómo instalar y configurar el proyecto.

```bash
# Clonar el repositorio
git clone https://github.com/martelligiovi/turneraJava.git

# Entrar al directorio del proyecto
cd turneraJava

```

## Uso

Para iniciar la aplicación, ejecute el siguiente comando en la raíz del proyecto:

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en http://localhost:8080.

## Características

- Gestión de publicaciones
- Gestión de comentarios
- API REST

## Documentación de la API

La documentación de la API se encuentra en el archivo `/API_DOCUMENTATION.md`.

## Contribución

Si desea contribuir al proyecto, por favor haga un fork del repositorio y proponga un pull request.
