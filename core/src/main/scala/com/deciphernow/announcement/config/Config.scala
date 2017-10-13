package com.deciphernow.announcement.config



/*
    Copyright 2017 Decipher Technology Studios LLC

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

import com.twitter.app.GlobalFlag
import com.deciphernow.announcement.config.Implicits.stringOptionFlaggable

// if these not set: fall back to 'service.forward'
package os.env {
  object hostname extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the 'hostname' or 'ipAddress'")
  object adminPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the admin port.")
  object httpPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the http port.")
  object httpsPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the https port.")
  object thriftPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the thrift port.")
}

// if not set: fall back to 'server.config.env':
package service.forward {
  object hostname extends GlobalFlag[Option[String]](None, "'hostname' or 'ipAddress' to be registered. If None then current hostname.")
  object adminPort extends GlobalFlag[Option[String]](None, "Admin port to announce.")
  object httpPort extends GlobalFlag[Option[String]](None, "HTTP port to announce.")
  object httpsPort extends GlobalFlag[Option[String]](None, "HTTPS port to announce.")
  object thriftPort extends GlobalFlag[Option[String]](None, "Thrift port to announce.")
}

// if 'server.config.env' not set: fall back to regular configuration.

