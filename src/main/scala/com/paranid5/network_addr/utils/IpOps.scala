package com.paranid5.network_addr.utils

import scala.annotation.targetName

extension (ip: String)
  private def nums: List[Int] =
    ip.split("\\.").map(_.toInt).toList

  private def toBinary: String =
    def fillToIpPart(part: String): String =
      "0".repeat(MaskPartLen - part.length) + part

    def partToBinary(part: Int): String =
      fillToIpPart(Integer.toString(part, 2))

    ip.nums map partToBinary mkString ""

  private def combined: Long =
    java.lang.Long.parseLong(ip.toBinary, 2)

  @targetName("binAnd")
  def #&#(other: String): String =
    (ip.nums zip other.nums)
      .map(_ & _)
      .map(_.toString)
      .mkString(".")

  @targetName("binOr")
  def #|# (other: String): String =
    (ip.nums zip other.nums)
      .map(_ | _)
      .map(_.toString)
      .mkString(".")

  def binInc: String =
    (ip.combined + 1).toIpString

  def binDec: String =
    (ip.combined - 1).toIpString

extension (ipNum: Long)
  private def toIpString =
    f"${ipNum >> 24}.${(ipNum & 0xFFFFFF) >> 16}.${(ipNum & 0xFFFF) >> 8}.${ipNum & 0xFF}"

extension (rawIp: String)
  def toIpFormat: String =
    rawIp
      .grouped(8)
      .map(Integer.parseInt(_, 2))
      .mkString(".")
