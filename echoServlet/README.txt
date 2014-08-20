servlet to echo the request back to the browser.  Setup in web.xml
 
  <!-- Echo servlet so we can see the http -->
  <servlet>
    <servlet-name>echo</servlet-name>
    <servlet-class>org.hyzer.echoServlet.EchoServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>echo</servlet-name>
    <url-pattern>/echo</url-pattern>
  </servlet-mapping>

Go to the app:

https://app.whatever/appName/echo?paramName1=paramValue1 
