# Flink-clean-architecture-demo

[![Build Status](https://travis-ci.org/bmeriaux/flink-clean-architecture-example.svg?branch=master)](https://travis-ci.org/bmeriaux/flink-clean-architecture-example)

An example of clean architecture Flink and Spring Boot

## Foreword (french)

Cet example propose un packaging d'un job flink selon le pattern d'[**architecture hexagonale**](http://www.maximecolin.fr/uploads/2015/11/56570243d02c0_hexagonal-architecture.png) (également appelé Clean Architecture).
De ce fait, les principes [SOLID](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)) sont utilisés dans le code, notamment le [Dependency Inversion Principle](https://en.wikipedia.org/wiki/Dependency_inversion_principle) (à ne pas confondre avec la classique injection de dépendances avec Spring par exemple).

Il est divisé en 3 modules gradle: `application`, `core`, `job`
- `application`: l'application spring boot qui permet de piloter le job, ici depuis un endpoint web
- `core`: le code métier indépendant utilisable par l'`application` ou le `job`
- `job`: le(s) job(s) flink utilisant le code métier du `core`

chacun constitué de 3 packages: `domain`, `usecase` et `infrastructure` qui doivent respecter ces règles :
- `domain` contient le métier et son intelligence et n'a aucune dépendance vers l'extérieur : ni vers des frameworks (Hibernate par exemple), ni vers les packages `usecase` et `infrastructure`.
- `usecase` est un chef d'orchestre et va dépendre uniquement du `domain` pour répondre à des cas d'utilisation métier. `usecase` ne doit pas avoir de dépendances vers `infrastructure`.
- `infrastructure` contient toute la technique, et ne doit pas contenir de métier. `infrastructure` a des dépendances vers `domain`, `usecase` et les frameworks.

  

## Install

```
./gradlew assemble
```

## Test

```
./gradlew check
```

## Run

```
./gradlew bootRun
```
