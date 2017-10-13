package com.deciphernow.server

import com.deciphernow.server.{ config => serverConfig }
import com.deciphernow.announcement.{ config => announcementConfig }

/**
  *
  */
object GMFNetworkConfigurationResolver {

  var haveAnnouncementPoints = false;
  /*
  - 1 : Set ports && hostname via : com.deciphernow.server.config.{admin,http,https,thrift} ports.
  - 2 : Look up for env and if exist && have values ... override [1]
  - 3 : if 'service.forward' set then over ...          overrice [2]
  - 4 : if 'os.env' set && have values ...              override [3]
   */
  // todo: do I make the ports 'Int' && if not set then -1?
  var hostname   : String = _

  var bindAdminPort  : String = _
  var bindHttpPort   : String = _
  var bindHttpsPort  : String = _
  var bindThriftPort : String = _

  var announceHostname   : String = _

  var announceAdminPort  : String = _
  var announceHttpPort   : String = _
  var announceHttpsPort  : String = _
  var announceThriftPort : String = _

  def getHostname = hostname

  def getBindAdminPort = bindAdminPort
  def getBindHttpPort = bindHttpPort
  def getBindHttpsPort = bindHttpsPort
  def getBindThriftPort = bindThriftPort

  def getAnnounceAdminPort = announceAdminPort
  def getAnnounceHttpPort  = announceHttpPort
  def getAnnounceHttpsPort = announceHttpsPort
  def getAnnounceThriftPort = announceThriftPort

  def resolveConfiguration = {
    resolveAnnouncementEnvironmentVars
    resolveAnnouncementConfiguration
    resolveBindConfiguration
  }

  // todo: if have useIpAddress ... only works if we are @ level 1.
  protected [this] def resolveBindConfiguration = {

    // if none are configured then bind && with these values.

    bindHttpPort = serverConfig.rest.httpPort.apply
    bindHttpsPort = serverConfig.rest.httpsPort.apply
    bindAdminPort = serverConfig.admin.port.apply
    bindThriftPort = serverConfig.thrift.port.apply

    if (!haveAnnouncementPoints) {
      // todo: announce the bind ports as announcment ports.
    }
    // todo: determine the ipAddress here.
    // if useIpAddress then set 'hostname' as IPAddress : Otherwise convert to 'IP' address.

    println("resolveLevelFourConfiguration")
  }

  // todo: ENV values
  protected [this] def resolveLevelThreeConfiguration = {
    println("resolveLevelThreeConfiguration")
    // if announcement NOT configured && have ENV here ... then announcement + binding are equal to these values.
  }

  // These are the default announcement point values ... if configured.
  /**
    *
    */
  protected [this] def resolveAnnouncementConfiguration = {
    if (!haveAnnouncementPoints) {
      val adminValue = configValue(announcementConfig.service.forward.adminPort.apply)
      val httpValue = configValue(announcementConfig.service.forward.httpPort.apply)
      val httpsValue = configValue(announcementConfig.service.forward.httpsPort.apply)
      val thriftValue = configValue(announcementConfig.service.forward.thriftPort.apply)

      haveAnnouncementPoints = ((adminValue.trim.length > 0) && (httpValue.trim.length > 0))

      if (haveAnnouncementPoints) {
        announceAdminPort = adminValue
        announceHttpPort = httpValue
        announceHttpsPort = httpsValue
        announceThriftPort = thriftValue
      }
    }
  }

  /**
    *
    */
  protected [this] def resolveAnnouncementEnvironmentVars = {
    val hostnameEnv = announcementConfig.os.env.hostname // todo: what to do?

    val adminValue  = envValue(announcementConfig.os.env.adminPort.apply)
    val httpValue   = envValue(announcementConfig.os.env.httpPort.apply)
    val httpsValue  = envValue(announcementConfig.os.env.httpsPort.apply)
    val thriftValue = envValue(announcementConfig.os.env.thriftPort.apply)

    haveAnnouncementPoints = ((adminValue.trim.length > 0 ) && (httpValue.trim.length > 0))

    if (haveAnnouncementPoints) {
      announceAdminPort  = adminValue
      announceHttpPort   = httpValue
      announceHttpsPort  = httpsValue
      announceThriftPort = thriftValue
    }
  }

  /**
    *
    * @param env
    * @return
    */
  protected [this] def envValue(env: Option[String]) = env.flatMap{value => sys.env.get(value)}.getOrElse("")

  /**
    * 
    * @param config
    * @return
    */
  protected [this] def configValue(config: Option[String]) = {
    config match {
      case Some(v) => v
      case None => ""
    }
  }


}
