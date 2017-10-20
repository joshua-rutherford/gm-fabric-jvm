import com.twitter.app.{Flags, GlobalFlag}
import com.twitter.finagle.mux.FailureDetector.GlobalFlagConfig
import org.scalatest.{BeforeAndAfterEach, FlatSpec, FunSuite, Matchers}
import org.scalatest.junit.JUnitRunner
//import org.junit.runner.RunWith
import com.deciphernow.server.{config => serverConfig}
//@RunWith(classOf[JUnitRunner])
import com.deciphernow.server.GMFNetworkConfigurationResolver

class GMFNetworkConfigurationResolverSpec extends FlatSpec with Matchers with BeforeAndAfterEach {//extends FunSuite { //extends FlatSpec with Matchers {


//class GMFNetworkConfigurationResolverSpec extends FunSuite with BeforeAndAfterEach {

//
//  "t1 " should "do something " in {
//    val flag = new Flags("my", includeGlobal=true)
//
//    //flag.getAll()
//    //val answer : Seq[String] = flag.parseOrExit1(Array("-com.deciphernow.server.config.rest.httpPort"))
//    //flag.add(new GlobalFlag.type )
//    //val answer : Seq[String] = flag.parse(Array("-com.deciphernow.server.config.rest.httpPort=9999"))
//
//    println(serverConfig.rest.httpPort)
//    System.setProperty("com.deciphernow.xxx.server.config.rest.httpPort","3333")
//    println(serverConfig.rest.httpPort)
//    System.setProperty("com.deciphernow.server.config.rest.httpPort","3333")
//    println(System.getProperty("com.deciphernow.server.config.rest.httpPort"))
//    val answer : Seq[String] = flag.parse(Array("-com.deciphernow.server.config.rest.httpPort","3333"))
//    println("Answer => " + answer)
//    val answer2 : Seq[String] = flag.parseOrExit1(Array("-com.deciphernow.server.config.rest.httpPort","3333"))
//    println("Answer2 => " + answer2)
//
//    //GMFNetworkConfigurationResolver
//  }
//  "t1 " should "do something " in {
//    //GlobalFlagConfig
//
//    //val v1 = com.deciphernow.server.config.rest.httpPort.let(":60000")
//    val v2 = com.deciphernow.server.config.rest.httpPort.name = ":60000"
//
//    println("v1 = " + v2)
//  }
//
//  class X extends GlobalFlag[String] {
//    override def hashCode(): Int = super.hashCode()
//  }

  override def beforeEach() {
    println("beforeEach")
  }
//
//  def testBlah: Unit = {
//    System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
//    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
//    //System.getenv().putIfAbsent("ANNOUNCE_ADMIN_PORT",":7777")
//    //    System.getenv().put("ANNOUNCE_ADMIN_PORT",":7777")
//    System.getProperties.setProperty("ANNOUNCE_ADMIN_PORT",":7777");
//
//
//    GMFNetworkConfigurationResolver.resolveConfiguration
//    println("Announce adminPort = " + GMFNetworkConfigurationResolver.getAnnounceAdminPort)
//    println("Bind adminPort     = " + GMFNetworkConfigurationResolver.getBindAdminPort)
//    println("Bind httpPort      = " + GMFNetworkConfigurationResolver.getBindHttpPort)
//  }

//  class X1 {
//
//    def apply(): Unit = {
//      System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
//      System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
//      //System.getenv().putIfAbsent("ANNOUNCE_ADMIN_PORT",":7777")
//      //    System.getenv().put("ANNOUNCE_ADMIN_PORT",":7777")
//      System.getProperties.setProperty("ANNOUNCE_ADMIN_PORT",":00000");
//
//
//
//
//      GMFNetworkConfigurationResolver.resolveConfiguration
//      println("Announce adminPort = " + GMFNetworkConfigurationResolver.getAnnounceAdminPort)
//      println("Bind adminPort     = " + GMFNetworkConfigurationResolver.getBindAdminPort)
//      println("Bind httpPort      = " + GMFNetworkConfigurationResolver.getBindHttpPort)
//    }
//  }
//  "t0" should " blah " in {
//    val runtime = Runtime.getRuntime;
//
//    val environment = Array("ANNOUNCE_ADMIN_PORT=:00000")
//    //runtime.exec(new X1,environment)
//  }


  "t0" should " blah " in {

    System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.httpPort","ANNOUNCE_HTTP_PORT");

    System.getProperties.setProperty("com.deciphernow.server.config.os.env.adminPort","BIND_ADMIN_PORT")
    System.getProperties.setProperty("com.deciphernow.server.config.os.env.httpPort","BIND_HTTP_PORT")

    //System.getenv().putIfAbsent("ANNOUNCE_ADMIN_PORT",":7777")
    //    System.getenv().put("ANNOUNCE_ADMIN_PORT",":7777")
    //System.getProperties.setProperty("ANNOUNCE_ADMIN_PORT",":00000");

    //println("System::getProperties " + System.getProperties)
    println("System::env           " + sys.env)
    GMFNetworkConfigurationResolver.resolveConfiguration
    //println(System.getProperties.getProperty("com.deciphernow.announcement.config.os.env.adminPort"))

    println("Announce hostname  = " + GMFNetworkConfigurationResolver.getAnnounceHostname)
    println("Announce adminPort = " + GMFNetworkConfigurationResolver.getAnnounceAdminPort)
    println("Announce httpPort  = " + GMFNetworkConfigurationResolver.getAnnounceHttpPort)

    println("Bind adminPort     = " + GMFNetworkConfigurationResolver.getBindAdminPort)
    println("Bind httpPort      = " + GMFNetworkConfigurationResolver.getBindHttpPort)

    //
  }


//  "t1" should " blah " in {
//
////    val defaultPath = "/home/ghershfield/dev/projects/workspaces/latest-workspaces/decipher-now-workspaces/gth/gm-fabric-jvm/core/src/test/resources"
////    val p1 = defaultPath + "/parametersSpec001.config"
////    import scala.io.Source._
////    //val flagsAsArray = fromFile("./etc/parameters.config").getLines.toTraversable.toArray
////
////    //val v = this.getClass.getClassLoader.getResources("/home/ghershfield/dev/projects/workspaces/latest-workspace/decipher-now-workspace/gth/gm-fabric-jvm/core/src/test/resources/parametersSpec001.config")
//////    val v = this.getClass.getClassLoader.getResources(p1)
//////
//////    while (v.hasMoreElements) {
//////      println(v.nextElement())
//////    }
////
//////    println("GOING TO TRY AND LOAD THIS FILE::::")
////    val flagsAsArray = fromFile(p1).getLines.toTraversable.toArray
////
////    val length = flagsAsArray.length
////    var count = 0;
////    while (count < length) {
////      println("flag[" + count + "] = " + flagsAsArray.apply(count))
////      count = count + 1
////    }
////
////    val flag = new Flags("myapp.wombat.blah")
////    val i = flag("myapp.wombat.blah", 123, "iteration count")
////    val x = flag("a","help")
////    val mm = flag("com.deciphernow.server.config.rest.httpPort",":22225","HTTP Port")
////    x.parse("10")
////    println(flag.usage)
////    println(i)
////
////    val xxx = flag.parseArgs(flagsAsArray,false)
//////    println(flag.usage)
//////    println(i)
///////    println(xxx)
////    println(x)
////    println(mm)
////    println(mm.get.get)
//
//
//
//
//    sys.env.updated("ANNOUNCE_ADMIN_PORT",":7777")
//
//    {
//     // var m : util.Map[String,String] = System.getenv()
//      System
//      System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
//      System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
//      //System.getenv().putIfAbsent("ANNOUNCE_ADMIN_PORT",":7777")
//      //    System.getenv().put("ANNOUNCE_ADMIN_PORT",":7777")
//      System.getProperties.setProperty("ANNOUNCE_ADMIN_PORT",":00000");
//
//
//
//
//      GMFNetworkConfigurationResolver.resolveConfiguration
//      println("Announce adminPort = " + GMFNetworkConfigurationResolver.getAnnounceAdminPort)
//      println("Bind adminPort     = " + GMFNetworkConfigurationResolver.getBindAdminPort)
//      println("Bind httpPort      = " + GMFNetworkConfigurationResolver.getBindHttpPort)
//    }
//
//    System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
//    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
//    //System.getenv().putIfAbsent("ANNOUNCE_ADMIN_PORT",":7777")
////    System.getenv().put("ANNOUNCE_ADMIN_PORT",":7777")
//    System.getProperties.setProperty("ANNOUNCE_ADMIN_PORT",":00000");
//
//
//
//
//    GMFNetworkConfigurationResolver.resolveConfiguration
//    println("Announce adminPort = " + GMFNetworkConfigurationResolver.getAnnounceAdminPort)
//    println("Bind adminPort     = " + GMFNetworkConfigurationResolver.getBindAdminPort)
//    println("Bind httpPort      = " + GMFNetworkConfigurationResolver.getBindHttpPort)
////
////    println("flagsAsArray = " + flagsAsArray)
////
////    //val flags = new Flags(flagsAsArray,true)
////    val flags = new Flags("myHack",true)
////    flags.parseArgs(flagsAsArray,true)
////    val x = flags.getAll(true,this.getClass.getClassLoader)
////
////    //flags.add(new Flags(flagsAsArray.apply(0)).)
////    //val myFlag = new Flags(flagsAsArray.apply(0))
////    //println("myFlag =>" + myFlag.)
////
//////
//////    val nf1 : GlobalFlag = new GlobalFlag.(flagsAsArray.apply(0))
//////    println(nf1.apply
////
////
//////    val flags = new Flags()
//////    flag.parseArgs(flagsAsArray)
////    //println("resources = " + this.getClass.getClassLoader.getResources("resources/parametersSpec001.config"))
////    //fromFile("")
//
//  }

//  "t1 " should " blah " in {
//
//  }

}