send-mail-server
================

Simple example about sending mail on WildFly. :-)


Configuration JavaMail in WildFly 8
====================================

Example into standalone.xml
```xml
<mail-session name="App" jndi-name="java:/mail/gmail">
   <smtp-server outbound-socket-binding-ref="mail-smtp-gmail" ssl="true" username="email@gmail.com" password="your_password"/>
</mail-session>

```

```xml
<outbound-socket-binding name="mail-smtp-gmail">
   <remote-destination host="smtp.gmail.com" port="465"/>
</outbound-socket-binding>
```

Example with jboss-cli
```bash
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=mail-smtp-gmail:add(host=smtp.gmail.com,port=465)
```

```bash
/subsystem=mail/mail-session=App:add(jndi-name=java:/mail/gmail)
```

```bash
/subsystem=mail/mail-session=App/server=smtp:add(outbound-socket-binding-ref=mail-smtp-gmail, username=email@gmail.com, password=your_password, ssl=true)
```
