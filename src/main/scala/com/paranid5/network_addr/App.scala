package com.paranid5.network_addr

import cats.data.Kleisli
import cats.effect.*

import com.comcast.ip4s.{ipv4, port}

import com.paranid5.network_addr.entity.NetworkProperties.networkPropertiesEncoder
import com.paranid5.network_addr.properties.*

import org.http4s.*
import org.http4s.circe._
import org.http4s.dsl.io.*
import org.http4s.ember.server.EmberServerBuilder

object App extends IOApp:
  private def onGetProperties(input: String): IO[Response[IO]] =
    println(f"Request: $input")

    if !isCorrectInput(input) then
      return BadRequest("Invalid input")

    val (ip, port) = splitAndMapIpInput(input)
    Ok(networkPropertiesEncoder(networkProperties(ip, port)))

  private def netPropService: Kleisli[IO, Request[IO], Response[IO]] =
    HttpRoutes
      .of[IO]:
        case GET → (Root / "properties" / ip / port) ⇒ onGetProperties(f"$ip/$port")
        case _                                       ⇒ NotFound("Unknown request")
      .orNotFound

  override def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"3000")
      .withHttpApp(netPropService)
      .build
      .use(_ ⇒ IO.never)
      .as(ExitCode.Success)
