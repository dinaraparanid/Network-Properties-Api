package com.paranid5.network_addr.entity

import io.circe.Encoder
import io.circe.syntax.*
import io.circe.literal.json

case class NetworkProperties(
  ip:               String,
  port:             Int,
  subnetMask:       String,
  wildcard:         String,
  networkAddress:   String,
  broadcastAddress: String,
  minHostAddress:   String,
  maxHostAddress:   String,
  numberOfHosts:    Int
)

object NetworkProperties:
  given networkPropertiesEncoder: Encoder[NetworkProperties] =
    Encoder.forProduct9(
      "ip",
      "port",
      "subnet_mask",
      "wildcard",
      "network_address",
      "broadcast_address",
      "min_host_address",
      "max_host_address",
      "number_of_hosts"
    ): p ⇒
      (
        p.ip,
        p.port,
        p.subnetMask,
        p.wildcard,
        p.networkAddress,
        p.broadcastAddress,
        p.minHostAddress,
        p.maxHostAddress,
        p.numberOfHosts
      )
