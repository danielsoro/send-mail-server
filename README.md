send-mail-server
================

Simples example about sending mail on WildFly. :-)

------------------------------------
Configuration JavaMail in WildFly 8

Example into standalone.xml
```xml
 <mail-session name="gmail" jndi-name="java:jboss/mail/Gmail"
       <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp-gmail">
           	<login name="email@gmail.com" password="password" />
         </smtp-server>
 </mail-session>
```

```xml
<outbound-socket-binding name="mail-smtp-gmail">
   <remote-destination host="smtp.gmail.com" port="465"/>
</outbound-socket-binding>
```
