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
             <li class="pull-right"><a href="editProfile/${user.id}" >${user.firstName }  </a></li>
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
        <h2>Products</h2>
        <div class="container">
    <div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab">
    <thead>
    <a href="newProduct" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new product</a>
        <tr>
            <th>Product Name</th>
            <th>Type</th>
            <th>Price</th>
             <th>Description</th>
            <th class="text-center">Action</th>
        </tr>
         </thead>
        
      <c:forEach items="${products}" var="product">
      <tr><td>${product.productName}</td><td>${product. productType}</td>  
      <td>${product.price}</td><td>${product.description}</td>
      <td class="text-center"><a href="products/${product.id}" class='btn btn-info btn-xs' href="#"><span class="glyphicon glyphicon-edit"></span> Edit</a> <a href="del/${product.id}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td></tr>
      </c:forEach>
      </table>
        </div>
</div>
      </div>
     
    </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</body>
</html>