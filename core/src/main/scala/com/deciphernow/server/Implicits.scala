package com.deciphernow.server


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

import java.io.File

import com.twitter.app.Flaggable


object Implicits {
  implicit val fileFlaggable: Flaggable[File] = new Flaggable[File] {
    override def default = None
    def parse(s: String) = new File(s)
    override def show(file: File) = file.toString
  }

  implicit val fileOptionFlaggable: Flaggable[Option[File]] = new Flaggable[Option[File]] {
    override def default = None
    def parse(s: String) = if (s != null && !s.trim.isEmpty) Some(new File(s)) else None
    override def show(file: Option[File]) = file.toString
  }

  implicit val stringOptionFlaggable: Flaggable[Option[String]] = new Flaggable[Option[String]] {
    override def default = None
    def parse(s: String) = if (s!=null) Some(s) else None
  }
}
