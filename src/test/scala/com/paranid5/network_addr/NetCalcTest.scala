package com.paranid5.network_addr

import com.paranid5.network_addr.properties.*
import org.scalatest.funsuite.AnyFunSuite

private val Input:            String = "10.90.137.244/22"
private val Ip:               String = "10.90.137.244"
private val Port:             Int    = 22
private val SubnetMask:       String = "255.255.252.0"
private val Wildcard:         String = "0.0.3.255"
private val NetworkAddress:   String = "10.90.136.0"
private val BroadcastAddress: String = "10.90.139.255"
private val MinHostAddress:   String = "10.90.136.1"
private val MaxHostAddress:   String = "10.90.139.254"
private val NumberOfHosts:    Int    = 1022

class NetCalcTest extends AnyFunSuite:
  test("correct input"):
    assert(isCorrectInput(Input))

  test("subnet mask"):
    assert(subnetMask(Port) == SubnetMask)

  test("wildcard"):
    assert(wildcard(Port) == Wildcard)

  test("network address"):
    assert(networkAddress(Ip, SubnetMask) == NetworkAddress)

  test("broadcast address"):
    assert(broadcastAddress(Ip, Wildcard) == BroadcastAddress)

  test("min host address"):
    assert(minHostAddress(NetworkAddress) == MinHostAddress)

  test("max host address"):
    assert(maxHostAddress(BroadcastAddress) == MaxHostAddress)

  test("number of hosts"):
    assert(numberOfHosts(Port) == NumberOfHosts)