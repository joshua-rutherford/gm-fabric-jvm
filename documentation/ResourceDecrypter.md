# Overview


If value resources have to be encrypted / decrypted,
Though String resources may be encrypted within the __gm-fabric__ it is explicitly coded to only decrypt passwords for keystore/truststores. The default decryptor is the NoDecryptor.

# Provided decryptors

| Name | Description |
| ---- | ---- |
| NoDecryptor | Default behavior. String in == String out. |


## Configuring a different Decryptor

To enable a different decryptor, the fully qualified class name must be specified to __com.deciphernow.server.config.resources.decryptClass__ in __etc/parameters.config__ as follows:

    -com.deciphernow.server.config.resources.decryptClass=a.b.c.MyNewDecryptor
        

### JAVA : Accessing the decyrptor
The following code example is how to load and access the decryptor in Java.

    try {
        Decryptor decryptor = DecryptorManager.class.newInstance().getInstance();
        decryptedKeyPass    = decryptor.decryptResource(keystorePassword);
        decryptedTrustPass  = decryptor.decryptResource(truststorePassword);
    } 
    catch ( Exception e ) {
        String msg = "Unable to decrypt password(s)";
        LOG.error( msg, e );
        throw new IllegalArgumentException( msg, e );
    }

## I want to implement my own decryptor

To implement your own decryptor create a Scala class with the following __Trait:__ `com.deciphernow.server.support.Decryptor` and implement the method `def decryptResource(string: String) : String`. Then just provide the configuration parameter pointing to your decryptor and add it to `parameters.conf`.

To retrieve your decryptor in __Scala__ do the following:


    import com.deciphernow.server.support.{DecryptorManager, Decryptor}
    
    ...
    
    lazy val decryptor : Decryptor = DecryptorManager.getInstance
    
    ...
    
    val decryptedValue : String = decryptor.decryptResource(EncryptedStringPassedHere)
    
    ...

## NO!!! I want to instantiate my decryptor in a different location.

If for some reasone you want to instantiate different decryptors within different microservice modules you can now pass a fully quallified class name to the `DecryptorManager` and have a new instance created.

    val myDecryptorManager = new DecryptorManager(Option("com.skittles.wombat.MyDecryptor"))
    val returnV1 = myDecryptorManager.getInstance.decryptResource("Some encrypted String")

