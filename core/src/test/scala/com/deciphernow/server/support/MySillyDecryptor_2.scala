package com.deciphernow.server.support

class MySillyDecryptor_2 extends Decryptor {
  override def decryptResource(string: String): String = {
    if (string.startsWith("-->{")) {
      "MySillyDecryptor_2"
    }
    else {
      string
    }
  }
}
