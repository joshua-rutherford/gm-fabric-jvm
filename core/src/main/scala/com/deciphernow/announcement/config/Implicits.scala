//package com.deciphernow.announcement.config
//
//import com.twitter.app.Flaggable
//
//object Implicits {
//
//  implicit val stringOptionFlaggable: Flaggable[Option[String]] = new Flaggable[Option[String]] {
//    override def default = None
//    def parse(s: String) = if (s!=null) Some(s) else None
//  }
//}
