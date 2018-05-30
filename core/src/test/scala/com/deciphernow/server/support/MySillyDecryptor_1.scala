package com.deciphernow.server.support

class MySillyDecryptor_1 extends Decryptor {
  override def decryptResource(string: String): String = {
    if (string.startsWith("-->{")) {
      "MySillyDecryptor_1"
    }
    else {
      string
    }
  }
}
