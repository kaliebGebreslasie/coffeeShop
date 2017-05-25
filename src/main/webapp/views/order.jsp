<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Coffee Shop-Products</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/home.css">
</head>
<body>
  <div class='container'>
   <h1>Coffee Shop</h1>
 
    <div class='navbar navbar-inverse'>
      <div class='navbar-inner nav-collapse' >
        <ul class="nav navbar-nav" style="width: 100%">
          <li class="active"><a href="admin">Home</a></li>
          <sec:authorize access="hasRole('ROLE_USER')">
          <li><a href="order">Order</a></li>
           </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
          <li><a href="editProducts">Edit Products</a></li>
        
          <li><a href="persons">Manage Person</a></li>
           <li><a href="orders" >List Orders</a></li>
             </sec:authorize>
             
            <li class="pull-right"><a href="logout" >Logout</a></li>
        
        </ul>
      </div>
    </div>
    <div id='content' class='row-fluid'>
    <div class='col-md-2 sidebar'>
         <h3>Today's Special<h3>
        <ul class="nav nav-tabs nav-stacked">
          <li><a href='#'>Latte</a></li>
        </ul>
      </div>
      <div class='span8 main'>
        <h2>Order</h2>
        <div class="container">
        <c:if test="${list !=null }">
         <div class="row col-md-6 col-md-offset-2 custyle">
    
    <table class="table table-striped custab">
    
          
      
   
   <c:forEach items="${list}" var="ol">
   <tr><td>${ol.product.productName}
  </td><td>${ol.quantity}</td></tr>
    </c:forEach>
  
      
       <form class="form" role="form" method="POST" action="order" >
      <td class="text-center"><button  class='btn btn-info btn-xs' href="#" type="submit"><span class="glyphicon glyphicon-edit"></span> Order</button> </td></tr>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     </form>
      </table>
     
        </div>
        </c:if>
</div>
        
        
        
    <div class="row col-md-6 col-md-offset-2 custyle">
  
     <form class="form" role="form" method="GET" action="addOrder" >
    <table class="table table-striped custab">
    <thead>
    
    <button class="btn btn-primary btn-xs pull-right" type="submit" ><b>+</b> Add new Order</button>
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
           
           
        </tr>
         </thead>
      <tr><td>    
      
   <select name="selProduct">
   <c:forEach items="${products}" var="product">
   <option value="${product.id}">${product.productName}</option> 
    </c:forEach>
   </select> 
      
     <td><input type="text" name="quantity"/></td>
      </td>
       </form>
      </tr>
      </table>
        </div>
</div>
      </div>
     
    </div>
  </div>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <!-- <script src="${pageContext.request.contextPath}/assets/order.js" ></script> -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</body>
</html>