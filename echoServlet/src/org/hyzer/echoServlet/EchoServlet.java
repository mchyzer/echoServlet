/**
 * @author mchyzer $Id: EchoServlet.java,v 1.12 2013/06/28 02:18:50 mchyzer Exp $
 */
package org.hyzer.echoServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * just echo stuff to make sure going there
 */
public class EchoServlet extends HttpServlet {

  /**
   * 
   * @param httpServletRequest 
   * @return the echo string
   */
  @SuppressWarnings("unchecked")
  public static String echoString(HttpServletRequest httpServletRequest) {

    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

    StringBuilder result = new StringBuilder();
    result.append("<pre>Method: ").append(httpServletRequest.getMethod());
    result.append("\n\nHeaders:\n\n");
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      result.append(headerName).append(": ").append(httpServletRequest.getHeader(headerName))
          .append("\n");
    }
    result.append("\n\nAttributes:\n\n");
    Enumeration<String> attributeNames = httpServletRequest.getAttributeNames();

    boolean foundRemoteUser = false;
    while (attributeNames.hasMoreElements()) {
      String attributeName = attributeNames.nextElement();
      
      if ("REMOTE_USER".equals(attributeName)) {
        foundRemoteUser = true;
      }
      
      result.append(attributeName).append(": ")
          .append(httpServletRequest.getAttribute(attributeName)).append("\n");
    }

    if (!foundRemoteUser) {
      if (httpServletRequest.getAttribute("REMOTE_USER") != null) {
        result.append("REMOTE_USER").append(": ")
            .append(httpServletRequest.getAttribute("REMOTE_USER")).append("\n");
      }
      
    }
    
    if (httpServletRequest.getAttribute("EPPN2") != null) {
      result.append("EPPN2").append(": ")
          .append(httpServletRequest.getAttribute("EPPN2")).append("\n");
    }
    if (httpServletRequest.getAttribute("EPPN") != null) {
      result.append("EPPN").append(": ")
          .append(httpServletRequest.getAttribute("EPPN")).append("\n");
    }
    if (httpServletRequest.getAttribute("eppn") != null) {
      result.append("eppn").append(": ")
          .append(httpServletRequest.getAttribute("eppn")).append("\n");
    }
    if (httpServletRequest.getAttribute("COSIGN_SERVICE") != null) {
      result.append("COSIGN_SERVICE").append(": ")
          .append(httpServletRequest.getAttribute("COSIGN_SERVICE")).append("\n");
    }
    if (httpServletRequest.getAttribute("COSIGN_FACTOR") != null) {
      result.append("COSIGN_FACTOR").append(": ")
          .append(httpServletRequest.getAttribute("COSIGN_FACTOR")).append("\n");
    }
    if (httpServletRequest.getAttribute("COSIGN_AUTHTIME") != null) {
      result.append("COSIGN_AUTHTIME").append(": ")
          .append(httpServletRequest.getAttribute("COSIGN_AUTHTIME")).append("\n");
    }
    if (httpServletRequest.getAttribute("TEST_ATTR") != null) {
      result.append("TEST_ATTR").append(": ").append(httpServletRequest.getAttribute("TEST_ATTR"))
          .append("\n");
    }

    result.append("\n\nParams:\n\n");
    Enumeration<String> paramNames = httpServletRequest.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String paramName = paramNames.nextElement();
      String paramValue = httpServletRequest.getParameter(paramName);
      if (equals(paramName, "password")) {
        paramValue = repeat("*", paramValue == null ? 0 : paramValue.length());
      }
      result.append(paramName).append(": ").append(paramValue).append("\n");
    }


    result.append("\n\nRequest details:\n\n");
    
    
    result.append("characterEncoding: ").append(httpServletRequest.getCharacterEncoding()).append("\n");
    result.append("contentLength: ").append(httpServletRequest.getContentLength()).append("\n");
    result.append("contentType: ").append(httpServletRequest.getContentType()).append("\n");
    result.append("cookies: ").append(httpServletRequest.getCookies() == null ? 0 : httpServletRequest.getCookies().length).append("\n");
    result.append("locale: ").append(httpServletRequest.getLocale()).append("\n");
    result.append("protocol: ").append(httpServletRequest.getProtocol()).append("\n");
    result.append("remoteAddr: ").append(httpServletRequest.getRemoteAddr()).append("\n");
    result.append("remoteHost: ").append(httpServletRequest.getRemoteHost()).append("\n");
    result.append("scheme: ").append(httpServletRequest.getScheme()).append("\n");
    result.append("serverName: ").append(httpServletRequest.getServerName()).append("\n");
    result.append("serverPort: ").append(httpServletRequest.getServerPort()).append("\n");
    result.append("secure: ").append(httpServletRequest.isSecure()).append("\n");
    result.append("authType: ").append(httpServletRequest.getAuthType()).append("\n");
    result.append("contextPath: ").append(httpServletRequest.getContextPath()).append("\n");
    result.append("method: ").append(httpServletRequest.getMethod()).append("\n");
    result.append("pathInfo: ").append(httpServletRequest.getPathInfo()).append("\n");
    result.append("pathTranslated: ").append(httpServletRequest.getPathTranslated()).append("\n");
    result.append("queryString: ").append(httpServletRequest.getQueryString()).append("\n");
    result.append("remoteUser: ").append(httpServletRequest.getRemoteUser()).append("\n");
    result.append("requestURI: ").append(httpServletRequest.getRequestURI()).append("\n");
    result.append("requestURL: ").append(httpServletRequest.getRequestURL()).append("\n");
    result.append("requestedSessionId: ").append(httpServletRequest.getRequestedSessionId()).append("\n");
    result.append("servletPath: ").append(httpServletRequest.getServletPath()).append("\n");
    result.append("requestedSessionIdFromCookie: ").append(httpServletRequest.isRequestedSessionIdFromCookie()).append("\n");
    result.append("requestedSessionIdFromURL: ").append(httpServletRequest.isRequestedSessionIdFromURL()).append("\n");
    result.append("requestedSessionIdValid: ").append(httpServletRequest.isRequestedSessionIdValid()).append("\n");
    String userPrincipal = httpServletRequest.getUserPrincipal() == null ? null : httpServletRequest.getUserPrincipal().getName();
    result.append("userPrincipal: ").append(userPrincipal).append("\n");    

    result.append("</pre>");
    return result.toString();
  }

  /**
   * <p>Compares two CharSequences, returning {@code true} if they are equal.</p>
   *
   * <p>{@code null}s are handled without exceptions. Two {@code null}
   * references are considered to be equal. The comparison is case sensitive.</p>
   *
   * <pre>
   * StringUtils.equals(null, null)   = true
   * StringUtils.equals(null, "abc")  = false
   * StringUtils.equals("abc", null)  = false
   * StringUtils.equals("abc", "abc") = true
   * StringUtils.equals("abc", "ABC") = false
   * </pre>
   *
   * @see java.lang.String#equals(Object)
   * @param cs1  the first CharSequence, may be null
   * @param cs2  the second CharSequence, may be null
   * @return {@code true} if the CharSequences are equal, case sensitive, or
   *  both {@code null}
   * @since 3.0 Changed signature from equals(String, String) to equals(CharSequence, CharSequence)
   */
  public static boolean equals(CharSequence cs1, CharSequence cs2) {
    return cs1 == null ? cs2 == null : cs1.equals(cs2);
  }

  /**
   * The empty String {@code ""}.
   * @since 2.0
   */
  public static final String EMPTY = "";

  /**
   * <p>The maximum size to which the padding constant(s) can expand.</p>
   */
  private static final int PAD_LIMIT = 8192;

  /**
   * <p>Repeat a String {@code repeat} times to form a
   * new String.</p>
   *
   * <pre>
   * StringUtils.repeat(null, 2) = null
   * StringUtils.repeat("", 0)   = ""
   * StringUtils.repeat("", 2)   = ""
   * StringUtils.repeat("a", 3)  = "aaa"
   * StringUtils.repeat("ab", 2) = "abab"
   * StringUtils.repeat("a", -2) = ""
   * </pre>
   *
   * @param str  the String to repeat, may be null
   * @param repeat  number of times to repeat str, negative treated as zero
   * @return a new String consisting of the original String repeated,
   *  {@code null} if null String input
   */
  public static String repeat(String str, int repeat) {
    // Performance tuned for 2.0 (JDK1.4)

    if (str == null) {
      return null;
    }
    if (repeat <= 0) {
      return EMPTY;
    }
    int inputLength = str.length();
    if (repeat == 1 || inputLength == 0) {
      return str;
    }
    if (inputLength == 1 && repeat <= PAD_LIMIT) {
      return repeat(str.charAt(0), repeat);
    }

    int outputLength = inputLength * repeat;
    switch (inputLength) {
      case 1:
        return repeat(str.charAt(0), repeat);
      case 2:
        char ch0 = str.charAt(0);
        char ch1 = str.charAt(1);
        char[] output2 = new char[outputLength];
        for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
          output2[i] = ch0;
          output2[i + 1] = ch1;
        }
        return new String(output2);
      default:
        StringBuilder buf = new StringBuilder(outputLength);
        for (int i = 0; i < repeat; i++) {
          buf.append(str);
        }
        return buf.toString();
    }
  }

  /**
   * <p>Repeat a String {@code repeat} times to form a
   * new String, with a String separator injected each time. </p>
   *
   * <pre>
   * StringUtils.repeat(null, null, 2) = null
   * StringUtils.repeat(null, "x", 2)  = null
   * StringUtils.repeat("", null, 0)   = ""
   * StringUtils.repeat("", "", 2)     = ""
   * StringUtils.repeat("", "x", 3)    = "xxx"
   * StringUtils.repeat("?", ", ", 3)  = "?, ?, ?"
   * </pre>
   *
   * @param str        the String to repeat, may be null
   * @param separator  the String to inject, may be null
   * @param repeat     number of times to repeat str, negative treated as zero
   * @return a new String consisting of the original String repeated,
   *  {@code null} if null String input
   * @since 2.5
   */
  public static String repeat(String str, String separator, int repeat) {
    if (str == null || separator == null) {
      return repeat(str, repeat);
    }
    // given that repeat(String, int) is quite optimized, better to rely on it than try and splice this into it
    String result = repeat(str + separator, repeat);
    return removeEnd(result, separator);
  }

  /**
   * <p>Returns padding using the specified delimiter repeated
   * to a given length.</p>
   *
   * <pre>
   * StringUtils.repeat(0, 'e')  = ""
   * StringUtils.repeat(3, 'e')  = "eee"
   * StringUtils.repeat(-2, 'e') = ""
   * </pre>
   *
   * <p>Note: this method doesn't not support padding with
   * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
   * as they require a pair of {@code char}s to be represented.
   * If you are needing to support full I18N of your applications
   * consider using {@link #repeat(String, int)} instead.
   * </p>
   *
   * @param ch  character to repeat
   * @param repeat  number of times to repeat char, negative treated as zero
   * @return String with repeated character
   * @see #repeat(String, int)
   */
  public static String repeat(char ch, int repeat) {
    char[] buf = new char[repeat];
    for (int i = repeat - 1; i >= 0; i--) {
      buf[i] = ch;
    }
    return new String(buf);
  }

  /**
   * <p>Removes a substring only if it is at the end of a source string,
   * otherwise returns the source string.</p>
   *
   * <p>A {@code null} source string will return {@code null}.
   * An empty ("") source string will return the empty string.
   * A {@code null} search string will return the source string.</p>
   *
   * <pre>
   * StringUtils.removeEnd(null, *)      = null
   * StringUtils.removeEnd("", *)        = ""
   * StringUtils.removeEnd(*, null)      = *
   * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
   * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
   * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
   * StringUtils.removeEnd("abc", "")    = "abc"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  {@code null} if null String input
   * @since 2.1
   */
  public static String removeEnd(String str, String remove) {
    if (isEmpty(str) || isEmpty(remove)) {
      return str;
    }
    if (str.endsWith(remove)) {
      return str.substring(0, str.length() - remove.length());
    }
    return str;
  }

  /**
   * <p>Checks if a CharSequence is empty ("") or null.</p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * <p>NOTE: This method changed in Lang version 2.0.
   * It no longer trims the CharSequence.
   * That functionality is available in isBlank().</p>
   *
   * @param cs  the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is empty or null
   * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
   */
  public static boolean isEmpty(CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  /**
   * 
   * @see javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
   */
  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException,
      IOException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

    try {

      String echoString = echoString(httpServletRequest);
      printToScreen(echoString, "text/html", true, true, httpServletResponse);

    } catch (Exception e) {

      e.printStackTrace();

      //dont set status code since browser might change text to user

      if (e instanceof RuntimeException) {
        throw (RuntimeException) e;
      }
      throw new RuntimeException(e);

    }
  }

  /**
   * Print some text to the screen
   * @param string 
   * @param httpContentType e.g. "text/html", "text/xml", should get from HttpContentType
   * @param includeXmlTag 
   * @param includeHtmlTag 
   * @param response 
   * 
   */
  public static void printToScreen(String string, String httpContentType,
      boolean includeXmlTag, boolean includeHtmlTag, HttpServletResponse response) {

    //say it is HTML, if not too late
    if (httpContentType != null && !response.isCommitted()) {
      response.setContentType("text/html");
    }

    //just write some stuff
    PrintWriter out = null;

    try {
      out = response.getWriter();
    } catch (Exception e) {
      throw new RuntimeException("Cant get response.getWriter: ", e);
    }

    if (includeXmlTag) {
      out.println("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n"
          + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 "
          + "Transitional//EN\" " +
          "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
    }

    //see if we should add <html> etc
    if (includeHtmlTag) {
      out.println("<html><head></head><body>");
      out.println(string);
      out.println("</body></html>");
    } else {
      out.println(string);
    }

    out.close();

  }

}
