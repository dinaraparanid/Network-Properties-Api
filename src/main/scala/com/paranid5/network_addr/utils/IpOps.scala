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

  private def combined: Int =
    Integer.parseInt(ip.toBinary, 2)

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

extension (ipInt: Int)
  private def toIpString =
    f"${ipInt >> 24}.${(ipInt & 0xFFFFFF) >> 16}.${(ipInt & 0xFFFF) >> 8}.${ipInt & 0xFF}"

extension (rawIp: String)
  def toIpFormat: String =
    rawIp
      .grouped(8)
      .map(Integer.parseInt(_, 2))
      .mkString(".")
