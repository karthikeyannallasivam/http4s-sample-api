package com.example.quickstart

import cats.effect.Effect
import io.circe.Json
import io.circe.syntax._

import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

import io.circe.generic.auto._

import cats._
import cats.effect._
import cats.implicits._

import org.http4s._

class HelloService[F[_]: Effect] extends Http4sDsl[F] {

  val service: HttpService[F] = {
    HttpService[F] {
      case GET -> Root / "external" / "users" / id =>
        Ok(getUser(id))
      case GET -> Root / "api" / "users" / id =>
        Ok(Client.getUser(id).map(UserEncoder.encode(_)).unsafeRunSync)
    }
  }

  private def getUser(id: String): Json =
    ExternalUser(id, "Aljon", "Aniban", "Mr").asJson
}

case class ExternalUser(id: String, firstName: String, lastName: String, title: String)
