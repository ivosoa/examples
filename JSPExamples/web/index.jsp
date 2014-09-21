<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    
  </head>
  <body>
    <h1>Hello World!</h1>
    <% 
      List<String> list = new ArrayList();
      for(char c='A'; c<='Z'; c++){
        list.add(String.valueOf(c));
      }
      Map<String, String> map = new TreeMap();
      map.put("Atest1", "testA1");
      map.put("Atest2", "testA2");
      map.put("Btest1", "testB1");
      map.put("Btest2", "testB2");
      map.put("Btest3", "testB3");
      map.put("Ktest1", "testK1");
      map.put("Ktest2", "testK2");
      map.put("Mtest1", "testM1");
      map.put("Vtest1", "testV1");
      map.put("Vtest2", "testV2");
      map.put("Ztest1", "testZ1");
      map.put("Ztest2", "testZ2");
      map.put("Ztest3", "testZ3");
      pageContext.setAttribute("list", list);
      pageContext.setAttribute("map", map);
      
      Map<String, List> categorizedMap = new TreeMap();
      for(String le: list){
        out.print("le: "+le);
        for(Map.Entry<String, String> me: map.entrySet()){
          
          if(me.getKey().toUpperCase().startsWith(le)){
            out.print("eeee ");
            if(!categorizedMap.containsKey(le)){
              categorizedMap.put(le, new ArrayList());
            }
            categorizedMap.get(le).add(me);
          }          
        }
      }
      pageContext.setAttribute("categorizedMap", categorizedMap);
    %>
    <c:forEach var="item" begin="1" end="5">
      <p>Item ${item}</p> 
   </c:forEach>
      
    <c:forEach var="i" begin="65" end="90">
      <%=Character.toChars((Integer)pageContext.getAttribute("i"))%>
    </c:forEach>
      <!--div>
      <c:forEach var="entry" items="${list}">
        <div>
          <span>${entry}</span>
        </div>
      </c:forEach>
      <c:forEach var="entry" items="${map}">
        <div>
          <span>${entry.key}</span> <span>${entry.value}</span>
        </div>
      </c:forEach>
      </div-->
      
      <c:forEach var="listEntry" items="${list}">
        <c:forEach var="mapEntry" items="${map}">
          <c:if test="${fn:startsWith(fn:toUpperCase(mapEntry.key), listEntry)}">
             <div>
              <span>${entry.key}</span> <span>${entry.value}</span>
             </div>
          </c:if>
        </c:forEach>
      </c:forEach>
      
      <c:forEach var="entry" items="${categorizedMap}">
        <div>
          <h1>${entry.key}</h1> 
          <ul>
            <c:forEach var="lval" items="${entry.value}">
                <li>${lval}</li>
            </c:forEach>
          </ul>
        </div>
      </c:forEach>
  </body>
</html>
