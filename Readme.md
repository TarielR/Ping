<h2>Simple Rest API for work with JSON.</h2>

<p>For use clone the project and run 'mvn spring-boot: run' in the root folder (pom file level).</p>

<p>If you terminate the app with 'Ctrl+Z' port may be stay busy so find PID with 'lsoft -i -P | grep 9001'
and kill the procces with 'kill -9 PID_NUM'</p>

<h3>API</h3>
<p>GET localhost:9001/persons</p>
<p>GET localhost:9001/person/{id)    id must be between 7 - 12</p>
