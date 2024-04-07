package com.paranid5.network_addr.utils

import scala.util.matching.Regex

val MaskPartLen:  Int             = 8
val MaskLen:      Int             = MaskPartLen * 4
val InputPattern: Regex           = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/\\d{1,10}$".r
val IpPartRange:  Range.Inclusive = 0 to 255
