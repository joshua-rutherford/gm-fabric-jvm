package com.deciphernow.server.thrift


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

import java.net.InetSocketAddress

import com.deciphernow.server.support.{DecryptorManager, SupportUtils}
import com.deciphernow.server.tls.TlsServerUtil
import com.twitter.finagle.thrift.Protocols
import com.twitter.inject.app.App
import com.twitter.finagle.transport.Transport
import com.twitter.finagle.{ListeningServer, SimpleFilter, Thrift}
import com.twitter.logging.Logger
import com.deciphernow.server.{GMFNetworkConfigurationResolver, config => configuration}
import com.deciphernow.server.security.ClientCertificateServiceFactory
import com.deciphernow.server.support.{Decryptor, DecryptorManager}


/**
  *
  * @param filters
  * @param service
  */
class GMFabricThriftServer(filters: Seq[SimpleFilter[Array[Byte], Array[Byte]]], service: AnyRef)
  extends App {

  val log = Logger.get(getClass)
  var server : Option[ListeningServer] = None
  val serverPrefixLabel = "thrift"

  def getServer = server

  override def main() : Unit = {

    // DO NOT create a thrift server.
    if ((defaultThriftPort == "") || (defaultThriftPort == ":")) {
      log.ifInfo("Thrift port has been configured such that NO THRIFT server will be instantiated.")
      return
    }
    val stateSsl = SupportUtils.isSsl
    val serverLabel = "thrift"
    val statsFilter = new ThriftMethodStatsFilter(Protocols.binaryFactory(),"srv/" + serverLabel,"/method/")
    val newSequenceFilters = filters :+ statsFilter
    val compositeService = newSequenceFilters.foldRight(ThriftUtil.serverFromIface(service,Protocols.binaryFactory()))(_ andThen _)
    if (!stateSsl) {
      log.ifDebug("Creating thrift server without SSL.");
      server = Option(Thrift.server.withLabel(serverLabel)
                      .serve(new InetSocketAddress(defaultThriftPort.substring(1).toInt),
                      compositeService))
    }
    else {
      log.ifDebug("Creating thrift server with 2-Way SSL.")
      val clientCertificateServiceFactory = new ClientCertificateServiceFactory[Array[Byte],Array[Byte]](compositeService)
      server = Option(Thrift.server.withLabel(serverLabel).configured(Transport.TLSServerEngine(Option(()=>{TlsServerUtil.createTlsEngine(
        configuration.tls.keyStore().get,
        decrypter.decryptResource(configuration.tls.keyStorePass()),
        configuration.tls.trustStore().get,
        decrypter.decryptResource(configuration.tls.trustStorePass())
      )}))).serve(new InetSocketAddress(defaultThriftPort.substring(1).toInt), clientCertificateServiceFactory))
    }
  }

  def defaultThriftPort: String = configuration.thrift.port()

  lazy val decrypter : Decryptor = DecryptorManager.getInstance
}
