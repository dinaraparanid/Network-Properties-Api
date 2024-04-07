package com.paranid5.network_addr

import com.paranid5.network_addr.utils.*

private def isCorrectInput(input: String): Boolean =
  def isCorrectIp(ip: String): Boolean =
    val List(fi, se, th, fo) = ip.split("\\.").toList

    def impl: Option[Unit] =
      for
        fiInt ← fi.toIntOption if IpPartRange contains fiInt
        seInt ← se.toIntOption if IpPartRange contains seInt
        thInt ← th.toIntOption if IpPartRange contains thInt
        foInt ← fo.toIntOption if IpPartRange contains foInt
      yield ()

    impl.isDefined

  def isCorrectPort(port: String): Boolean =
    port.toIntOption.isDefined

  if !(InputPattern matches input) then
    return false

  val (ip, port) = splitIpInput(input)
  isCorrectIp(ip) && isCorrectPort(port)

private def splitIpInput(input: String): (String, String) =
  val List(ip, port) = input.split("/").toList
  (ip, port)

private def splitAndMapIpInput(input: String): (String, Int) =
  val List(ip, port) = input.split("/").toList
  (ip, port.toInt)