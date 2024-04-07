package com.paranid5.network_addr.properties

import com.paranid5.network_addr.entity.NetworkProperties
import com.paranid5.network_addr.utils.*

def networkProperties(ip: String, port: Int): NetworkProperties =
  val subnet     = subnetMask(port)
  val wldcrd     = wildcard(port)
  val netAddr    = networkAddress(ip, subnet)
  val brdcstAddr = broadcastAddress(ip, wldcrd)
  val minHstAddr = minHostAddress(netAddr)
  val maxHstAddr = maxHostAddress(brdcstAddr)
  val hostsNum   = numberOfHosts(port)

  NetworkProperties(
    ip               = ip,
    port             = port,
    subnetMask       = subnet,
    wildcard         = wldcrd,
    networkAddress   = netAddr,
    broadcastAddress = brdcstAddr,
    minHostAddress   = minHstAddr,
    maxHostAddress   = maxHstAddr,
    numberOfHosts    = hostsNum
  )

def subnetMask(port: Int): String =
  ("1".repeat(port) + "0".repeat(MaskLen - port)).toIpFormat

def wildcard(port: Int): String =
  ("0".repeat(port) + "1".repeat(MaskLen - port)).toIpFormat

def networkAddress(ip: String, subnetMask: String): String =
  ip #&# subnetMask

def broadcastAddress(ip: String, wildcard: String): String =
  ip #|# wildcard

def minHostAddress(networkAddress: String): String =
  networkAddress.binInc

def maxHostAddress(broadcastAddress: String): String =
  broadcastAddress.binDec

def numberOfHosts(port: Int): Int =
  (BigInt(2).pow(MaskLen - port) - 2).toInt
